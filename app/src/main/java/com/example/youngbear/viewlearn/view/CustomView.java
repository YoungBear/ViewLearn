package com.example.youngbear.viewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.youngbear.viewlearn.utils.ScreenUtils;

/**
 * Created by youngbear on 17/2/7.
 * 参考
 * http://blog.csdn.net/aigestudio/article/details/41212583
 */

public class CustomView extends View implements Runnable{

    private static String TAG = CustomView.class.getSimpleName();

    private Context mContext;
    private Paint mPaint;
    private int radiu;

    public CustomView(Context context) {
        this(context, null, 0);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        /**
         * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
         *
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE：描边
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        mPaint.setStyle(Paint.Style.STROKE);

        /**
         * 设置画笔颜色为浅灰色
         * */
        mPaint.setColor(Color.LTGRAY);

        mPaint.setStrokeWidth(10);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        super.onDraw(canvas);

        canvas.drawCircle(ScreenUtils.getScreenWidth(mContext.getApplicationContext()) / 2,
                ScreenUtils.getScreenHeight(mContext.getApplicationContext()) / 2,
                radiu,
                mPaint);
    }


    @Override
    public void run() {
        while (true) {
            try {
                if (radiu <= 200) {
                    radiu += 10;
                    postInvalidate();
                } else {
                    radiu = 0;
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
