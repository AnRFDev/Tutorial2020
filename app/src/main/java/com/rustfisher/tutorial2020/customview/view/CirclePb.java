package com.rustfisher.tutorial2020.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.rustfisher.tutorial2020.R;


public class CirclePb extends View {
    private int vh, vw;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int underColor;
    private int pbColor;
    private float paintWid;

    private int max = 100;
    private int progress = 0;

    public CirclePb(Context context) {
        this(context, null);
    }

    public CirclePb(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePb(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CirclePb);
        underColor = typedArray.getColor(R.styleable.CirclePb_under_color, Color.GRAY);
        pbColor = typedArray.getColor(R.styleable.CirclePb_pb_color, Color.YELLOW);
        paintWid = typedArray.getDimensionPixelSize(R.styleable.CirclePb_stroke_wid, 20);
        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        vh = getHeight();
        vw = getWidth();
    }

    Path pbPath = new Path();
    RectF pbRectF = null;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(underColor);
        paint.setStrokeWidth(paintWid);

        float cx = vw / 2f;
        float cy = vh / 2f;
        float r = cx - paintWid / 2f;

        canvas.drawCircle(cx, cy, r, paint);

        float percent = progress / (max * 1f);
        if (percent > 0) {
            percent = Math.min(1, percent);
            pbPath.reset();
            paint.setStrokeWidth(paintWid);
            paint.setColor(pbColor);
            if (pbRectF == null) {
                pbRectF = new RectF(paintWid / 2, paintWid / 2, vw - paintWid / 2, vh - paintWid / 2);
            }
            pbPath.addArc(pbRectF, -90, 360 * percent);

            canvas.drawPath(pbPath, paint);
//            canvas.drawArc(pbRectF, -90, 360 * percent, false, paint);
        }

    }

    public void setProgressAndMax(int progress, int max) {
        this.progress = progress;
        this.max = max;
        invalidate();
    }

    public void setPbColor(int pbColor) {
        this.pbColor = pbColor;
        invalidate();
    }

    public void setUnderColor(int underColor) {
        this.underColor = underColor;
        invalidate();
    }
}
