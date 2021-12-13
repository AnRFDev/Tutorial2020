package com.rustfisher.tutorial2020.pinyin;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.promeg.pinyinhelper.Pinyin;
import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;

public class TinyPinyinDemo extends AbsActivity {

    private TextView mOutputTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tinypinyin_demo);
        mOutputTv = findViewById(R.id.output_tv);

        // 添加自定义词典
//        Pinyin.init(Pinyin.newConfig()
//                .with(new PinyinMapDict() {
//                    @Override
//                    public Map<String, String[]> mapping() {
//                        HashMap<String, String[]> map = new HashMap<>();
//                        map.put("重庆", new String[]{"CHONG", "QING"});
//                        map.put("嗯", new String[]{"EN"});
//                        map.put("哦", new String[]{"O"});
//                        return map;
//                    }
//                }));

        Pinyin.init(Pinyin.newConfig()
                .with(new CnCityFullDict(getApplicationContext()))
                .with(new PinyinDuoYin(getApplicationContext())));


        StringBuilder outputSb = new StringBuilder();

        for (String word : PinyinUtil.chWords) {
//            outputSb.append("[tiny] ").append(word).append(" -> ").append(Pinyin.toPinyin(word, " ")).append("\n");
            outputSb.append("[tiny] ").append(word).append(" -> ").append(getFirstSpell(word)).append("\n");
        }
        Log.d(TAG, outputSb.toString());
        mOutputTv.setText(outputSb.toString());

//
//        for (String word : PinyinUtil.chWords) {
//            Log.d(TAG, "[4j] " + word + " -> " + Pinyin4jUtil.getPingYin(word));
//        }


    }

    public static String getFirstSpell(String chinese) {
        String p1 = Pinyin.toPinyin(chinese, " ");
        if (TextUtils.isEmpty(p1)) {
            return "";
        }
        p1 = p1.trim();
        String[] ps = p1.split(" ");

        StringBuilder resSb = new StringBuilder();
        for (String p : ps) {
            if (!TextUtils.isEmpty(p)) {
                resSb.append(p.substring(0, 1));
            }
        }
        return resSb.toString();
    }

}
