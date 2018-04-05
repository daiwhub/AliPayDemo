package daiw.com.taopiaopiao;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/4/2  上午11:41
 *         深圳市优讯信息技术有限公司
 *         ***************************
 */

public class TwoService extends BaseIntentService {

    private int i = 0;

    public TwoService(){
        super("TwoService");
    }

    public TwoService(String name) {
        super(name);
    }

    @Override
    public void onHandleIntent(Intent intent) {
        super.onHandleIntent(intent);
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    Log.i("TwoService", "run: " + (i++));
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
        Log.i("TwoService", "run: ");

    }
}
