package daiw.com.taopiaopiao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/4/5  下午3:29
 *         深圳市优讯信息技术有限公司
 *         ***************************
 */

public class StaticBroadcastReciver extends BroadcastReceiver {

    static final String ACTION = "daiw.com.taopiaopiao.receicet.PUBLIC_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "我是插件   收到宿主的消息  静态注册的广播  收到宿主的消息----->", Toast.LENGTH_SHORT).show();

        context.sendBroadcast(new Intent(ACTION));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, "休眠之后---->", Toast.LENGTH_SHORT).show();
    }
}
