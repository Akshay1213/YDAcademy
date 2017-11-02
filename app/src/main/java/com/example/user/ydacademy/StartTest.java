package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class StartTest extends AppCompatActivity {

    @InjectView(R.id.btn_startTest)Button start_test;
    String exam, subject, class1, es, id, chapter;
    int count;
    UrlRequest urlRequest;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        ButterKnife.inject(this);

        exam = getIntent().getStringExtra("Exam");
        subject = getIntent().getStringExtra("Subject");
        class1 = getIntent().getStringExtra("Class");
        chapter = getIntent().getStringExtra("position");
        es = getIntent().getStringExtra("ES");
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        class1 = sp.getString("CLASS", null);
        id = sp.getString("ID", null);
        actionBarSetup();
        Log.d("Class***", class1);
        Log.d("subject", subject);
        Log.d("ES*****", es);
        Log.d("chapter*", chapter);
        Log.d("ID", id);

    }

    @OnClick({R.id.btn_startTest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_startTest:

                urlRequest = UrlRequest.getObject();
                urlRequest.setContext(StartTest.this);
                urlRequest.setUrl("http://yashodeepacademy.co.in/getexamcount.php?student_id=" + id + "&examcode=" + es + chapter + "&class=" + class1);
                urlRequest.getResponse(new ServerCallback() {
                                           @Override
                                           public void onSuccess(String response) {
                                               Log.d("ResponseCount", response);
                                               Log.d("***","http://yashodeepacademy.co.in/getexamcount.php?student_id=" + id + "&examcode=" + es + chapter + "&class=" + class1);
                                               try {
                                                   JSONArray jsonArray = new JSONArray(response);
                                                   for (int i = 0; i < jsonArray.length(); i++) {
                                                       JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                       count = jsonObject.getInt("count(id)");

                                                       Log.d("Count****", jsonObject.getInt("count(id)") + "");

                                                   }

                                                   if (count < 2) {
                                                       Intent intent = new Intent(StartTest.this, TestActivity.class);
                                                       intent.putExtra("Class", class1);
                                                       intent.putExtra("Subject", subject);
                                                       intent.putExtra("Exam", exam);
                                                       intent.putExtra("ES", es);
                                                       intent.putExtra("Chapter", chapter);
                                                       startActivity(intent);
                                                   } else
                                                       Toast.makeText(StartTest.this, "Sorry,You can attemp test maximum 2 times", Toast.LENGTH_LONG).show();


                                               } catch (JSONException e1) {
                                                   e1.printStackTrace();
                                               }
                                           }
                                       }
                );


                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");

        }
    }
}
