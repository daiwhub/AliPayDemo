package daiw.com.alipaydemo;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity implements View.OnClickListener {

    private final int PERMISSION_REQUEST_CODE = 100;

    static final String ACTION = "daiw.com.taopiaopiao.receicet.PUBLIC_ACTION";

    private Button btnDownLoad;
    private Button btnStart;
    private Button btnCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().setContext(this);
        initView();
        //注册广播器
        registerReceiver(mReceiver,new IntentFilter(ACTION));
    }
     /*
      * @Description : 接收插件广播器发送过来的广播
      * @Params :
      * @Author : daiw
      * @Date : 2018/4/5
      */
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, " 我是宿主，收到你的消息,握手完成!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private void permissionCheck() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 没有权限，申请权限。
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            // 有权限了，去放肆吧。
            //下载淘票票
            loadPlugin();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 权限被用户同意，可以去放肆了。
                    //下载淘票票
                    loadPlugin();
                } else {
                    // 权限被用户拒绝了，洗洗睡吧。
                    Toast.makeText(this, "请开启存储权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void initView() {
        btnDownLoad = (Button) findViewById(R.id.btnDownLoad);
        btnStart = (Button) findViewById(R.id.btnStart);

        btnDownLoad.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnCast = (Button) findViewById(R.id.btnCast);
        btnCast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDownLoad:
                permissionCheck();
                break;
            case R.id.btnStart:
                //跳转淘票票
                Intent intent = new Intent(this, ProxyActivity.class);
                intent.putExtra("className", PluginManager.getInstance().getPackageInfo().activities[0].name);
                startActivity(intent);
                break;
            case R.id.btnCast:
                //给插件发送广播
                Toast.makeText(this, "我是宿主  插件插件!收到请回答!!  1", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent();
                intent1.setAction("daiw.com.taopiaopiao.MainActivity");
                sendBroadcast(intent1);
                break;
        }
    }

    private void loadPlugin() {
        File filesDir = this.getDir("plugin", Context.MODE_PRIVATE);
        String name = "taopiaopiao.apk";
        String filePath = new File(filesDir, name).getAbsolutePath();
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        InputStream is = null;
        FileOutputStream out = null;

        Logger.d("加载插件：" + new File(Environment.getExternalStorageDirectory(), name).getAbsolutePath());

        try {
            is = new FileInputStream(new File(Environment.getExternalStorageDirectory(), name));
            out = new FileOutputStream(filePath);

            int len = 0;
            byte[] buffer = new byte[1024];

            while ((len = is.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            File file0 = new File(filePath);
            if (file0.exists()) {
                Toast.makeText(this, "dex overwrite", Toast.LENGTH_SHORT).show();
            }

            PluginManager.getInstance().loadPath(this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
