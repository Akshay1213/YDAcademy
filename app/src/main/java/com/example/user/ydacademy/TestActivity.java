package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {
    static int i, flag = 0;
    @InjectView(R.id.btn_next) Button btn_next;
    @InjectView(R.id.btn_submit) Button btn_submit;
    @InjectView(R.id.imageview) ImageView imageview;
    @InjectView(R.id.radioGroup) RadioGroup radioGroup;
    @InjectView(R.id.txt_timer) TextView Text_timer;
    @InjectView(R.id.txt_queNumber) TextView text_queNumber;
    CountDownTimer countDownTimer;
    String answerKey, ans, data, userans, exam, subject, chapter, es, class1;
    ProgressBar progressBar ;
    int count;
    UrlRequest urlRequest;
    JSONObject json_data;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.inject(this);
        count=0;
        userans="";
        data="";
        ans=null;
        countDownTimer=new Mycountdowntimer(30000,1000);
        exam=getIntent().getStringExtra("Exam");
        subject=getIntent().getStringExtra("Subject");
        es=getIntent().getStringExtra("ES");
        chapter=getIntent().getStringExtra("Chapter");
        Log.d("ES***",es+chapter);
        sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
        class1=sp.getString("CLASS",null);
        Log.d("Class***",class1);
        actionBarSetup();
        progressBar=(ProgressBar)findViewById(R.id.progress);
        load_image();
    }

    @OnClick({R.id.btn_submit,R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:

                if (count == 24 || flag == 0 || count == 25)
                    check_result();
                while (userans.length()<25)
                {
                    userans+="E";
                }


                urlRequest=UrlRequest.getObject();
                urlRequest.setContext(TestActivity.this);
                urlRequest.setUrl("http://yashodeepacademy.co.in/fetchanswerkeys.php?examcode="+es+chapter+"&class="+class1);


                urlRequest.getResponse(new ServerCallback() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("Response", response);


                        try {

                            JSONArray jArray = new JSONArray(response);
                            answerKey="";
                            for(i = 0; i < jArray.length(); i++) {
                                json_data = jArray.getJSONObject(i);
                                Log.d("wth", "onSuccess: "+json_data.getString("ans"));
                                answerKey+=json_data.getString("ans");
                            }
                            Log.d("answerkey***",answerKey);

                            data=userans+" "+answerKey;
                            Intent intent=new Intent(TestActivity.this,ResultActivity.class);
                            intent.putExtra("data",data);
                            intent.putExtra("Class",class1);
                            intent.putExtra("Subject", subject);
                            intent.putExtra("Exam", exam);
                            intent.putExtra("ES", es);
                            intent.putExtra("Chapter",chapter);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

                break;
            case R.id.btn_next:
                flag=1;
                check_result();

                load_image();
                flag=0;
                if(count==24) {

                    view.setEnabled(false);
                }


                break;
        }
    }

    public void load_image()
    {


           Glide.with(getApplicationContext()).load("http://yashodeepacademy.co.in/" + class1 + "/" + es + chapter + "/q" + (count + 1) + ".PNG").asBitmap().override(600, 600)
                   .placeholder(null).listener(new RequestListener<String, Bitmap>() {
               @Override
               public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                   progressBar.setVisibility(View.VISIBLE);
                   countDownTimer.cancel();
                   text_queNumber.setText(count + 1 + ".");
                   btn_next.performClick();

                   return false;
               }

               @Override
               public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                   progressBar.setVisibility(View.GONE);
                   text_queNumber.setText(count + 1 + ".");
                   countDownTimer.start();
                   Toast.makeText(TestActivity.this, "count " + count, Toast.LENGTH_LONG).show();
                   count++;
                   return false;
               }
           }).error(null).into(imageview);

    }

    public void check_result()
    {
        if(radioGroup.getCheckedRadioButtonId()!=-1)
        {
            RadioButton radioButton=((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId()));
            userans+=radioButton.getText().toString();
        }
        else
            userans+="E";
         if(count<25)
        radioGroup.clearCheck();


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(TestActivity.this,"You can't go back",Toast.LENGTH_LONG).show();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Test/" + exam + "/" + subject);
        }
    }

    public class Mycountdowntimer extends CountDownTimer {
        public Mycountdowntimer(long starttime, long interval)

        {
            super(starttime, interval);
        }


        @Override
        public void onTick(long miliseconds) {
            Text_timer.setText(miliseconds / 1000 + ":00");

        }

        @Override
        public void onFinish() {
            check_result();
            if (count < 25)
                load_image();

        }
    }
}
