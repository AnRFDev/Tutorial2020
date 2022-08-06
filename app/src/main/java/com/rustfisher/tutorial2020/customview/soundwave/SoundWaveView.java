package com.rustfisher.tutorial2020.customview.soundwave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 确定宽高
 * 2. 确定可以显示多少个刻度
 * 3. 确定刻度最大高度
 *
 * @author an.rustfisher.com
 */
public class SoundWaveView extends View {
    private static final String TAG = "rustAppSoundWaveView";

    /**
     * 单纯播放 展示 无交互
     */
    public static final int MODE_PLAY = 1;

    /**
     * 允许左右拖动
     */
    public static final int MODE_CAN_DRAG = 2;

    private int mode = MODE_PLAY; // 1 播放
    private List<Float> dataList = new ArrayList<>(100);
    private float showMaxData = 300f; // 能显示的最大数据
    private int midIndex = 0;   // 在中间显示的数据的下标
    private float barWidDp = 2f;
    private float barWidPx = 3f;
    private float barGapPx = barWidPx / 2;
    private int barCount = 1;       // 当前宽度能绘制多少个柱子
    private int viewWid = 1000;     // px
    private int viewHeight = 100;   // px

    private final Paint paint = new Paint();
    private int leftColor = Color.GREEN;
    private int rightColor = Color.LTGRAY;
    private int middleLineColor = Color.parseColor("#55000000");

    private float downX = 0; // getX
    private int downOldMidIndex = 0;

    public interface OnEvent {
        void onMoveEnd(); // 停止拖动了
    }

    private OnEvent onEventListener;

    public SoundWaveView(Context context) {
        this(context, null);
    }

    public SoundWaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SoundWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWid = w;
        viewHeight = h;
        calBarPara();
        Log.d(TAG, "onSizeChanged: " + w + ", " + h);
        Log.d(TAG, "onSizeChanged: barWidPx: " + barWidPx);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dataList == null || dataList.isEmpty()) {
            // draw nothing
            return;
        }
        float x0 = viewWid / 2.0f;

        // 绘制数据
//        if (mode == MODE_PLAY) {
        if (midIndex > 0) {
            x0 = x0 - (barGapPx + barWidPx) * midIndex; // 可能是负数
//                x0 = Math.max(x0, (barGapPx + barWidPx / 2f));
        }
//            Log.d(TAG, "onDraw: x0: " + x0);
        // 绘制中线右边的
        for (int i = 0; i < dataList.size(); i++) {
            float d = dataList.get(i);
            float x = x0 + (barWidPx + barGapPx) * i;
//                Log.d(TAG, "onDraw: i:" + i + ", x:" + x);
            if (x < 0) {
                continue;
            }
            if (x > viewWid) {
                break;
            }
            if (i <= midIndex) {
                paint.setColor(leftColor);
            } else {
                paint.setColor(rightColor);
            }
            float bh = (d / showMaxData) * viewHeight;
            bh = Math.max(bh, 4); // 最小也要一点高度
            float bhGap = (viewHeight - bh) / 2f;
            canvas.drawLine(x, bhGap, x, viewHeight - bhGap, paint);
        }
//        }

        paint.setColor(middleLineColor);
        canvas.drawLine(viewWid / 2f, 0, viewWid / 2f, viewHeight, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mode == MODE_CAN_DRAG) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    float dx = (downX - event.getX()); // 不要那么灵敏
                    float movePercent = dx / viewWid;
                    int dIndex = (int) (movePercent * barCount);
                    int targetMidIndex = downOldMidIndex + dIndex;
                    targetMidIndex = Math.max(0, targetMidIndex);
                    targetMidIndex = Math.min(targetMidIndex, dataList.size() - 1);
                    setMidIndex(targetMidIndex);
                    Log.d(TAG, "onTouchEvent-MOVE; dx: " + dx + ", dIndex: " + dIndex + "; targetMidIndex: " + targetMidIndex);
                    break;
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downOldMidIndex = midIndex;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    downOldMidIndex = midIndex;
                    tellOnMoveEnd();
                    break;
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public int getMidIndex() {
        return midIndex;
    }

    public void setOnEventListener(OnEvent onEventListener) {
        this.onEventListener = onEventListener;
    }

    private void calBarPara() {
        barWidPx = dp2Px(barWidDp);
        barGapPx = barWidPx * 0.75f;
        barCount = (int) ((viewWid - barGapPx) / (barWidPx + barGapPx));
        paint.setStrokeWidth(barWidPx);
        Log.d(TAG, "calBarPara: barCount: " + barCount);
    }

    public void setDataList(List<Float> input) {
        dataList = new ArrayList<>(input);
        midIndex = 0;
        invalidate();
    }

    public void setMidIndex(int midIndex) {
        this.midIndex = midIndex;
        invalidate();
    }

    // 设置当前播放进度
    public void setPlayPercent(float percent) {

        invalidate();
    }

    public void setShowMaxData(float showMaxData) {
        this.showMaxData = showMaxData;
    }

    public float getShowMaxData() {
        return showMaxData;
    }

    // 不停地插入数据
    public void addDataEnd(float f) {
        dataList.add(f);
        midIndex = dataList.size() - 1;
        invalidate();
    }

    public void setLeftColor(int leftColor) {
        this.leftColor = leftColor;
    }

    public void setRightColor(int rightColor) {
        this.rightColor = rightColor;
    }

    private float dp2Px(float dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        int mark = dp > 0 ? 1 : -1;
        return dp * density * mark;
    }

    private void tellOnMoveEnd() {
        if (onEventListener != null) {
            onEventListener.onMoveEnd();
        }
    }
}
