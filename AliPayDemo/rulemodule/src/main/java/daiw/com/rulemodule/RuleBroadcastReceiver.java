package daiw.com.rulemodule;

import android.content.Context;
import android.content.Intent;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/4/3  下午8:36
 *         深圳市优讯信息技术有限公司
 *         ***************************
 */

public interface RuleBroadcastReceiver {

    void attach(Context context);

    void onReceive(Context context, Intent intent);
}
