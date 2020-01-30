package com.rustfisher.tutorial2020.recycler.multi;

public class MultiData1 extends BaseMultiData {

    private String str1;

    public MultiData1(String str1) {
        this.str1 = str1;
    }

    public String getStr1() {
        return str1;
    }

    @Override
    public int typeCode() {
        return ItemTypeDef.Type.ONE_TEXT.getCode();
    }
}
