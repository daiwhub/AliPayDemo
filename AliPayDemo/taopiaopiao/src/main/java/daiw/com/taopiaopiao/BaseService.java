package daiw.com.taopiaopiao;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;


import daiw.com.rulemodule.RuleService;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/3/30  下午1:05
 *
 *         ***************************
 */

public class BaseService extends Service implements RuleService {

    protected String TAG = BaseActivity.class.getCanonicalName();

    private Service that;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void attach(Service proxyService) {
        that = proxyService;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onStartCommand");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onConfigurationChanged");
    }

    @Override
    public void onLowMemory() {
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onTrimMemory");

    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onUnbind");
        return false;
    }

    @Override
    public void onRebind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onRebind");
    }

    @Override
    public void onTeskRemoved(Intent rootIntent) {
        Log.d(TAG, TAG + " onTeskRemoved");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        // TODO Auto-generated method stub
        Log.d(TAG, TAG + " onTaskRemoved");
    }
}
