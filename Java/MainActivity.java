package com.example.easystq;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    //定义全局变量
    private LinearLayout main_l;
    private ObjectAnimator objectAnimator;
    private TextView mr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //调用状态栏函数
        Utlis.status(this);

        //获取控件
        main_l=findViewById(R.id.main_l);
        mr=findViewById(R.id.mr);

        //每日文案
        DataList();
        //进行跳转
        Start();

    }

    //进行跳转
    private void Start(){
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行跳转
                Intent intent =new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
                //跳转同时运行动画，动画要放到anim下面
                overridePendingTransition(R.anim.activity_visible, R.anim.activity_gone);
            }
        },2000); // 延时2秒
    }

    public void DataList() {
        //开启线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //获取网络Data
                    //调用另类拿到URL
                    //要菜单文件里给INTERNET权限
                    URL url = new URL("https://v1.hitokoto.cn/?encode=text");
                    System.out.println(url);
                    URLConnection backtraclk = url.openConnection();
                    InputStreamReader back = new InputStreamReader(backtraclk.getInputStream());
                    BufferedReader Readerl = new BufferedReader(back);
                    //Data转换成String类
                    String datas = Readerl.readLine();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mr.setText(datas);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}