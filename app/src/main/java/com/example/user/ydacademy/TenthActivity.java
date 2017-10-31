package com.example.user.ydacademy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TenthActivity extends AppCompatActivity {

    @InjectView(R.id.imageScience)
    ImageView imageScience;
    @InjectView(R.id.imageMaths)
    ImageView imageMaths;
    @InjectView(R.id.imageEnglish)
    ImageView imageEnglish;

    Intent intent;
    String class1, subject;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenth);
        ButterKnife.inject(this);
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        class1 = sp.getString("CLASS", null);
        intent = new Intent(TenthActivity.this, ImpQuestions.class);
    }

    @OnClick({R.id.imageScience, R.id.imageEnglish, R.id.imageMaths})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imageScience:
                subject = "science";
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;
            case R.id.imageMaths:
                subject = "maths";
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;
            case R.id.imageEnglish:
                subject = "english";
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;


        }
    }

}
