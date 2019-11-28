package com.example.demo006;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.demo006.util.UiUtils;

/**
 * 自己的布局文件
 */
public class JetReletiveLayout extends RelativeLayout {
    private boolean flag=true;
    public JetReletiveLayout(Context context) {
        super(context);
    }

    public JetReletiveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JetReletiveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 添加自定义属性
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (flag) {
            //获取缩放比
            float scraleX = UiUtils.getInstance(getContext()).getHorizontalScaleValue();
            float scraleY = UiUtils.getInstance(getContext()).getVerticalScaleValue();
            int childCount = this.getChildCount();//得到所有的孩子
            for (int i = 0; i < childCount; i++) {
                View childAt = this.getChildAt(i);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                layoutParams.width = (int) (layoutParams.width * scraleX);
                layoutParams.height = (int) (layoutParams.height * scraleY);

                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scraleX);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scraleX);
                layoutParams.topMargin = (int) (layoutParams.topMargin * scraleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scraleY);

//            this.setPadding();

            }
            flag=false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
