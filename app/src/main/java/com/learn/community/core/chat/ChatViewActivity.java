package com.learn.community.core.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.learn.community.R;

public class ChatViewActivity extends AppCompatActivity {

    private WebView mWvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);
        mWvMain = (WebView) findViewById(R.id.wv);
        //mWvMain.setWebViewClient(new MyWebViewClient());
        //mWvMain.setWebChromeClient(new MyWebChromeClient());
        //mWvMain.setWebViewClient(new WebViewClient());
        mWvMain.getSettings().setJavaScriptEnabled(true);
        //mWvMain.loadUrl("http://www.baidu.com");
        //mWvMain.loadUrl("http://vtu509wb13.lwy12.store/index.php?action=page.index");
        mWvMain.loadUrl("http://madpdy.top/index.php?action=page.index");
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (request.getUrl().toString().startsWith("http:")||request.getUrl().toString().startsWith("https:")||request.getUrl().toString().startsWith("ftp")){
                view.loadUrl(request.getUrl().toString());
                return true;
            }
            else {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(request.getUrl().toString()));
                startActivity(intent);
                return true;
            }
            //return false;
            //view.loadUrl(request.getUrl().toString());
            //return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("WebView","onPageStarted");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("WebView","onPageFinished");
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWvMain.canGoBack()){
            mWvMain.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}