package com.rustfisher.tutorial2020.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.Locale;

public class AlignCenterDrawTextView extends BaseView {

    public AlignCenterDrawTextView(Context context) {
        this(context, null);
    }

    public AlignCenterDrawTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlignCenterDrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawChart(canvas);

    }

    private void drawChart(Canvas canvas) {
        final float x0 = 100;
        final float y0 = 300; // 原点在画布上的坐标
        final float halfLine = 5; // 刻度长度的一半

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20);
        notePaint.setStrokeWidth(2);
        canvas.drawLine(x0, y0, x0 + 500, y0, notePaint); // 绘制横轴
        canvas.drawLine(x0, y0, x0, y0 - 290, notePaint); // 绘制纵轴

        // 绘制横轴刻度和数字
        for (int i = 1; i <= 4; i++) {
            int step = i * 100;
            String n = String.valueOf(step);
            float x = x0 + step;
            canvas.drawLine(x, y0 - halfLine, x, y0 + halfLine, notePaint); // 刻度

            // 文字对齐
            textPaint.getTextBounds(n, 0, n.length(), textRect);
            canvas.drawText(n, x - textRect.width() / 2f, y0 + textRect.height() + halfLine, textPaint);
        }


        // 绘制纵轴刻度和数字
        for (int i = 1; i <= 2; i++) {
            int step = i * 100;
            String n = String.valueOf(step);
            float y = y0 - step;
            canvas.drawLine(x0 - halfLine, y, x0 + halfLine, y, notePaint); // 刻度

            // 文字对齐
            textPaint.getTextBounds(n, 0, n.length(), textRect);
            canvas.drawText(n, x0 - halfLine * 2 - textRect.width(), y + textRect.height() / 2f, textPaint);
        }

        // 绘制原点 0
        String origin = "0";
        textPaint.getTextBounds(origin, 0, origin.length(), textRect);
        canvas.drawText(origin, x0 - textRect.width(), y0 + textRect.height(), textPaint);
    }


}
