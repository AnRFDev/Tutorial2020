package com.rustfisher.tutorial2020.web;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.web.longmenu.WebChooseActionListener;
import com.rustfisher.tutorial2020.web.x5longmenu.X5CustomClickWebView;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.ISelectionInterface;
import com.tencent.smtt.sdk.WebView;

import java.util.Arrays;

/**
 * 响应点击，长按
 * 2020-11-7
 */
public class WebViewX5ClickAct extends AbsActivity {
    private X5CustomClickWebView mX5WebView1;
    private WebView mX5WebView2;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private static final String mH1 = "<p>Tell me the <font color=\"red\">color</font></p>"
            + "<span style=\"background-color: red\">这是中文解释。</span>"
            + "<p>Tell me the <text style=\"color: blue; font-weight: bold;\">color</text>.</p>"
            + "<div><p>定位功能内核默认开启，提供关闭接口，当用户请求定位时会回调对应client的授权接口<p></div>";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wv_x5_click_act1);

//        mX5WebView1 = findViewById(R.id.web1);
        mX5WebView2 = findViewById(R.id.web2);

//        initWeb1();
        initWeb2();
    }

    private void initWeb1() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            mX5WebView1.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mX5WebView1.getSettings().setJavaScriptEnabled(true);
        mX5WebView1.setActionList(Arrays.asList("复制", "翻译"));
        mX5WebView1.getSettings().setBlockNetworkImage(false);
        mX5WebView1.linkJSInterface();

        mX5WebView1.setActionSelectListener(new WebChooseActionListener() {
            @Override
            public void onChosen(String title, String selectText) {
                Toast.makeText(getApplicationContext(), "(" + title + ") -> " + selectText, Toast.LENGTH_SHORT).show();
            }
        });
        mX5WebView1.loadDataWithBaseURL(null, mH1, "text/html", "UTF-8", null);
    }

    private void initWeb2() {
        mX5WebView2.getSettings().setJavaScriptEnabled(true);

//        mX5WebView2.setWebViewClient(new WebViewClient());
//        ProxyWebViewClientExtension extension = new ProxyWebViewClientExtension() {
//            @Override
//            public boolean onShowLongClickPopupMenu() {
//                Log.d(TAG, "extension onShowLongClickPopupMenu: ");
//                return true;
//            }
//        };
//        mX5WebView2.setWebViewClientExtension(extension);
        IX5WebViewExtension x5WebViewExtension = mX5WebView2.getX5WebViewExtension();
        if (x5WebViewExtension != null) {
            x5WebViewExtension.enterSelectionMode(true);
            x5WebViewExtension.setWebViewClientExtension(new ProxyWebViewClientExtensionX(mX5WebView2));
            x5WebViewExtension.setSelectListener(new ISelectionInterfaceX(mX5WebView2));
        } else {
            Log.e(TAG, "x5还没有准备好 - x5WebViewExtension is null.");
        }

//        mX5WebView2.loadUrl("http://soft.imtt.qq.com/browser/tes/feedback.html");
        mX5WebView2.loadDataWithBaseURL(null, mH1, "text/html", "UTF-8", null);
    }

    private class ProxyWebViewClientExtensionX extends ProxyWebViewClientExtension {
        private WebView webView;
        private Handler handler = new Handler(Looper.getMainLooper());

        public ProxyWebViewClientExtensionX(WebView webView) {
            this.webView = webView;
        }

        @Override
        public boolean onShowLongClickPopupMenu() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    webView.getX5WebViewExtension().enterSelectionMode(false);
                }
            }, 30);
            return true;
        }
    }

    private class ISelectionInterfaceX implements ISelectionInterface {
        boolean isInActionMode = false;
        ActionMode actionMode;
        private WebView webView;
        View actionView;
        private Handler handler = new Handler(Looper.getMainLooper());

        public ISelectionInterfaceX(WebView webView) {
            this.webView = webView;
        }

        @Override
        public void onSelectionChange(Rect rect, Rect rect1, int i, int i1, short i2) {
            Log.d(TAG, "onSelectionChange: ");
        }

        @Override
        public void onSelectionBegin(Rect rect, Rect rect1, int i, int i1, short i2) {
            Log.d(TAG, "onSelectionBegin: ");
        }

        @Override
        public void onSelectionBeginFailed(int i, int i1) {
            Log.d(TAG, "onSelectionBeginFailed: ");
        }

        @Override
        public void onSelectionDone(Rect rect, boolean b) {
            Log.d(TAG, "onSelectionDone: ");
        }

        @Override
        public void hideSelectionView() {
            System.out.println();
            if (actionView != null) {
                webView.removeViewInLayout(actionView);
                actionView = null;
            }
            if (actionMode != null) {
                actionMode.finish();
                actionMode = null;
            }
        }

        @Override
        public void onSelectCancel() {
            Log.d(TAG, "onSelectCancel: ");
        }

        @Override
        public void updateHelperWidget(final Rect rect, final Rect rect1) {
            System.out.println();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    final ActionMode.Callback callback = new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            mode.getMenuInflater().inflate(R.menu.web_view_x5_menu1, menu);
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                            return false;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode mode,
                                                           MenuItem item) {
                            final IX5WebViewExtension webViewExtension = webView.getX5WebViewExtension();
                            String selectionText = "";
                            if (webViewExtension != null) {
                                selectionText = webViewExtension.getSelectionText();
                            }
                            boolean leaveSelection = true;
                            switch (item.getItemId()) {
                                case R.id.copy:
                                    Toast.makeText(WebViewX5ClickAct.this, selectionText, Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.action1:
                                    Toast.makeText(WebViewX5ClickAct.this, "(action1) " + selectionText, Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            final boolean finalLeaveSelection = leaveSelection;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (finalLeaveSelection && webViewExtension != null) {
                                        webViewExtension.leaveSelectionMode();
                                    }
                                }
                            }, 30);
                            return true;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode mode) {
                            isInActionMode = false;
                        }
                    };

                    Context context = webView.getContext();

                    // 生成actionView，并通过它启动ActionMode，解决定位问题
                    // actionView的大小和位置，大致和Selection相同，直接将其add到WebView中
                    if (actionView != null) {
                        webView.removeViewInLayout(actionView);
                    }

                    actionView = new View(context);
                    actionView.setBackgroundColor(0x33FF00FF);// 方便调试
                    int width = rect1.right - rect.left;
                    int height = rect1.bottom - rect.top;
                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width <= 0 ? 10 : width, height <= 0 ? 10 : height);
                    lp.leftMargin = rect.left;
                    lp.topMargin = rect.top;
                    webView.addView(actionView, lp);

                    // 需要延迟startActionMode，给布局actionView的时间
                    actionView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                actionMode = actionView.startActionMode(callback, ActionMode.TYPE_FLOATING);
                            } else {
                                actionMode = actionView.startActionMode(callback);
                            }
                        }
                    });
                }
            }, 0);
        }

        @Override
        public void setText(String s, boolean b) {
            Log.d(TAG, "setText: ");
        }

        @Override
        public String getText() {
            Log.d(TAG, "getText: ");
            return null;
        }

        @Override
        public void onRetrieveFingerSearchContextResponse(String s, String s1, int i) {
            Log.d(TAG, "onRetrieveFingerSearchContextResponse: ");
        }
    }
}
