package daiw.com.alipaydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.lang.reflect.Constructor;

import daiw.com.rulemodule.RuleBroadcastReceiver;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/4/3  下午8:43
 *
 *         ***************************
 */

public class ProxyBroadcastReceiver extends BroadcastReceiver {
    private Context context;
    private String className;

    private RuleBroadcastReceiver ruleBroadcastReceiver;

    public ProxyBroadcastReceiver(Context context, String className) {
        this.context = context;
        this.className = className;

        try {
            //通过类名反射获取到规则广播类对象
            Class loadClass = PluginManager.getInstance().getDexClassLoader().loadClass(className);
            Constructor constructor = loadClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});

            ruleBroadcastReceiver = (RuleBroadcastReceiver) instance;
            //像插件app中注册上下文对象
            ruleBroadcastReceiver.attach(context);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //宿主app代理广播接收器中调用插件app的广播接收器
        ruleBroadcastReceiver.onReceive(context,intent);
    }
}
