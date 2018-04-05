package daiw.com.alipaydemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Constructor;

import daiw.com.rulemodule.RuleService;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/3/30  下午1:15
 *
 *         ***************************
 */

public class ProxyService extends Service {

    private String serviceName;
    private RuleService ruleService;

    @Override
    public IBinder onBind(Intent intent) {
        init(intent);
        return null;
    }

    private void init(Intent intent) {
        serviceName = intent.getStringExtra("serviceName");

        Logger.d("serviceName: "+ serviceName);

        try {
            Class loadClass = PluginManager.getInstance().getDexClassLoader().loadClass(serviceName);

            Logger.d("loadClass: "+ loadClass);

            Constructor<?> constructor = loadClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});

            ruleService = (RuleService) instance;

            ruleService.attach(this);

            ruleService.onCreate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(ruleService == null){
            init(intent);
        }

        return ruleService.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        Logger.d("停止服务");

        return ruleService.stopService(name);
    }

    @Override
    public void onDestroy() {
        ruleService.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        ruleService.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        ruleService.onUnbind(intent);
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        ruleService.onRebind(intent);
        super.onRebind(intent);
    }
}
