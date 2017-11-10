package com.example.user.ydacademy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ListQuestions extends AppCompatActivity {
    @InjectView(R.id.webView)
    WebView webView1;
    UrlRequest urlRequest;
    SharedPreferences sp;
    String urlPDF = "https://docs.google.com/gview?embedded=true&url=http://www.stafforini.com/txt/Covey%20-%20The%207%20habits%20of%20highly%20effective%20people.pdf";
    String class1, subject, pdfname;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);
        ButterKnife.inject(this);
        loading = new ProgressDialog(ListQuestions.this);
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        class1 = sp.getString("CLASS", null);
        pdfname = getIntent().getStringExtra("pdfname");
        subject = getIntent().getStringExtra("Sub");
        Log.d("Sub", subject);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                loading.setMessage("Loading");
                loading.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                loading.dismiss();

                String webUrl = webView1.getUrl();

            }


        });

        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        String class1 = sp.getString("CLASS", null);
        webView1.loadUrl("https://docs.google.com/gview?embedded=true&url=http://yashodeepacademy.co.in/imp/" + class1 + "/" + subject + "/" + pdfname);
        Log.d("PDF", "https://docs.google.com/gview?embedded=true&url=http://yashodeepacademy.co.in/imp/" + class1 + "/" + subject + "/" + pdfname);
    }
}
