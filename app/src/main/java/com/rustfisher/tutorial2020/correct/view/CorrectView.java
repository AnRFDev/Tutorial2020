package com.rustfisher.tutorial2020.correct.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 文字操作
 * Created on 2020-3-28
 */
public class CorrectView extends View {
    private static final String TAG = "rustAppCorrectView";
    private int extraPaddingHor = 4; // 必须的水平内距

    private List<Word> words = new ArrayList<>();

    private int viewWid;
    private int viewHeight;
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint correctPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect rectText = new Rect(); // 用来测量文字宽高
    float lineGap = 50;   // 行间距，可调整的
    float wordGap = 10;   // 字间距
    float textSizePx = 20;
    private int originTextColor = Color.BLACK;
    private int correctTextColor = Color.parseColor("#F9A825");
    private int delLineColor = Color.parseColor("#F9A825");
    private int underLineColor = Color.parseColor("#F9A825");
    private int chosenTextBgColor = Color.GRAY;

    public CorrectView(Context context) {
        this(context, null);
    }

    public CorrectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CorrectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWid = w;
        viewHeight = h;
        Log.d(TAG, "onSizeChanged: view-size: [" + viewWid + ", " + viewHeight + "]");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (words == null || words.isEmpty()) {
            Log.e(TAG, "onDraw: input words is nothing.");
            return;
        }
        textPaint.setColor(originTextColor);
        textPaint.setTextSize(textSizePx);

        correctPaint.setStrokeWidth(5);

        final int wordTotal = words.size();
        for (int i = 0; i < wordTotal; i++) {
            Word w = words.get(i);
            if (w.isOnChosen()) {
                bgPaint.setColor(chosenTextBgColor);
                canvas.drawRect(w.getLeft(), w.getTop(), w.getRight() + 2, w.getBottom(), bgPaint);
            }

            int wordMiddleY = (int) ((w.getBottom() + w.getTop()) / 2);

            switch (w.getStatusCode()) {
                case Word.IDLE:
                    canvas.drawText(w.getOriginTxt(), w.getLeft(), w.getBaseline(), textPaint);
                    break;
                case Word.DEL:
                    canvas.drawText(w.getOriginTxt(), w.getLeft(), w.getBaseline(), textPaint);
                    correctPaint.setColor(delLineColor);
                    canvas.drawLine(w.getLeft(), wordMiddleY, w.getRight(), wordMiddleY, correctPaint);
                    break;
                case Word.INSERT_FRONT:
                    canvas.drawText(w.getOriginTxt(), w.getLeft(), w.getBaseline(), textPaint);
                    correctPaint.setColor(underLineColor);
                    correctPaint.setTextSize(textSizePx);
                    int shift = 15;
                    canvas.drawLine(w.getLeft(), w.getBottom(), w.getLeft() - shift, w.getBottom() + shift, correctPaint);
                    canvas.drawLine(w.getLeft(), w.getBottom(), w.getLeft() + shift, w.getBottom() + shift, correctPaint);

                    correctPaint.getTextBounds(w.getOpTxt(), 0, w.getOpTxt().length(), rectText);
                    canvas.drawText(w.getOpTxt(), w.getLeft() - rectText.width() / 2f, w.getBottom() + shift + rectText.height(), correctPaint);

                    break;
                case Word.REPLACE:
                    canvas.drawText(w.getOriginTxt(), w.getLeft(), w.getBaseline(), textPaint);
                    correctPaint.setColor(underLineColor);
                    correctPaint.setTextSize(textSizePx);
                    int underShift = 5;
                    canvas.drawLine(w.getLeft(), w.getBottom(), w.getRight(), w.getBottom(), correctPaint);
                    correctPaint.getTextBounds(w.getOpTxt(), 0, w.getOpTxt().length(), rectText);
                    canvas.drawText(w.getOpTxt(), (w.getRight() + w.getLeft()) / 2 - rectText.width() / 2f, w.getBottom() + underShift + rectText.height(), correctPaint);
                    break;
            }
        }
    }

    public void onClickPos(float x, float y) {
        if (words == null || words.isEmpty()) {
            Log.d(TAG, "onClickPos: 没有数据，不处理点击");
            return;
        }
        Word click = null;
        for (Word w : words) {
            boolean chosen = w.containPoint(x, y);
            w.setOnChosen(chosen);
            if (chosen) {
                click = w;
            }
        }
        invalidate();
        tellOnClick(click);
    }

    private OnClickWord onClickWord;

    public void setOnClickWord(OnClickWord onClickWord) {
        this.onClickWord = onClickWord;
    }

    public interface OnClickWord {
        /**
         * @param w 被点击到的单词，可能为null
         */
        void onClick(Word w);
    }

    private void tellOnClick(Word w) {
        if (onClickWord != null) {
            onClickWord.onClick(w);
        }
    }

    public void setWords(List<Word> words) {
        this.words = words;
        int lineNumber = 0;
        int textHeight = 0;

        // 计算需要的宽高
        if (words != null && words.size() > 0) {
            float cx = extraPaddingHor, cy = lineGap; // 绘制的光标
            textPaint.setTextSize(textSizePx);
            final int wordTotal = words.size();

            textPaint.getTextBounds("W", 0, 1, rectText); // 获取文本的宽高
            extraPaddingHor = rectText.width();

            for (int i = 0; i < wordTotal; i++) {
                Word w = words.get(i);
                w.setLineNumber(lineNumber);
                textPaint.getTextBounds(w.getOriginTxt(), 0, w.getOriginTxt().length(), rectText); // 获取文本的宽高
                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                textHeight = (int) (rectText.height());

                if (cx + rectText.width() > viewWid - extraPaddingHor) {
                    cx = 0; // 换行
                    cy += lineGap;
                    lineNumber++;
                }

                w.setLeft(cx);
                w.setBottom(cy + fontMetrics.bottom);
                w.setBaseline(cy);
                w.setRight(cx + rectText.width());
                w.setTop(cy + fontMetrics.top);

                if (i < wordTotal - 1) {
                    Word n = words.get(i + 1);
                    if (n.isMark()) {
                        if (n.isMarkBefore()) {
                            cx += (rectText.width() + wordGap);
                        } else {
                            cx += (rectText.width() + wordGap / 3);
                        }
                    } else {
                        if (w.isMarkBefore()) {
                            cx += (rectText.width() + wordGap / 3);
                        } else {
                            cx += (rectText.width() + wordGap);
                        }
                    }
                }
            }
        }

        Log.d(TAG, "setWords: lineNum: " + lineNumber);

        setMinimumHeight((int) ((lineGap) * (lineNumber + 2)) + textHeight);
        invalidate();
    }

    public void setTextSizePx(float textSizePx) {
        this.textSizePx = textSizePx;
    }

    public float getLineGap() {
        return lineGap;
    }

    public void setLineGap(float lineGap) {
        this.lineGap = lineGap;
    }

    public float getWordGap() {
        return wordGap;
    }

    public void setWordGap(float wordGap) {
        this.wordGap = wordGap;
    }
}
