package com.rustfisher.tutorial2020.pinyin;

import android.content.Context;

import com.github.promeg.tinypinyin.android.asset.lexicons.AndroidAssetDict;

public class PinyinDuoYin extends AndroidAssetDict {

    public PinyinDuoYin(Context context) {
        super(context);
    }

    @Override
    protected String assetFileName() {
        return "pinyin_duoyinzi.txt";
    }
}
