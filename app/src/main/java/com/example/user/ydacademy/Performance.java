package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class Performance extends AppCompatActivity {

    UrlRequest urlRequest;
    AdapterPerformance adapter;
    List<DataPerformance> data;
    RecyclerView recyclerView;
    SharedPreferences sp;
    RelativeLayout relativeLayout;
    String id, class1, exam1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);
        ButterKnife.inject(this);
        actionBarSetup();
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        id = sp.getString("ID1", null);
        class1 = sp.getString("CLASS1", null);
        Log.d("Id", id);
        Log.d("Class", class1);

        urlRequest = UrlRequest.getObject();

        urlRequest.setContext(Performance.this);
        urlRequest.setUrl("http://yashodeepacademy.co.in/fetchexamstat.php?student_id=" + id + "&class=" + class1);
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       Log.d("Response", response);
                                       if (!response.contains("Invalid subject code")) {

                                           List<DataPerformance> data = new ArrayList<>();
                                           try {
                                               JSONArray jsonArray = new JSONArray(response);
                                               for (int i = 0; i < jsonArray.length(); i++) {
                                                   DataPerformance dataPerformance = new DataPerformance();
                                                   JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                   exam1 = jsonObject.getString("examcode");
                                                   Log.d("Exam", exam1);

                                                   switch (exam1.charAt(0)) {
                                                       case 'n':
                                                           dataPerformance.exam = "NEET";
                                                           break;
                                                       case 'c':
                                                           dataPerformance.exam = "CET";
                                                           break;
                                                       case 'j':
                                                           dataPerformance.exam = "JEE";
                                                           break;
                                                   }
                                                   switch (exam1.charAt(1)) {
                                                       case 'p':
                                                           dataPerformance.subject1 = "Physics";
                                                           break;
                                                       case 'c':
                                                           dataPerformance.subject1 = "Chemistry";
                                                           break;
                                                       case 'm':
                                                           dataPerformance.subject1 = "Maths";
                                                           break;
                                                       case 'b':
                                                           dataPerformance.subject1 = "Biology";
                                                           break;
                                                   }

                                                   dataPerformance.chapter = jsonObject.getString("name");
                                                   dataPerformance.score = jsonObject.getString("score");
                                                   dataPerformance.date = jsonObject.getString("date");
                                                   dataPerformance.time = jsonObject.getString("time");
                                                   Log.d("Exam", dataPerformance.exam);
                                                   Log.d("Subject", dataPerformance.subject1);
                                                   Log.d("Chapter", dataPerformance.chapter);
                                                   Log.d("Score", dataPerformance.score);
                                                   Log.d("Date****", dataPerformance.date);
                                                   int score = Integer.parseInt(dataPerformance.score.split("-")[0]);
                                                   if (score <= 10) {
                                                       dataPerformance.performance = "Poor";
                                                   } else if (score > 10 && score <= 30) {
                                                       dataPerformance.performance = "Average";
                                                   } else if (score > 30 && score <= 40) {
                                                       dataPerformance.performance = "Good";
                                                   } else {
                                                       dataPerformance.performance = "Excellent";
                                                   }
                                                   Log.d("Performance", dataPerformance.performance);

                                                   data.add(dataPerformance);
                                               }
                                               Log.d("Size***", data.size() + "");
                                               recyclerView = (RecyclerView) findViewById(R.id.recyclePerformance);
                                               recyclerView.setVisibility(View.VISIBLE);
                                               adapter = new AdapterPerformance(Performance.this, data);
                                               recyclerView.setAdapter(adapter);
                                               recyclerView.setLayoutManager(new LinearLayoutManager(Performance.this));
                                               adapter.notifyDataSetChanged();
                                           } catch (JSONException e1) {
                                               e1.printStackTrace();
                                           }
                                       } else {
                                           TextView textView = (TextView) findViewById(R.id.txtNoTest);
                                           relativeLayout = (RelativeLayout) findViewById(R.id.relativePerformance);
                                           relativeLayout.setVisibility(View.GONE);
                                           textView.setVisibility(View.VISIBLE);
                                       }
                                   }
                               }
        );
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Home/Performance");
        }
    }
}
