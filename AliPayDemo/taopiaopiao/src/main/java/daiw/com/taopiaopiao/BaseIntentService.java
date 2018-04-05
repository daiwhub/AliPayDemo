package daiw.com.taopiaopiao;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;

import daiw.com.rulemodule.RuleIntentService;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/4/2  上午11:40
 *
 *         ***************************
 */

public class BaseIntentService extends IntentService implements RuleIntentService{

    private Service than;

    @Override
    public void attach(IntentService proxyService) {
        than = proxyService;
    }


    @Override
    public void onHandleIntent(Intent intent) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return than.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        return than.stopService(name);
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        than.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        than.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        than.onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return than.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        than.onRebind(intent);
    }

    @Override
    public void onTeskRemoved(Intent rootIntent) {

    }

    public BaseIntentService(){
        super("BaseIntentService");
    }

    public BaseIntentService(String name) {
        super(name);
    }

}
