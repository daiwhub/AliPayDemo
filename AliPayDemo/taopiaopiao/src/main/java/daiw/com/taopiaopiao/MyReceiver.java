package daiw.com.taopiaopiao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import daiw.com.rulemodule.RuleBroadcastReceiver;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/4/3  下午8:37
 *         深圳市优讯信息技术有限公司
 *         ***************************
 */

public class MyReceiver extends BroadcastReceiver implements RuleBroadcastReceiver{

    private Context that;

    @Override
    public void attach(Context context) {
        that=context;

        Toast.makeText(context, "绑定上下文成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "插件收到广播", Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "插件收到广播1", Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "插件收到广播2", Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "插件收到广播3", Toast.LENGTH_SHORT).show();
    }
}
