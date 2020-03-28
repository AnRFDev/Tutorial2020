package com.rustfisher.tutorial2020.correct;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.correct.view.CorrectPopupWindow;
import com.rustfisher.tutorial2020.correct.view.CorrectView;
import com.rustfisher.tutorial2020.correct.view.DelAnsFloatingWindow;
import com.rustfisher.tutorial2020.correct.view.InputTextPopupWindow;
import com.rustfisher.tutorial2020.correct.view.OnChooseCmd;
import com.rustfisher.tutorial2020.correct.view.Word;
import com.rustfisher.tutorial2020.databinding.ActCorrectSampleBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文字操作
 */
public class CorrectSampleAct extends AbsActivity {

    private String mArticle = "There are friends' books. It's good. 1st, 2nd, 3rd, 4th. One day, the students of \"Class 6\" were reading in the classroom. " +
            "Mrs. Brown came in and told the whole class they would have to change the <<class>> the next week. " +
            "All of the students disagreed <<to>> Mrs. Brown, so they shouted when Mrs. Brown said it was because a new student would <<join>> the class. “Why must we move to another classroom just because someone new is coming?” One of them asked <<angrily>>. Mrs. Brown stood there without saying anything. After a few minutes, she told them to be <<kind>> to their new classmate and then she left.";

    private CorrectPopupWindow correctPopupWindow;
    private DelAnsFloatingWindow delAnsFloatingWindow;
    private InputTextPopupWindow inputTextPopupWindow;

    private Word mCurrentWord = null;

    private float downX;
    private float downY;
    private float rawDownX;
    private float rawDownY;

    private ActCorrectSampleBinding binding;
    private CorrectView mCorrectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.act_correct_sample);

        final List<Word> words = genWordList(mArticle);
//        Log.d(TAG, "onCreate: " + words);
        Log.d(TAG, "onCreate: 带有问题的单词 " + queWordList);
        correctPopupWindow = new CorrectPopupWindow(this);
        delAnsFloatingWindow = new DelAnsFloatingWindow(this);
        inputTextPopupWindow = new InputTextPopupWindow(this);

        mCorrectView = binding.cv;
        binding.cv.setTextSizePx(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        binding.cv.setLineGap(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15 * 3, getResources().getDisplayMetrics()));
        binding.cv.setWordGap(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 7, getResources().getDisplayMetrics()));
        binding.cv.post(new Runnable() {
            @Override
            public void run() {
                binding.cv.setWords(words);
            }
        });
        binding.cv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                downX = event.getX();
                downY = event.getY();
                rawDownX = event.getRawX();
                rawDownY = event.getRawY();
                return false;
            }
        });

        binding.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.cv.onClickPos(downX, downY);
            }
        });

        binding.cv.setOnClickWord(new CorrectView.OnClickWord() {
            @Override
            public void onClick(Word w) {
                if (mCurrentWord != null) {
                    if (w == null) {
                        mCurrentWord.setOnChosen(false);
                    }
                    if (inputTextPopupWindow.isShowing()) {
                        String input = inputTextPopupWindow.getCurrentInputText();
                        if (TextUtils.isEmpty(input)) {
                            mCurrentWord.setToIdle();
                        } else {
                            mCurrentWord.setOpTxt(input);
                            mCurrentWord.setStatusCode(inputTextPopupWindow.getWordStatusCode());
                        }
                        mCorrectView.invalidate();
                    }
                }

                mCurrentWord = w;
                if (w != null) {
//                    Log.d(TAG, "onClick: " + w);
                    if (correctPopupWindow.isShowing()) {
                        correctPopupWindow.dismiss();
                    }
                    if (inputTextPopupWindow.isShowing()) {
                        inputTextPopupWindow.dismiss();
                    }
                    if (w.isIdle()) {
                        correctPopupWindow.show(mCorrectView, (int) w.getLeft(), (int) (rawDownY - 2.5 * (w.getBottom() - w.getTop())));
                    } else {
                        delAnsFloatingWindow.show(mCorrectView, (int) w.getLeft(), (int) (rawDownY - 2.5 * (w.getBottom() - w.getTop())));
                    }
                } else {
                    correctPopupWindow.dismiss();
                    inputTextPopupWindow.dismiss();
                    delAnsFloatingWindow.dismiss();
                }
            }
        });

        correctPopupWindow.setOnChooseCmd(onChooseCmd);
        delAnsFloatingWindow.setOnChooseCmd(onChooseCmd);
        inputTextPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                String input = inputTextPopupWindow.getCurrentInputText();
                if (mCurrentWord != null) {
                    mCurrentWord.setOnChosen(false);
                    mCurrentWord.setOpTxt(input);
                    if (TextUtils.isEmpty(input)) {
                        mCurrentWord.setToIdle();
                    } else {
                        mCurrentWord.setStatusCode(inputTextPopupWindow.getWordStatusCode());
                    }
                    mCorrectView.invalidate();
                }
            }
        });

        binding.getRoot().getViewTreeObserver().addOnScrollChangedListener(mOnScrollChangedListener);
    }

    @Override
    protected void onDestroy() {
        binding.getRoot().getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
        super.onDestroy();
    }

    private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {
        @Override
        public void onScrollChanged() {
            if (mCurrentWord != null) {
                mCurrentWord.setOnChosen(false);
                mCorrectView.invalidate();
            }
            inputTextPopupWindow.dismiss(); // 界面滑动要去掉悬浮窗
            correctPopupWindow.dismiss();
            delAnsFloatingWindow.dismiss();
        }
    };

    private OnChooseCmd onChooseCmd = new OnChooseCmd() {
        @Override
        public void insertBefore() {
            Log.d(TAG, "insertBefore: 插入");
            correctPopupWindow.dismiss();
            if (mCurrentWord != null) {
                inputTextPopupWindow.setNote("在" + mCurrentWord.getOriginTxt() + "之前插入");
                inputTextPopupWindow.setOpText(mCurrentWord.getOpTxt());
                inputTextPopupWindow.show(Word.INSERT_FRONT, mCorrectView, 0, (int) (rawDownY - 2.5 * (mCurrentWord.getBottom() - mCurrentWord.getTop())));
            }
        }

        @Override
        public void replace() {
            Log.d(TAG, "replace: 替换");
            correctPopupWindow.dismiss();
            if (mCurrentWord != null) {
                inputTextPopupWindow.setNote("替换" + mCurrentWord.getOriginTxt());
                inputTextPopupWindow.setOpText(mCurrentWord.getOpTxt());
                inputTextPopupWindow.show(Word.REPLACE, mCorrectView, 0, (int) (rawDownY - 2.5 * (mCurrentWord.getBottom() - mCurrentWord.getTop())));
            }
        }

        @Override
        public void del() {
            Log.d(TAG, "del: 设置为删除");
            correctPopupWindow.dismiss();
            if (mCurrentWord != null) {
                mCurrentWord.setOpTxt("");
                mCurrentWord.setOnChosen(false);
                mCurrentWord.setToDel();
                mCorrectView.invalidate();
            }
        }

        @Override
        public void removeAns() {
            delAnsFloatingWindow.dismiss();
            if (mCurrentWord != null) {
                mCurrentWord.setOpTxt("");
                mCurrentWord.setToIdle();
                mCurrentWord.setOnChosen(false);
                mCorrectView.invalidate();
            }
        }
    };

    private static final String QUE_WORD_REG = ".*<<.*?>>.*";
    private static final Pattern QUE_WORD_PATTERN = Pattern.compile(QUE_WORD_REG);
    private static final Pattern QUE_WORD_CONTENT_PATTERN = Pattern.compile("<<(.*?)>>");

    private List<Word> queWordList = new ArrayList<>();

    private List<Word> genWordList(String str) {
        List<Word> ret = new ArrayList<>();
        for (String s : str.split(" ")) {
            if (TextUtils.isEmpty(s)) {
                continue;
            }
            if (s.matches(QUE_WORD_REG)) {
                dealQueWord(ret, s);
                continue;
            }

            String[] parts = splitText(s);
            String leftMark = parts[0];
            String middleText = parts[1];
            String endMark = parts[2];
            Word word = null;
            boolean startWithMark = !TextUtils.isEmpty(leftMark);
            boolean endWithMark = !TextUtils.isEmpty(endMark);
            Log.d(TAG, "genWordList: left: " + leftMark + ", m: " + middleText + ", end: " + endMark);

            if (startWithMark && endWithMark) { // 两边都有符号
                ret.add(new Word(leftMark, true, true));
                word = new Word(middleText);
                ret.add(word);
                ret.add(new Word(endMark, true));
            } else if (endWithMark) { // 只有右边有符号
                word = new Word(middleText);
                ret.add(word);
                ret.add(new Word(endMark, true));
            } else if (startWithMark) { // 左边有符号
                ret.add(new Word(leftMark, true, true));
                if (!TextUtils.isEmpty(middleText)) {
                    word = new Word(middleText);
                    ret.add(word);
                }
            } else {
                word = new Word(s);
                ret.add(word);
            }
            if (word != null && word.isQuestion()) {
                queWordList.add(word);
            }
        }
        return ret;
    }

    private void dealQueWord(List<Word> ret, String s) {
        Matcher matcher = QUE_WORD_PATTERN.matcher(s);
        while (matcher.find()) {
            String hasMarkTxt = matcher.group(); // 带尖括号的
//                    Log.d(TAG, "genWordList: " + hasMarkTxt);
            Matcher m2 = QUE_WORD_CONTENT_PATTERN.matcher(hasMarkTxt);
            String txt = null; // 不带尖括号的

            while (m2.find()) {
                txt = m2.group(1);
            }

            int index = s.indexOf(hasMarkTxt);
            if (index >= 0) {
                String left = s.substring(0, index);
                if (!TextUtils.isEmpty(left)) {
                    ret.add(new Word(left, true, true));
                }
            }
            if (!TextUtils.isEmpty(txt)) {
                Word queWord = new Word(txt);
                queWord.setQuestion(true);
                ret.add(queWord);
                queWordList.add(queWord);
            }
            if (hasMarkTxt.length() + index < s.length()) {
                ret.add(new Word(s.substring(hasMarkTxt.length() + index), true));
            }
        }
    }

    private static final String MARK_REG = "[`~!@#$%^&*()+=|{}':;\",\\\\.<>/?！￥…（）—【】‘；：”“’。，、？]";
    private static Pattern GET_EN_PATTERN = Pattern.compile("[^\\w]*([0-9a-zA-Z'\"]+)[^\\w]*");

    /**
     * @param text 把它拆分成3个部分
     * @return 字符串数组，分别是字母前面的符号，单词，字母后面的符号
     */
    private static String[] splitText(String text) {
        String[] res = new String[3];
        String en = null;
        Matcher matcher = GET_EN_PATTERN.matcher(text);
        while (matcher.find()) {
            en = matcher.group(1);
            res[1] = en;
        }

        if (en != null && en.length() > 0) {
            int markLeftEnd = text.indexOf(en);
            if (markLeftEnd > 0) {
                res[0] = text.substring(0, markLeftEnd);
            }
            if (markLeftEnd + en.length() < text.length()) {
                res[2] = text.substring(markLeftEnd + en.length());
            }
        } else {
            res[0] = text;
        }
        return res;
    }

}
