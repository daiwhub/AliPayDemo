package daiw.com.rulemodule;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/3/28  下午11:27
 *         深圳市优讯信息技术有限公司
 *         ***************************
 */

public interface RuleActivity {

    void attach(Activity proxyActivity);

    /**
     * 生命周期
     *
     * @param savedInstanceState
     */

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);

    boolean onTouchEvent(MotionEvent event);

    void onBackPressed();

}
