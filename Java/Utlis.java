package com.example.easystq;

import android.app.Activity;
import android.content.Context;

import com.gyf.immersionbar.ImmersionBar;

public class Utlis {

    private static String AppID="56396852";
    private static String APPSecret="EKdmUN78";
    public static void status(Context context) {
        ImmersionBar.with((Activity) context)
                .statusBarColor(R.color.top) // 状态栏颜色
                .navigationBarColor(R.color.top) // 导航栏深色
                .statusBarDarkFont(true) // 状态栏深色
                .navigationBarDarkIcon(true) // 导航栏深色
                .fitsSystemWindows(true) // 解决状态栏和布局重叠问题
                .init();
    }

    //数据请求地址
    private static String GETUrl="https://www.yiketianqi.com/free/day?appid="+AppID+"&appsecret="+APPSecret+"&unescape=1";
    public static String getGETUrl() {
        return GETUrl;
    }
}
