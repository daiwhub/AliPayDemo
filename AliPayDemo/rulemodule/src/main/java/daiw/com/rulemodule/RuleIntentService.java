package daiw.com.rulemodule;

import android.app.IntentService;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/4/2  上午11:46
 *
 *         ***************************
 */

public interface RuleIntentService {

    void attach(IntentService proxyService);

    void onHandleIntent(Intent intent);

    void onCreate();

    void onStart(Intent intent,int startId);

    int onStartCommand(Intent intent,int flags,int startId);

    boolean stopService(Intent intent);

    void onDestroy();

    IBinder onBind(Intent intent);

    boolean onUnbind(Intent intent);

    void onRebind(Intent intent);

    void onTeskRemoved(Intent rootIntent);

    void onConfigurationChanged(Configuration newConfig);

    void onLowMemory();

    void onTrimMemory(int level);
}
