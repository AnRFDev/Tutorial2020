package com.rustfisher.tutorial2020.web.longmenu;

/**
 * webView的监听器
 * 2020-11-2
 */
public interface WebChooseActionListener {

    /**
     * @param itemTitle 选项的标题
     * @param txt       选中的文字
     */
    void onChosen(String itemTitle, String txt);
}
