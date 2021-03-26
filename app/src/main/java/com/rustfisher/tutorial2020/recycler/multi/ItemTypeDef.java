package com.rustfisher.tutorial2020.recycler.multi;

/**
 * 选项的类型定义
 */
public class ItemTypeDef {

    public static final int ITEM_TYPE_ONE_TEXT = 1;
    public static final int ITEM_TYPE_2_TEXT = 2;

    // item的类型
    public enum Type {
        ONE_TEXT(ITEM_TYPE_ONE_TEXT),
        TWO_TEXT(ITEM_TYPE_2_TEXT);
        int code;

        Type(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static Type getItemTypeByCode(int code) {
            switch (code) {
                case ITEM_TYPE_ONE_TEXT:
                    return Type.ONE_TEXT;
                case ITEM_TYPE_2_TEXT:
                    return Type.TWO_TEXT;
            }
            return Type.ONE_TEXT;
        }
    }

}