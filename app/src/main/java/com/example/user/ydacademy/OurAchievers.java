package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
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

public class OurAchievers extends AppCompatActivity {


    UrlRequest urlRequest;
    AdapterStudent adapter;
    List<DataStudent> data;
    DataStudent studentData;
    ProgressDialog loading;
    /*  @InjectView(R.id.Liststudent)*/ RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_achievers);
        //  ButterKnife.inject(this);
        actionBarSetup();
        urlRequest = UrlRequest.getObject();
        urlRequest.setContext(OurAchievers.this);
        urlRequest.setUrl("http://yashodeepacademy.co.in/fetchstudentacheivers.php");
        loading = ProgressDialog.show(OurAchievers.this, "Loading", "Please wait.....", false, false);

        urlRequest.getResponse(new ServerCallback() {
                                   @Override
                                   public void onSuccess(String response) {
                                       Log.d("Response", response);
                                       final List<DataStudent> data = new ArrayList<>();
                                       try {
                                           JSONArray jsonArray = new JSONArray(response);
                                           for (int i = 0; i < jsonArray.length(); i++) {
                                               studentData = new DataStudent();
                                               JSONObject jsonObject = jsonArray.getJSONObject(i);
                                               studentData.name = jsonObject.getString("name");
                                               studentData.id = jsonObject.getString("id");
                                               studentData.description = jsonObject.getString("description");
                                               data.add(studentData);
                                           }
                                           Log.d("Size", data.size() + "");
                                           recyclerView = (RecyclerView) findViewById(R.id.Liststudent);

                                           recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                                               @Override
                                               public void onItemClick(View view, int position) {
                                                   // Toast.makeText(OurAchievers.this,"On"+data.get(position),Toast.LENGTH_LONG).show();
                                                   Intent intent = new Intent(OurAchievers.this, AchieversDescription.class);
                                                   intent.putExtra("Name", data.get(position).name);
                                                   intent.putExtra("Id", data.get(position).id);
                                                   intent.putExtra("Desc", data.get(position).description);
                                                   intent.putExtra("Url", "http://yashodeepacademy.co.in/studentacheivers/" + data.get(position).id + ".jpg");
                                                   startActivity(intent);
                                               }
                                                   })
                                           );
                                           recyclerView.setVisibility(View.VISIBLE);
                                           adapter = new AdapterStudent(OurAchievers.this, data);
                                           recyclerView.setAdapter(adapter);
                                           recyclerView.setLayoutManager(new GridLayoutManager(OurAchievers.this, 2));
                                           adapter.notifyDataSetChanged();
                                           loading.dismiss();

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
