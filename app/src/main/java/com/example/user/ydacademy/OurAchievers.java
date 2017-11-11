package com.example.user.ydacademy;

import android.annotation.TargetApi;
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

public class OurAchievers extends AppCompatActivity {


    UrlRequest urlRequest;
    AdapterStudent adapter;
    List<DataStudent> data;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_achievers);
        ButterKnife.inject(this);
        actionBarSetup();
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(OurAchievers.this);
        urlRequest.setUrl("http://yashodeepacademy.co.in/fetchstudentacheivers.php");
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       Log.d("Response", response);
                                       List<DataStudent> data = new ArrayList<>();
                                       try {
                                           JSONArray jsonArray = new JSONArray(response);
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               DataStudent studentData = new DataStudent();
                                               JSONObject jsonObject = jsonArray.getJSONObject(i);
                                               studentData.name = jsonObject.getString("name");
                                               studentData.id = jsonObject.getString("id");
                                               studentData.description = jsonObject.getString("description");
                                               data.add(studentData);
                                           }
                                           Log.d("Size", data.size() + "");
                                           recyclerView = (RecyclerView) findViewById(R.id.Liststudent);
                                           recyclerView.setVisibility(View.VISIBLE);
                                           adapter = new AdapterStudent(OurAchievers.this, data);
                                           recyclerView.setAdapter(adapter);
                                           recyclerView.setLayoutManager(new GridLayoutManager(OurAchievers.this, 2));
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
            ab.setSubtitle("Home/Our Achievers");
        }
    }
}
