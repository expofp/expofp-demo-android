package com.example.expofp;

import static android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ShareFplanView11 extends FrameLayout {

    private static WebView sharedWebView;
    private WebView webView;

    public ShareFplanView11(@NonNull Context context) {
        super(context);
    }

    public ShareFplanView11(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShareFplanView11(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ShareFplanView11(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void load(String url){

        if(sharedWebView == null){
            sharedWebView = createWebView();
        }

        WebView webView = sharedWebView;
        this.addView(webView);
        this.webView = webView;

        String oldUrl = webView.getUrl();
        if(webView.getUrl() != null){

        }
        else {
            webView.post(() -> {
                try {
                    webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                    webView.loadUrl(url);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    protected WebView createWebView(){
        WebView webView = new WebView(this.getContext());

        LayoutParams layoutParams = new LayoutParams
                (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.TOP | Gravity.RIGHT | Gravity.BOTTOM | Gravity.LEFT;

        webView.setLayoutParams(layoutParams);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW);
        //webView.getSettings().setUserAgentString("AndroidWebView");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (0 != (getContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE)) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }

        return webView;
    }

    public void destroy(){
        if (webView != null) {
            webView.setWebViewClient(null);
            webView.setWebChromeClient(null);

            //webView.removeAllViews();
            removeView(webView);

            //webView.destroy();
            webView = null;
        }
    }
}
