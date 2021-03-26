package com.rustfisher.tutorial2020.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.Locale;

public class FontMetricsView extends BaseView {

    public FontMetricsView(Context context) {
        this(context, null);
    }

    public FontMetricsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontMetricsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float x = 100, y = 200;

        String text = "ab,hjk.xyz!opq?";
        drawFontMetrics(canvas, x, y, text);


    }

    private void drawFontMetrics(Canvas canvas, float x, float y, String text) {
        canvas.drawText(text, x, y, textPaint);
        textPaint.getTextBounds(text, 0, text.length(), textRect);
        Paint.FontMetrics fm = textPaint.getFontMetrics();

        notePaint.setStrokeWidth(1);
        notePaint.setColor(Color.BLACK);
        canvas.drawText(String.format(Locale.CHINA, "top:%.2f, bottom:%.2f", fm.top, fm.bottom), x, y + notePaint.getTextSize() * 2.5f, notePaint);
        canvas.drawText(String.format(Locale.CHINA, "ascent:%.2f, descent:%.2f, leading:%.2f", fm.ascent, fm.descent, fm.leading), x, y + notePaint.getTextSize() * 4f, notePaint);

        notePaint.setColor(Color.parseColor("#FFD84315"));

        // fm top线
        canvas.drawLine(x - L_BIAS, y + fm.top, x + textRect.width() + L_BIAS, y + fm.top, notePaint);

        notePaint.setColor(Color.parseColor("#FF00695C"));

        // fm bottom线
        canvas.drawLine(x - L_BIAS, y + fm.bottom, x + textRect.width() + L_BIAS, y + fm.bottom, notePaint);

        notePaint.setColor(Color.parseColor("#4527A0"));

        // fm ascent线
        canvas.drawLine(x - L_BIAS, y + fm.ascent, x + textRect.width() + L_BIAS, y + fm.ascent, notePaint);

        notePaint.setColor(Color.parseColor("#0E0822"));

        // fm descent线
        canvas.drawLine(x - L_BIAS, y + fm.descent, x + textRect.width() + L_BIAS, y + fm.descent, notePaint);


    }

}
