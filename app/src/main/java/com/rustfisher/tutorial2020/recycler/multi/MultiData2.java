package com.rustfisher.tutorial2020.recycler.multi;

public class MultiData2 extends BaseMultiData {

    private String str1;
    private String str2;

    public MultiData2(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    public String getStr1() {
        return str1;
    }

    public String getStr2() {
        return str2;
    }

    @Override
    public int typeCode() {
        return ItemTypeDef.Type.TWO_TEXT.getCode();
    }
}
