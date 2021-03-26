package com.rustfisher.tutorial2020.pinyin;

import android.content.Context;

import com.github.promeg.tinypinyin.android.asset.lexicons.AndroidAssetDict;

/**
 * 城市名单
 */
public class CnCityFullDict extends AndroidAssetDict {
    public CnCityFullDict(Context context) {
        super(context);
    }

    @Override
    protected String assetFileName() {
        return "cncity_fulllist.txt";
    }
}
