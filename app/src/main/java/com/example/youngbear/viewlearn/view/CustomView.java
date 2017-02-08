package com.example.youngbear.viewlearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.youngbear.viewlearn.R;
import com.example.youngbear.viewlearn.utils.ScreenUtils;

/**
 * Created by youngbear on 17/2/7.
 * 参考
 * http://blog.csdn.net/aigestudio/article/details/41212583
 */

public class CustomView extends View{

    private static String TAG = CustomView.class.getSimpleName();

    private Context mContext;
    /**
     * 画笔
     * */
    private Paint mPaint;
    /**
     * 位图
     * */
    private Bitmap mBitmap;
    /**
     * 位图绘制时左上角的起点坐标
     * */
    private int x, y;

    /**
     * 用来标识控件是否被点击过
     * */
    private boolean isClick;

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
        initRes(mContext);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick) {
                    mPaint.setColorFilter(null);
                    isClick = false;
                } else {
                    mPaint.setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0X00FFFF00));
                    isClick = true;
                }
                invalidate();
            }
        });
    }

    private void initRes(Context context) {
        mBitmap = BitmapFactory.decodeResource(
                context.getApplicationContext().getResources(), R.mipmap.mountain);

        int screenWidth = ScreenUtils.getScreenWidth(context);
        int screenHeight = ScreenUtils.getScreenHeight(context);

        Log.d(TAG, "screenWidth: " + screenWidth + ", screenHeight: " + screenHeight);

        x = screenWidth / 2 - mBitmap.getWidth() / 2;
        y = screenHeight / 2 - mBitmap.getHeight() / 2;
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
        mPaint.setStyle(Paint.Style.FILL);

        /**
         * 设置画笔颜色为自定义颜色
         * */
        mPaint.setColor(Color.argb(255, 255, 128, 103));

        /**
         * 设置描边的粗细，单位：像素px 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
         * */
        mPaint.setStrokeWidth(10);

        /**
         * 生成色彩矩阵
         * */
        ColorMatrix colorMatrix = new ColorMatrix(new float[] {
//                0.5f, 0, 0, 0, 0,
//                0, 0.5f, 0, 0, 0,
//                0, 0, 0.5f, 0, 0,
//                0, 0, 0, 1, 0

//                0.33f, 0.59f, 0.11f, 0, 0,
//                0.33f, 0.59f, 0.11f, 0, 0,
//                0.33f, 0.59f, 0.11f, 0, 0,
//                0, 0, 0, 1, 0

//                -1, 0, 0, 1, 1,
//                0, -1, 0, 1, 1,
//                0, 0, -1, 1, 1,
//                0, 0, 0, 1, 0

//                0, 0, 1, 0, 0,
//                0, 1, 0, 0, 0,
//                1, 0, 0, 0, 0,
//                0, 0, 0, 1, 0

//                0.393f, 0.769f, 0.189f, 0, 0,
//                0.349f, 0.686f, 0.168f, 0, 0,
//                0.272f, 0.534f, 0.131f, 0, 0,
//                0, 0, 0, 1, 0

//                1.5f, 1.5f, 1.5f, 0, -1,
//                1.5f, 1.5f, 1.5f, 0, -1,
//                1.5f, 1.5f, 1.5f, 0, -1,
//                0, 0, 0, 1, 0

                1.438f, -0.122f, -0.016f, 0, -0.03f,
                -0.062f, 1.378f, -0.016f, 0, 0.05f,
                -0.062f, -0.122f, 1.483f, 0, -0.02f,
                0, 0, 0, 1, 0
        });

//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//        mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x00000000));
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));
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

        /**
         * 绘制位图
         * */
        canvas.drawBitmap(mBitmap, x, y, mPaint);

//        canvas.drawCircle(ScreenUtils.getScreenWidth(mContext.getApplicationContext()) / 2,
//                ScreenUtils.getScreenHeight(mContext.getApplicationContext()) / 2,
//                200,
//                mPaint);
    }
}
