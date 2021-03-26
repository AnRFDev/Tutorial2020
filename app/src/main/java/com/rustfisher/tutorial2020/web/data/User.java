package com.rustfisher.tutorial2020.web.data;

/**
 * 示例
 * 用来给webview传消息
 * 2020-1-5
 */
public class User {
    private int num;
    private boolean enable = false;
    private String rule;
    private int starCount;

    public User(int num) {
        this.num = num;
    }

    public User(int num, boolean enable, String rule, int starCount) {
        this.num = num;
        this.enable = enable;
        this.rule = rule;
        this.starCount = starCount;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }
}
