package com.guang.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.guang.R;


/**
 * youxuanz
 *
 * @author hgy
 * @date 2018/8/22
 * 只对src有效 设置background无效
 */

public class RoundImageView extends ImageView {
    float width, height;
    private boolean misTopLeftOuterCircle = true;
    private boolean mIsTopRightOuterCircle = true;
    private boolean mIsBottomLeftOuterCircle = true;
    private boolean mIsBottomRightOuterCircle = true;
    private boolean mIsTopLeft;
    private boolean mIsTopRight;
    private boolean mIsBottomLeft;
    private boolean mIsBottomRight;
    private int mRadius;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.roundImageView, defStyleAttr, 0);
        int count = array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = array.getIndex(i);
            if (attr == R.styleable.roundImageView_isTopLeftOuterCircle) {
                misTopLeftOuterCircle = array.getBoolean(attr, true);
            }else if(attr == R.styleable.roundImageView_isTopRightOuterCircle) {
                mIsTopRightOuterCircle = array.getBoolean(attr, true);
            }else if(attr == R.styleable.roundImageView_isBottomLeftOuterCircle) {
                mIsBottomLeftOuterCircle = array.getBoolean(attr, true);
            }else if(attr == R.styleable.roundImageView_isBottomRightOuterCircle) {
                mIsBottomRightOuterCircle = array.getBoolean(attr, true);
            }else if(attr == R.styleable.roundImageView_isTopLeft) {
                mIsTopLeft = array.getBoolean(attr, true);
            }else if(attr == R.styleable.roundImageView_isTopRight) {
                mIsTopRight = array.getBoolean(attr, true);
            }else if(attr == R.styleable.roundImageView_isBottomLeft) {
                mIsBottomLeft = array.getBoolean(attr, true);
            }else if(attr == R.styleable.roundImageView_isBottomRight) {
                mIsBottomRight = array.getBoolean(attr, true);
            }else if(attr == R.styleable.roundImageView_radius) {
                mRadius = (int) array.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 0, getResources().getDisplayMetrics()));
            }
//            switch (attr) {
//                case R.styleable.roundImageView_isTopLeftOuterCircle://true为外半圆 false为内半圆
//                    misTopLeftOuterCircle = array.getBoolean(attr, true);
//                    break;
//                case R.styleable.roundImageView_isTopRightOuterCircle://true为外半圆 false为内半圆
//                    mIsTopRightOuterCircle = array.getBoolean(attr, true);
//                    break;
//                case R.styleable.roundImageView_isBottomLeftOuterCircle://true为外半圆 false为内半圆
//                    mIsBottomLeftOuterCircle = array.getBoolean(attr, true);
//                    break;
//                case R.styleable.roundImageView_isBottomRightOuterCircle://true为外半圆 false为内半圆
//                    mRadius = (int) array.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 0, getResources().getDisplayMetrics()));
//                    break;
//                case R.styleable.roundImageView_isTopLeft://左上角是否要半圆
//                    mIsTopLeft = array.getBoolean(attr, true);
//                    break;
//                case R.styleable.roundImageView_isTopRight://左上角是否要半圆
//                    mIsTopRight = array.getBoolean(attr, true);
//                    break;
//                case R.styleable.roundImageView_isBottomLeft://左上角是否要半圆
//                    mIsBottomLeft = array.getBoolean(attr, true);
//                    break;
//                case R.styleable.roundImageView_isBottomRight://左上角是否要半圆
//                    mIsBottomRight = array.getBoolean(attr, true);
//                    break;
//                case R.styleable.roundImageView_radius://左上角是否要半圆
//                    mRadius = (int) array.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 0, getResources().getDisplayMetrics()));
//                    break;
//                default:
//            }
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (width > mRadius && height > mRadius) {
            drawRadiusImage(canvas);
        }
        super.onDraw(canvas);

    }

    private void drawRadiusImage(Canvas canvas) {
        Path path = new Path();
        path.moveTo(mRadius, 0);
        path.lineTo(width - mRadius, 0);
        if (mIsTopRight) {
            if (mIsTopRightOuterCircle) {
                path.quadTo(width, 0, width, mRadius);
            }else {
                path.quadTo(width - mRadius, mRadius, width, mRadius);
            }

        }else {
            path.quadTo(width, 0, width, 0);
        }
//        path.quadTo(width - round, round, width, round);

        path.lineTo(width, height - mRadius);
        if (mIsBottomRight) {
            if (mIsBottomRightOuterCircle) {
                path.quadTo(width, height, width - mRadius, height);
            }else {
                path.quadTo(width - mRadius, height - mRadius, width - mRadius, height);
            }
        }else {
            path.quadTo(width, height, width, height);
        }
//            path.quadTo(width - round, height - round, width - round, height);


        path.lineTo(mRadius, height);
        if (mIsBottomLeft) {
            if (mIsBottomLeftOuterCircle) {
                path.quadTo(0, height, 0, height - mRadius);
            }else {
                path.quadTo(mRadius, height - mRadius, 0, height - mRadius);
            }
        }else {
            path.quadTo(0, height, 0, height);
        }
        path.lineTo(0, mRadius);
        if (mIsTopLeft) {
            if (misTopLeftOuterCircle) {
                path.quadTo(0, 0, mRadius, 0);
            }else {
                path.quadTo(mRadius, mRadius, mRadius, 0);
            }
        }else {
            path.quadTo(0, 0, 0, 0);
        }
        canvas.clipPath(path);
    }

}
