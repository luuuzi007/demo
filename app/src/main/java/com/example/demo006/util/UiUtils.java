package com.example.demo006.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

//屏幕适配
public class UiUtils {
    //状态栏:用于得到状态栏的属性 ，高度、宽度等
    private static final String DIME_CLASS = "com.android.interal.R$dimen";
    Context context;
    //标准值(基本信息) 正常情况下应该保存在 配置信息中
    public static final float STANDARD_WIDTH = 1080f;
    public static final float STANDARD_HEIGHT = 1920f;
    //实际设备信息 480*800
    public float displayMetricsWidth;
    public float displayMetricsHeight;
    //单例
    private static UiUtils instance;

    public static UiUtils getInstance(Context context) {
        if (instance == null) {
            instance = new UiUtils(context);
        }
        return instance;
    }

    /**
     * z在这里把设备的相关信息初始化
     *
     * @param context
     */
    private UiUtils(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (this.displayMetricsWidth == 0.0f || this.displayMetricsHeight == 0.0f) {
            //这里可以得到设备宽 高 dpi等信息
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //需要得到状态栏的高度
            int systemBarHeight = getSystemBarHeight(context);
            //横竖屏问题
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {//横屏
                this.displayMetricsWidth = displayMetrics.heightPixels;
                this.displayMetricsHeight = displayMetrics.widthPixels - systemBarHeight;
            } else {
                this.displayMetricsWidth = displayMetrics.widthPixels;
                this.displayMetricsHeight = displayMetrics.heightPixels - systemBarHeight;
            }
        }
    }

    //获取状态栏的高度(通过反射拿到)
    private int getSystemBarHeight(Context context) {
        //system_bar_height 属性：表示状态栏高度
        return getValue(context, DIME_CLASS, "system_bar_height", 48);
    }

    private int getValue(Context context, String dimeClass, String system_bar_height, int defaultValue) {
        try {
            Class<?> clz = Class.forName(dimeClass);
            Object object = clz.newInstance();
            Field field = clz.getField(system_bar_height);
            int id = Integer.parseInt(field.get(object).toString());
            return context.getResources().getDimensionPixelSize(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //水平缩放比
    public float getHorizontalScaleValue() {
        return ((float) this.displayMetricsWidth)/STANDARD_WIDTH;
    }
    //垂直缩放比
    public float getVerticalScaleValue() {
        return ((float) this.displayMetricsHeight)/(STANDARD_HEIGHT-getSystemBarHeight(context));
    }
}
