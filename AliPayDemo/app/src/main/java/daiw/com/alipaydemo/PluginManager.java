package daiw.com.alipaydemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import dalvik.system.DexClassLoader;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/3/29  上午11:39
 *
 *         ***************************
 */

public class PluginManager {
    private static PluginManager instance = new PluginManager();

    private PackageInfo packageInfo;
    private Resources resources;
    private DexClassLoader dexClassLoader;
    private Context context;


    private PluginManager() {

    }

    public static PluginManager getInstance() {
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public Resources getResources() {
        return resources;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public void loadPath(Context context) {
        File fileDir = context.getDir("plugin", Context.MODE_PRIVATE);
        String name = "taopiaopiao.apk";
        String filePath = new File(fileDir, name).getAbsolutePath();

        PackageManager packageManager = context.getPackageManager();
        packageInfo = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);

        //获取activity的名字
        File dexOutFile = context.getDir("dex", Context.MODE_PRIVATE);
        dexClassLoader = new DexClassLoader(filePath, dexOutFile.getAbsolutePath(), null,
                context.getClassLoader());

        //获取resources
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getMethod("addAssetPath", String.class);
            method.invoke(assetManager, filePath);
            resources = new Resources(assetManager, context.getResources().getDisplayMetrics(),
                    context.getResources().getConfiguration());


        } catch (Exception e) {
            e.printStackTrace();
        }

        parseReceivers(context, filePath);
    }
     /*
      * @Description : 
      * @Params : 解析插件apk中的清单文件，获取静态广播，从而动态注册
      * @Author : daiw
      * @Date : 2018/4/5
      */
    private void parseReceivers(Context context, String filePath) {
        try {
            Class packageParserClass = Class.forName("android.content.pm.PackageParser");
            Method parsePackageMethod = packageParserClass.getDeclaredMethod("parsePackage",
                    File.class, int.class);
            Object packageParser = packageParserClass.newInstance();
            Object packageObj = parsePackageMethod.invoke(packageParser, new File(filePath),
                    PackageManager.GET_ACTIVITIES);

            Field fieldReceiver = packageObj.getClass().getDeclaredField("receivers");
            //拿到receivers  广播集合    app存在多个广播   集合  List<Activity>  name  ————》 ActivityInfo   className
            List receivers = (List) fieldReceiver.get(packageObj);

            Class componentClass = Class.forName("android.content.pm.PackageParser$Component");
            Field intentsField = componentClass.getDeclaredField("intents");

            // 调用generateActivityInfo 方法, 把PackageParser.Activity 转换成
            Class packageParser$ActivityClass = Class.forName("android.content.pm.PackageParser$Activity");
            //generateActivityInfo方法
            Class packageUserStateClass = Class.forName("android.content.pm.PackageUserState");
            Object defaltUserState = packageUserStateClass.newInstance();

            Method generateActivityInfo = packageParserClass.getDeclaredMethod("generateActivityInfo",
                    packageParser$ActivityClass, int.class, packageUserStateClass, int.class);
            Class userHandler = Class.forName("android.os.UserHandle");
            Method getCallingUserIdMethod = userHandler.getDeclaredMethod("getCallingUserId");
            int userId = (int) getCallingUserIdMethod.invoke(null);

            for (Object activity : receivers) {
                ActivityInfo info = (ActivityInfo) generateActivityInfo.invoke(packageParser,
                        activity, 0, defaltUserState, userId);
                BroadcastReceiver receiver = (BroadcastReceiver) dexClassLoader.loadClass(info.name).newInstance();
                List<? extends IntentFilter> intents = (List<? extends IntentFilter>) intentsField.get(activity);

                for (IntentFilter intentFilter : intents) {
                    context.registerReceiver(receiver, intentFilter);
                    //此处有个问题：当应用退出时，如何unregisterReceiver这些动态注册的广播
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
