package com.example.user.ydacademy;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AchieversDescription extends AppCompatActivity {


    @InjectView(R.id.profile_image)
    ImageView imageProfile;
    @InjectView(R.id.txtId)
    TextView txtId;
    @InjectView(R.id.txtName)
    TextView txtName;
    @InjectView(R.id.txtDescription)
    TextView txtDescription;
    String id, name, desc, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievers_description);
        ButterKnife.inject(this);
        id = getIntent().getStringExtra("Id");
        name = getIntent().getStringExtra("Name");
        desc = getIntent().getStringExtra("Description");
        url = getIntent().getStringExtra("Url");
        txtName.setText(name);
        txtId.setText(id);
        txtDescription.setText(desc);
        Glide.with(AchieversDescription.this).load(url).asBitmap().override(600, 600)
                .placeholder(null).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {

                //  progressBar.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                // progressBar.setVisibility(View.GONE);
                return false;
            }
        }).error(null)
                .into(imageProfile);
    }

}



