package com.rustfisher.tutorial2020.correct.view;

/**
 * 单词
 */
public class Word {
    public static final int IDLE = 0;
    public static final int DEL = 10;
    public static final int INSERT_FRONT = 20;
    public static final int REPLACE = 30;

    private String originTxt;
    private String opTxt;
    private int statusCode = IDLE;
    private boolean isMark = false;
    private boolean markBefore = false; // 当前是标点符号，并且形如 ["A] 。而不是 [A,] 。
    private boolean onChosen = false;
    private boolean isQuestion = false; // 是不是带有问题的

    private int wIndex;
    private int lineNumber;// 显示时的第几行
    private float left;    // 显示时的左边
    private float right;
    private float baseline;
    private float bottom;
    private float top;

    public Word(String inOriginTxt) {
        this.originTxt = inOriginTxt;
    }

    public Word(String originTxt, boolean isMark) {
        this.originTxt = originTxt;
        this.isMark = isMark;
    }

    public Word(String originTxt, boolean isMark, boolean markBefore) {
        this.markBefore = markBefore;
        this.originTxt = originTxt;
        this.isMark = isMark;
    }


    public String getOriginTxt() {
        return originTxt;
    }

    public void setOriginTxt(String originTxt) {
        this.originTxt = originTxt;
    }

    public String getOpTxt() {
        return opTxt;
    }

    public void setOpTxt(String opTxt) {
        this.opTxt = opTxt;
    }

    public boolean isMark() {
        return isMark;
    }

    public void setMark(boolean mark) {
        isMark = mark;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public boolean isOnChosen() {
        return onChosen;
    }

    public void setOnChosen(boolean onChosen) {
        this.onChosen = onChosen;
    }

    public boolean isMarkBefore() {
        return markBefore;
    }

    public void setMarkBefore(boolean markBefore) {
        this.markBefore = markBefore;
    }

    public boolean containPoint(float x, float y) {
        return x >= left && x <= right && y <= bottom && y >= top;
    }

    public float getBaseline() {
        return baseline;
    }

    public void setBaseline(float baseline) {
        this.baseline = baseline;
    }

    public void setToDel() {
        opTxt = "";
        statusCode = DEL;
    }

    public boolean isIdle() {
        return statusCode == IDLE;
    }

    public void setToIdle() {
        statusCode = IDLE;
    }

    public void setToInsertFront() {
        statusCode = INSERT_FRONT;
    }

    public void setToReplace() {
        statusCode = REPLACE;
    }

    public boolean isQuestion() {
        return isQuestion;
    }

    public void setQuestion(boolean question) {
        isQuestion = question;
    }

    @Override
    public String toString() {
        return "[" + originTxt + "] status:" + statusCode + " ; isQue:" + isQuestion +
                "; pos{" + left + ", " + top + ", " + right + ", " + bottom + "}" + "\n";
    }

}
