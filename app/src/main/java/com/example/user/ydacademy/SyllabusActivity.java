package com.example.user.ydacademy;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SyllabusActivity extends AppCompatActivity {
    /*@InjectView(R.id.listChapters)
    ListView listView;*/
    @InjectView(R.id.webView)
    WebView webView1;
    UrlRequest urlRequest;
    SharedPreferences sp;
    //String urlPDF="https://docs.google.com/gview?embedded=true&url=http://www.stafforini.com/txt/Covey%20-%20The%207%20habits%20of%20highly%20effective%20people.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        ButterKnife.inject(this);
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(SyllabusActivity.this);
        urlRequest.setUrl("http://ostallo.com/ostello/fetchcities.php");
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       Log.d("Response", response);
                                   }
                               }
        );

        webView1.getSettings().setJavaScriptEnabled(true);
        String exam = getIntent().getStringExtra("Exam");
        String subject = getIntent().getStringExtra("Subject");
        exam = exam.toLowerCase();
        subject = subject.toLowerCase();
        Log.d("Exam", exam);
        Log.d("subject", subject);
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        String class1 = sp.getString("CLASS", null);
        webView1.loadUrl("https://docs.google.com/gview?embedded=true&url=http://yashodeepacademy.co.in/syllabus/" + class1 + exam + subject + ".pdf");
        Log.d("PDF", "https://docs.google.com/gview?embedded=true&url=http://yashodeepacademy.co.in/syllabus/" + class1 + exam + subject + ".pdf");
    }
}
