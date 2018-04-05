package daiw.com.rulemodule;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/3/30  下午12:52
 *
 *         ***************************
 */

public interface RuleService {

    public void attach(Service proxyService);

    public void onCreate();

    public void onStart(Intent intent,int startId);

    public int onStartCommand(Intent intent,int flags,int startId);

    public boolean stopService(Intent intent);

    public void onDestroy();

    public IBinder onBind(Intent intent);

    public boolean onUnbind(Intent intent);

    public void onRebind(Intent intent);

    public void onTeskRemoved(Intent rootIntent);

    public void onConfigurationChanged(Configuration newConfig);

    public void onLowMemory();

    public void onTrimMemory(int level);





}
