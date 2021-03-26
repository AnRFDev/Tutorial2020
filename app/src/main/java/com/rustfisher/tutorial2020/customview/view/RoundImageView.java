package com.rustfisher.tutorial2020.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.rustfisher.tutorial2020.R;

public class RoundImageView extends AppCompatImageView {
    private float vw, vh;
    private Path path = new Path();

    private float topLeftR;
    private float topRightR;
    private float botLeftR;
    private float botRightR;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        topLeftR = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_topLeftR, 0);
        topRightR = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_topRightR, 0);
        botLeftR = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_botLeftR, 0);
        botRightR = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_botRightR, 0);
        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        vw = getWidth();
        vh = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        if (vw > topLeftR && vh > topRightR) {
            path.moveTo(topLeftR, 0);
            path.lineTo(vw - topRightR, 0);
            path.quadTo(vw, 0, vw, topRightR);
            path.lineTo(vw, vh - botRightR);
            path.quadTo(vw, vh, vw - botRightR, vh);
            path.lineTo(botLeftR, vh);
            path.quadTo(0, vh, 0, vh - botLeftR);
            path.lineTo(0, topLeftR);
            path.quadTo(0, 0, topLeftR, 0);
            path.close();
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}

