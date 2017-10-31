/*
package com.example.user.ydacademy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.io.File;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Advertisement extends AppCompatActivity
{
    @InjectView(R.id.slider)SliderLayout sliderShow;
    @InjectView(R.id.btn_login)AppCompatButton btn_login;
    @InjectView(R.id.btn_career)AppCompatButton btn_career;
    @InjectView(R.id.imageClassroom)ImageView imageClassroom;
    @InjectView(R.id.btn_staff)AppCompatButton btnStaff;
    @InjectView(R.id.btn_success)AppCompatButton btnsuccess;
    @InjectView(R.id.btn_contactus)AppCompatButton btnContactUs;
    @InjectView(R.id.btn_aboutus)AppCompatButton btnAboutUs;
    @InjectView(R.id.btn_achiever)AppCompatButton btnAchiever;
    SharedPreferences sp;
    String username,id,class1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        ButterKnife.inject(this);

        try
        {
            File f = new File("/data/data/com.xoxytech.ostello/shared_prefs/YourSharedPreference.xml");
            if (f.exists()) {
                Log.d("TAG", "SharedPreferences Name_of_your_preference : exist");
                sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                 username = sp.getString("USERNAME", null);
                 id= sp.getString("ID", null);
                class1=sp.getString("CLASS",null);
                Toast.makeText(Advertisement.this,"Logged in using "+username,Toast.LENGTH_LONG);
                if(username!=null)
                 {
                    btn_login.setText("Logout");
                }
            }
            else
                Log.d("TAG", "Setup default preferences");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        for(int i=1;i<=5;i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.image("http://yashodeepacademy.co.in/slider/"+i+".jpg");
          //  textSliderView.image("http://orientalbirdimages.org/images/data/striated_laughingthrush_0001.jpg");
            sliderShow.addSlider(textSliderView);
        }
       */
/* LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapteradvertisement=new Adapteradvertisement(getApplicationContext());
        recyclerView.setAdapter(adapteradvertisement);*//*

    }

    @OnClick({R.id.btn_login,R.id.imageClassroom,R.id.btn_career,R.id.btn_staff,R.id.btn_success,R.id.btn_contactus,R.id.btn_aboutus,R.id.btn_achiever})

        public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btn_login:
                if(btn_login.getText().toString().equals("Login")) {
                    Intent intent = new Intent(Advertisement.this, LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    SharedPreferences sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("USERNAME", null);
                    editor.putString("ID", null);
                    editor.putString("CLASS",null);
                    editor.commit();
                }
                break;
            case R.id.imageClassroom:

                sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                username = sp.getString("USERNAME", null);
                id= sp.getString("ID", null);
                class1=sp.getString("CLASS",null);

                if (username!=null||id!=null||class1!=null)
                {
                    Intent intent1 = new Intent(Advertisement.this, TabActivity.class);
                    startActivity(intent1);
                }
                else
                {
                    Intent intent = new Intent(Advertisement.this, LoginActivity.class);
                    startActivity(intent);
                }
                           break;
            case R.id.btn_career:
                         getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new CareerGuidenceFragment ()).commit();
                         break;
            case R.id.btn_staff:
                         getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new StaffFragment ()).commit();
                         break;
            case R.id.btn_success:
                         getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new SuccessStoriesFragment ()).commit();
                         break;
            case R.id.btn_contactus:
                        getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new ContactUsFragment()).commit();
                        break;
            case R.id.btn_aboutus:
                         getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new AboutUsFragment()).commit();
                         break;
            case R.id.btn_achiever:
                         getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new AchieversFragment()).commit();
                         break;



        }
    }
}
*/
