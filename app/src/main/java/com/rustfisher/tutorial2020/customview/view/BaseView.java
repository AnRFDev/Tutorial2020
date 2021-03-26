package com.rustfisher.tutorial2020.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class BaseView extends View {
    static final int L_BIAS = 60;

    protected Paint notePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    protected Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    protected Rect textRect = new Rect();

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        textPaint.setTextSize(70);
        textPaint.setColor(Color.GRAY);

        notePaint.setTextSize(30);
        notePaint.setColor(Color.BLACK);
    }
}
