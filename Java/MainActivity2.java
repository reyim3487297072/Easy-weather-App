package com.example.easystq;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {


    //设置全局变量
    private TextView tem;
    private TextView data1,data2,data3,data4;
    private TextView datas1,datas2,datas3,datas4,datas5,datas6,datas7,datas8,datas9;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //调用状态栏函数
        Utlis.status(this);

        //获取控件
        tem=findViewById(R.id.tem);
        data1=findViewById(R.id.data1);
        data2=findViewById(R.id.data2);
        data3=findViewById(R.id.data3);
        data4=findViewById(R.id.data4);
        datas1=findViewById(R.id.datas1);
        datas2=findViewById(R.id.datas2);
        datas3=findViewById(R.id.datas3);
        datas4=findViewById(R.id.datas4);
        datas5=findViewById(R.id.datas5);
        datas6=findViewById(R.id.datas6);
        datas7=findViewById(R.id.datas7);
        datas8=findViewById(R.id.datas8);
        datas9=findViewById(R.id.datas9);

        image=findViewById(R.id.tem_img);

        //数据处理
        DataList();


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
                    URL url = new URL(Utlis.getGETUrl());
                    System.out.println(url);
                    URLConnection backtraclk = url.openConnection();
                    InputStreamReader back = new InputStreamReader(backtraclk.getInputStream());
                    BufferedReader Readerl = new BufferedReader(back);
                    //Data转换成String类
                    String datas = Readerl.readLine();
                    //datas提交给Json函数进行解析并显示工作
                    JsonData(datas);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void JsonData(String datas) throws JSONException {
        //用官方JSONObject解析数据
        JSONObject jsonObject=new JSONObject(datas);
        //获取相应的值设置到控件上；
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tem.setText(jsonObject.optString("tem"));
                data1.setText(jsonObject.optString("city"));
                data2.setText(jsonObject.optString("date"));
                data3.setText(jsonObject.optString("week"));
                data4.setText(jsonObject.optString("wea"));//天气情况
                datas1.setText(jsonObject.optString("update_time"));//更新时间
                datas2.setText(jsonObject.optString("humidity"));//湿度
                datas3.setText(jsonObject.optString("pressure"));//气压
                datas4.setText(jsonObject.optString("air"));//空气质量
                datas5.setText(jsonObject.optString("win"));//风向
                datas6.setText(jsonObject.optString("win_meter"));//风速
                datas7.setText(jsonObject.optString("win_speed"));//风力
                datas8.setText(jsonObject.optString("tem_day"));//白天温度(高温)
                datas9.setText(jsonObject.optString("tem_night"));//夜间温度(低温)
                String img=jsonObject.optString("wea_img");//天气标识

                //两种方法来可实现
                //第一种switch
                //ImageSet(img);

                //第二种if
                //固定9种类型
                //xue、lei、shachen、wu、bingbao、yun、yu、yin、qing

                if (img.equals("xue"))
                    image.setImageResource(R.mipmap.snowfall);
                else if (img.equals("lei"))
                image.setImageResource(R.mipmap.lightning);
                else if (img.equals("shachen"))
                image.setImageResource(R.mipmap.wind);
                else if (img.equals("wu"))
                    image.setImageResource(R.mipmap.sunnywindy);
                else if (img.equals("bingbao"))
                    image.setImageResource(R.mipmap.bingnow);
                else if (img.equals("yun"))
                    image.setImageResource(R.mipmap.yun);
                else if (img.equals("yu"))
                    image.setImageResource(R.mipmap.yu);
                else if (img.equals("yin"))
                    image.setImageResource(R.mipmap.yin);
                else if (img.equals("qing"))
                    image.setImageResource(R.mipmap.sun);
                else image.setImageResource(R.mipmap.sun);
            }
        });
    }

    public void ImageSet(String img){
        switch (img){
            case "xue": image.setImageResource(R.mipmap.snowfall);
            break;
            case "lei": image.setImageResource(R.mipmap.lightning);
                break;
            case "shachen": image.setImageResource(R.mipmap.wind);
                break;
            case "wu": image.setImageResource(R.mipmap.sunnywindy);
                break;
            case "bingbao": image.setImageResource(R.mipmap.bingnow);
                break;
            case "yun": image.setImageResource(R.mipmap.yun);
                break;
            case "yu": image.setImageResource(R.mipmap.yu);
                break;
            case "yin": image.setImageResource(R.mipmap.yin);
                break;
            case "qing": image.setImageResource(R.mipmap.sun);
                break;
        }
    }

    // 捕获返回键的方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            // 1. 通过Context获取ActivityManager
            ActivityManager activityManager = (ActivityManager) this.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            // 2. 通过ActivityManager获取任务栈
            List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();
            // 3. 逐个关闭Activity
            for (ActivityManager.AppTask appTask : appTaskList) {
                appTask.finishAndRemoveTask();
            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

}