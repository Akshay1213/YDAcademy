package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.andexert.library.RippleView;

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
    @InjectView(R.id.more)
    RippleView rippleView;
    @InjectView(R.id.more1)
    RippleView rippleView1;
    @InjectView(R.id.more2)
    RippleView rippleView2;
    Animation animation;
    Intent intent;
    String class1, subject;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenth);
        ButterKnife.inject(this);
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        animation = AnimationUtils.loadAnimation(TenthActivity.this, R.anim.image_click);
        class1 = sp.getString("CLASS", null);
        intent = new Intent(TenthActivity.this, ImpQuestions.class);
        rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                rippleView.setRippleDuration(40);
                subject = "english";
                intent.putExtra("Subject", subject);
                startActivity(intent);
            }
        });

        rippleView1.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                rippleView1.setRippleDuration(40);
                subject = "maths";
                intent.putExtra("Subject", subject);
                startActivity(intent);

            }
        });
        rippleView2.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                rippleView2.setRippleDuration(40);
                subject = "science";
                intent.putExtra("Subject", subject);
                startActivity(intent);
            }
        });

    }


    @OnClick({R.id.imageScience, R.id.imageEnglish, R.id.imageMaths, R.id.more})
    public void onClick(View view) {

        switch (view.getId()) {

          /*  case R.id.more:


                break;*/

            case R.id.imageMaths:

                break;
            case R.id.imageEnglish:

                break;


        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Tenth/");
        }
    }

}
