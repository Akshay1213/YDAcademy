package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);
        ButterKnife.inject(this);
        actionBarSetup();
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        id = sp.getString("ID", null);
        Log.d("Id", id);
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(Performance.this);
        urlRequest.setUrl("http://yashodeepacademy.co.in/fetchstudentacheivers.php");
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       Log.d("Response", response);
                                       List<DataPerformance> data = new ArrayList<>();
                                       try {
                                           JSONArray jsonArray = new JSONArray(response);
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               DataPerformance dataPerformance = new DataPerformance();
                                               JSONObject jsonObject = jsonArray.getJSONObject(i);
                                               dataPerformance.exam = jsonObject.getString("exam");
                                               dataPerformance.subject1 = jsonObject.getString("subject");
                                               dataPerformance.chapter = jsonObject.getString("chapter");
                                               dataPerformance.score = jsonObject.getString("score");
                                               dataPerformance.performance = jsonObject.getString("performance");
                                               data.add(dataPerformance);
                                           }
                                           Log.d("Size", data.size() + "");
                                           recyclerView = (RecyclerView) findViewById(R.id.recyclePerformance);
                                           recyclerView.setVisibility(View.VISIBLE);
                                           adapter = new AdapterPerformance(Performance.this, data);
                                           recyclerView.setAdapter(adapter);
                                           recyclerView.setLayoutManager(new GridLayoutManager(Performance.this, 2));
                                           adapter.notifyDataSetChanged();

                                       } catch (JSONException e1) {
                                           e1.printStackTrace();
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
