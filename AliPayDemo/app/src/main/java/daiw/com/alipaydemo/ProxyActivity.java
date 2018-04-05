package daiw.com.alipaydemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Constructor;


import daiw.com.rulemodule.RuleActivity;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/3/29  下午7:24
 *
 *         ***************************
 */

public class ProxyActivity extends Activity {

    private String className;
    private RuleActivity ruleActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        className = getIntent().getStringExtra("className");

        try {
            Class activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});

            ruleActivity = (RuleActivity) instance;
            ruleActivity.attach(this);
            Bundle bundle = new Bundle();
            ruleActivity.onCreate(bundle);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {

        String className = intent.getStringExtra("className");
        Intent mIntent = new Intent(this,ProxyActivity.class);
        mIntent.putExtra("className",className);

        super.startActivity(mIntent);
    }

    @Override
    public ComponentName startService(Intent service) {
        String serviceName = service.getStringExtra("serviceName");
        Intent mService = new Intent(this,ProxyService.class);
        mService.putExtra("serviceName" , serviceName);

        return super.startService(mService);
    }

    @Override
    public boolean stopService(Intent service) {

        Logger.d("停止服务");

        String serviceName = service.getStringExtra("serviceName");
        Intent mService = new Intent(this,ProxyService.class);
        mService.putExtra("serviceName" , serviceName);

        return super.stopService(service);
    }
     /*
      * @Description : 注册广播
      * @Params :
      * @Author : daiw
      * @Date : 2018/4/5
      */
    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {

//        IntentFilter intentFilter = new IntentFilter();
//        //循环获取多个action，并添加到新的intentFilter中
//        for(int i = 0;i<filter.countActions() ; i++){
//            intentFilter.addAction(filter.getAction(i));
//        }

        return super.registerReceiver(new ProxyBroadcastReceiver(
                this,receiver.getClass().getName()), filter);
    }
     /*
      * @Description :发送广播
      * @Params :
      * @Author : daiw
      * @Date : 2018/4/5
      */
    @Override
    public void sendBroadcast(Intent intent) {
        super.sendBroadcast(intent);
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ruleActivity.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ruleActivity.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ruleActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ruleActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ruleActivity.onDestroy();
    }
}
