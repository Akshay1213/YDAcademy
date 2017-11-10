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
import butterknife.InjectView;

public class StaffActivity extends AppCompatActivity {

    UrlRequest urlRequest;
    AdapterStaff adapter;
    @InjectView(R.id.Liststaff)
    RecyclerView listStaff;
    List<DataStaff> data;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        ButterKnife.inject(this);
        actionBarSetup();
        final List<DataStaff> data = new ArrayList<>();
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(StaffActivity.this);
        urlRequest.setUrl("http://yashodeepacademy.co.in/fetchteachersimages.php");
        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       Log.d("Response", response);
                                       try {
                                           JSONArray jsonArray = new JSONArray(response);
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               DataStaff staffData = new DataStaff();
                                               JSONObject jsonObject = jsonArray.getJSONObject(i);
                                               staffData.name = jsonObject.getString("name");
                                               staffData.id = jsonObject.getString("id");
                                               staffData.degree = jsonObject.getString("degree");
                                               staffData.exp = jsonObject.getString("experience");
                                               staffData.description = jsonObject.getString("desription");
                                               data.add(staffData);
                                           }
                                           recyclerView = (RecyclerView) findViewById(R.id.Liststaff);
                                           recyclerView.setVisibility(View.VISIBLE);
                                           adapter = new AdapterStaff(StaffActivity.this, data);
                                           recyclerView.setAdapter(adapter);
                                           recyclerView.setLayoutManager(new GridLayoutManager(StaffActivity.this, 2));
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
            ab.setSubtitle("Staff");
        }
    }
}
