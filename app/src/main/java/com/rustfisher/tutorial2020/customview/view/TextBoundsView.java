package com.rustfisher.tutorial2020.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

public class TextBoundsView extends BaseView {

    public TextBoundsView(Context context) {
        this(context, null);
    }

    public TextBoundsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextBoundsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBounds(canvas, 100, 200, "just do it");
        drawBounds(canvas, 100, 450, "Rust Fisher");
    }

    private void drawBounds(Canvas canvas, float tx, float ty, String onShowText) {
        canvas.drawText(onShowText, tx, ty, textPaint); // 写字
        textPaint.getTextBounds(onShowText, 0, onShowText.length(), textRect); // 获取text边界

        notePaint.setStrokeWidth(3);
        notePaint.setColor(Color.parseColor("#EF6C00"));

        textPaint.getFontMetrics();

        // 左边线
        canvas.drawLine(tx, ty - textRect.height() - L_BIAS, tx, ty + L_BIAS, notePaint);

        // 右边线
        canvas.drawLine(tx + textRect.width(), ty - textRect.height() - L_BIAS, tx + textRect.width(), ty + L_BIAS, notePaint);

        notePaint.setColor(Color.parseColor("#FF0277BD"));
        // 上边线
        canvas.drawLine(tx - L_BIAS, ty - textRect.height(), tx + textRect.width() + L_BIAS, ty - textRect.height(), notePaint);

        notePaint.setColor(Color.parseColor("#00695C"));
        // y - baseline
        canvas.drawLine(tx - L_BIAS, ty, tx + textRect.width() + L_BIAS, ty, notePaint);
    }
}
