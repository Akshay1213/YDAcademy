package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class ChapterActivity extends AppCompatActivity {

    @InjectView(R.id.listChapter)
    ListView listChapters;
    /* @InjectView(R.id.txtListChapters)TextView txtListChapter;*/
    UrlRequest urlRequest;
    String exam;
    Intent intent;
    String subject, class1, chapter, es;
    SharedPreferences sp;
    List<String> chapters;
    ArrayAdapter adapter;
    ProgressDialog loading;
    int pos;
    char e, s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        actionBarSetup();
        ButterKnife.inject(this);
        chapters = new ArrayList<>();
        exam = getIntent().getStringExtra("Exam");
        subject = getIntent().getStringExtra("Subject");
        Log.d("Exam", exam);
        Log.d("Sub", subject);

        e = exam.toLowerCase().charAt(0);
        Log.d("InitialExam", e + "");
        s = subject.toLowerCase().charAt(0);
        Log.d("ExamSubject", s + "");

        es = e + "" + s;
        Log.d("E***", exam);
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        class1 = sp.getString("CLASS", null);
        Log.d("Class***", class1);

        getData();
    }

    public void getData() {
        loading = ProgressDialog.show(ChapterActivity.this, "Loading", "Please wait.....", false, false);
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(ChapterActivity.this);
        urlRequest.setUrl("http://yashodeepacademy.co.in/fetchchaptername.php?subjectcode=" + es + "&class=" + class1);
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {

                                       Log.d("Response", response);
                                       try {
                                           JSONArray jsonArray = new JSONArray(response);
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               JSONObject jsonObject = jsonArray.getJSONObject(i);
                                               chapter = jsonObject.getString("name");
                                               chapters.add("" + (i + 1) + ". " + chapter);
                                           }

                                       } catch (JSONException e1) {
                                           e1.printStackTrace();
                                       }
                                       loading.dismiss();

                                       Log.d("Chapters", chapters.toString() + "");
                                       adapter = new ArrayAdapter(ChapterActivity.this, android.R.layout.simple_list_item_1, chapters);
                                       listChapters.setAdapter(adapter);
                                       adapter.notifyDataSetChanged();

                                   }

                               }
        );
    }

    @OnItemClick(R.id.listChapter)
    public void onItemClick(int position) {
        chapters.get(position);
       /* pos=Integer.parseInt(chapters.get(position));*/
        Log.d("***", position + "");
        intent = new Intent(ChapterActivity.this, StartTest.class);
        intent.putExtra("Subject", subject);
        intent.putExtra("Class", class1);
        intent.putExtra("Exam", exam);
        intent.putExtra("ES", es);
        intent.putExtra("position", (position + 1) + "");
        startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Home/Chapters");
        }
    }
}
