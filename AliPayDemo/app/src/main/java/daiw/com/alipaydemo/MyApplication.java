package daiw.com.alipaydemo;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/3/29  上午11:31
 *         深圳市优讯信息技术有限公司
 *         ***************************
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
