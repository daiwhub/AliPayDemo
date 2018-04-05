package daiw.com.alipaydemo;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Constructor;

import daiw.com.rulemodule.RuleIntentService;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/4/2  上午11:49
 *         深圳市优讯信息技术有限公司
 *         ***************************
 */

public class ProxyIntentService extends IntentService {

    private String serviceName;
    private RuleIntentService ruleIntentService;

    public ProxyIntentService(){
        super("ProxyIntentService");
    }

    public ProxyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    private void init(Intent service){
        serviceName = service.getStringExtra("serviceName");

        try {
            Class loadService = PluginManager.getInstance().getDexClassLoader().loadClass(serviceName);
            Constructor constructor = loadService.getConstructor(new Class[]{});
            Object intance =constructor.newInstance(new Object[]{});

            ruleIntentService = (RuleIntentService) intance;

            ruleIntentService.attach(this);

            ruleIntentService.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        init(intent);
        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(ruleIntentService == null){
            init(intent);
        }


        return ruleIntentService.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        Logger.d("停止服务");

        return ruleIntentService.stopService(name);
    }

    @Override
    public void onDestroy() {
        ruleIntentService.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        ruleIntentService.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        ruleIntentService.onUnbind(intent);
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        ruleIntentService.onRebind(intent);
        super.onRebind(intent);
    }
}
