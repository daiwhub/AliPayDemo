package daiw.com.taopiaopiao;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import daiw.com.rulemodule.RuleActivity;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/3/28  下午11:31
 *
 *         ***************************
 */

public class BaseActivity extends Activity implements RuleActivity{

    protected Activity that;

    @Override
    public void attach(Activity activity) {
        this.that = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void setContentView(int layoutResID) {
        if(that != null) {
            that.setContentView(layoutResID);
        }else{
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view) {
        if(that != null) {
            that.setContentView(view);
        }else{
            super.setContentView(view);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if(that != null) {
            return that.findViewById(id);
        }else {
            return super.findViewById(id);
        }
    }

    @Override
    public Intent getIntent() {
        if(that != null) {
            return that.getIntent();
        }else{
            return super.getIntent();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if(that != null) {
            Intent mIntent = new Intent();
            mIntent.putExtra("className" , intent.getComponent().getClassName());
            that.startActivity(mIntent);
        }else{
            super.startActivity(intent);
        }
    }

    @Override
    public ComponentName startService(Intent service) {
        String serviceName = service.getComponent().getClassName();
        Intent mService = new Intent();
        mService.putExtra("serviceName",serviceName);

        return that.startService(mService);
    }

    @Override
    public boolean stopService(Intent service) {

        String serviceName = service.getComponent().getClassName();
        Intent mService = new Intent();
        mService.putExtra("serviceName",serviceName);

        return that.stopService(service);
    }

     /*
      * @Description : 广播的注册
      * @Params :
      * @Author : daiw
      * @Date : 2018/4/5
      */
    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        return that.registerReceiver(receiver, filter);
    }
     /*
      * @Description : 广播的发送
      * @Params :
      * @Author : daiw
      * @Date : 2018/4/5
      */
    @Override
    public void sendBroadcast(Intent intent) {
        that.sendBroadcast(intent);
    }

    public ClassLoader getClassLoader(){
        return that.getClassLoader();
  }

    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return that.getApplicationInfo();
    }


    @Override
    public Window getWindow() {
        return that.getWindow();
    }


    @Override
    public WindowManager getWindowManager() {
        return that.getWindowManager();
    }

}
