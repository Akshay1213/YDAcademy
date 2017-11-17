package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class StudentStatforAdmin extends AppCompatActivity {

    UrlRequest urlRequest;
    AdapterPerformance adapter;
    List<DataPerformance> data;
    RecyclerView recyclerView;
    SharedPreferences sp;
    String id, class1, exam1, name;
    @InjectView(R.id.et_username)
    EditText edtUsername;
    @InjectView(R.id.spinnerClass)
    Spinner spinnerClass;
    @InjectView(R.id.cv)
    CardView cardView1;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_statfor_admin);
        ButterKnife.inject(this);
        actionBarSetup();
        recyclerView = (RecyclerView) findViewById(R.id.recyclePerformance);
        recyclerView.setVisibility(View.GONE);
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        id = sp.getString("ID", null);
        submit = (Button) findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(StudentStatforAdmin.this, R.anim.bounce);
                //  submit.setAnimation(animation);
                name = edtUsername.getText().toString();
                class1 = spinnerClass.getSelectedItem().toString();
                Log.d("class", class1);
                if (spinnerClass.getSelectedItem() != null && spinnerClass.getSelectedItem() != "--Select class--") {
                    urlRequest = UrlRequest.getObject();
                    urlRequest.setContext(StudentStatforAdmin.this);
                    urlRequest.setUrl("http://yashodeepacademy.co.in/fetchstudentstatadmin.php?student_id=" + name + "&class=" + class1);
                    urlRequest.getResponse(new ServerCallback() {
                                               @Override
                                               public void onSuccess(String response) {
                                                   Log.d("Response", response);
                                                   if (!response.contains("Invalid subject code")) {
                                                       List<DataPerformance> data = new ArrayList<>();
                                                       recyclerView.setVisibility(View.VISIBLE);
                                                       cardView1.setVisibility(View.GONE);
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
                                                           adapter = new AdapterPerformance(StudentStatforAdmin.this, data);
                                                           recyclerView.setAdapter(adapter);
                                                           recyclerView.setLayoutManager(new LinearLayoutManager(StudentStatforAdmin.this));
                                                           adapter.notifyDataSetChanged();
                                                       } catch (JSONException e1) {
                                                           e1.printStackTrace();
                                                       }
                                                   } else {
                                                       Toast.makeText(StudentStatforAdmin.this, "Invalid data", Toast.LENGTH_LONG).show();
                                                   }
                                               }
                                           }
                    );
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (recyclerView.getVisibility() == View.GONE)
            super.onBackPressed();
        recyclerView.setVisibility(View.GONE);
        cardView1.setVisibility(View.VISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Home/Admin Panel");
        }
    }

}
