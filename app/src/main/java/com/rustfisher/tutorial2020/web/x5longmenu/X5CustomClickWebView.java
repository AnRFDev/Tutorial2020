package com.rustfisher.tutorial2020.web.x5longmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;

import com.rustfisher.tutorial2020.web.longmenu.WebChooseActionListener;
import com.tencent.smtt.sdk.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * 能自定义长按菜单的
 * 2020-11-2
 */
public class X5CustomClickWebView extends WebView {
    private ActionMode mCurActionMode;
    private List<String> mMenuItemNameList = new ArrayList<>();
    private WebChooseActionListener mWebChooseActionListener;

    public X5CustomClickWebView(Context context) {
        super(context);
    }

    public X5CustomClickWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public X5CustomClickWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public ActionMode startActionMode(ActionMode.Callback callback) {
        ActionMode actionMode = super.startActionMode(callback);
        return resolveActionMode(actionMode);
    }

    @Override
    public ActionMode startActionMode(ActionMode.Callback callback, int type) {
        ActionMode actionMode = super.startActionMode(callback, type);
        return resolveActionMode(actionMode);
    }

    private ActionMode resolveActionMode(ActionMode actionMode) {
        if (actionMode != null) {
            final Menu menu = actionMode.getMenu();
            mCurActionMode = actionMode;
            menu.clear();
            for (int i = 0; i < mMenuItemNameList.size(); i++) {
                menu.add(mMenuItemNameList.get(i));
            }
            for (int i = 0; i < menu.size(); i++) {
                MenuItem menuItem = menu.getItem(i);
                menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        exeSelectTextJs((String) item.getTitle());
                        releaseAction();
                        return true;
                    }
                });
            }
        }
        mCurActionMode = actionMode;
        return actionMode;
    }

    private void releaseAction() {
        if (mCurActionMode != null) {
            mCurActionMode.finish();
            mCurActionMode = null;
        }
    }

    private void exeSelectTextJs(String title) {
        String js = "(function getSelectedText() {" +
                "var txt;" +
                "var title = \"" + title + "\";" +
                "if (window.getSelection) {" +
                "txt = window.getSelection().toString();" +
                "} else if (window.document.getSelection) {" +
                "txt = window.document.getSelection().toString();" +
                "} else if (window.document.selection) {" +
                "txt = window.document.selection.createRange().text;" +
                "}" +
                "RFJSInterface.chooseAction(txt,title);" +
                "})()";
        evaluateJavascript("javascript:" + js, null);
    }

    /**
     * 在外部调用
     */
    public void linkJSInterface() {
        addJavascriptInterface(new CustomJsInterface(this), "RFJSInterface");
    }

    /**
     * @param actionList 弹出菜单
     */
    public void setActionList(List<String> actionList) {
        mMenuItemNameList = actionList;
    }

    public void setActionSelectListener(WebChooseActionListener webChooseActionListener) {
        this.mWebChooseActionListener = webChooseActionListener;
    }

    /**
     * 隐藏消失Action
     */
    public void dismissAction() {
        releaseAction();
    }

    /**
     * js选中的回掉接口
     */
    private class CustomJsInterface {

        X5CustomClickWebView webView;

        CustomJsInterface(X5CustomClickWebView c) {
            webView = c;
        }

        /**
         * @param value 选中的文字
         * @param title 菜单标题
         */
        @JavascriptInterface
        public void chooseAction(final String value, final String title) {
            if (mWebChooseActionListener != null) {
                mWebChooseActionListener.onChosen(title, value);
            }
        }
    }
}