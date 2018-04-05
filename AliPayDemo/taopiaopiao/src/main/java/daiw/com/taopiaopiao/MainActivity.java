package daiw.com.taopiaopiao;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.addLogAdapter(new AndroidLogAdapter());

        initView();
    }

    private void initView() {

        findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(that, "确认购票", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(that, SecondActivity.class));
//
//                startService(new Intent(that, OneService.class));

                //注册广播
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("daiw.com.taopiaopiao.MainActivity");
                registerReceiver(new MyReceiver(), intentFilter);
            }
        });


        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送广播
                Intent intent = new Intent();
                intent.setAction("daiw.com.taopiaopiao.MainActivity");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d("停止服务");
        //停止服务
        stopService(new Intent(that, OneService.class));
    }
}
