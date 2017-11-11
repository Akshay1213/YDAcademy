package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.biubiubiu.justifytext.library.JustifyTextView;

public class AboutUs extends AppCompatActivity {

    @InjectView(R.id.text)
    JustifyTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        actionBarSetup();
        ButterKnife.inject(this);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.move_up);
        textView.startAnimation(animation);

    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Home/About Us");
        }
    }
}
