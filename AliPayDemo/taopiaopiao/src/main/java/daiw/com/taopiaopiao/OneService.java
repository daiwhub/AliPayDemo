package daiw.com.taopiaopiao;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Created by baby on 2018/3/6.
 */

public class OneService extends BaseService {
    int i=0;

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                while ( i < 20) {
                    Logger.i("OneService run: "+(i++));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
