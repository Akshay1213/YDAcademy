package com.example.user.ydacademy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Activity_twitter extends AppCompatActivity {

    @InjectView(R.id.webView)
    WebView webView1;
    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        ButterKnife.inject(this);
        loading = new ProgressDialog(Activity_twitter.this);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                loading.setMessage("Loading");
                loading.show();
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                loading.dismiss();

                String webUrl = webView1.getUrl();

            }


        });
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.loadUrl("https://www.pinterest.com/xoxytech/");
        Log.d("PDF","https://www.pinterest.com/xoxytech/");
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(Activity_twitter.this,MainActivity.class);
        finish();
        startActivity(intent);

    }
}

