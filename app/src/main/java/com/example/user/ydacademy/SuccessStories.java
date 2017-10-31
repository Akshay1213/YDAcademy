package com.example.user.ydacademy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SuccessStories extends AppCompatActivity {

    @InjectView(R.id.recyclerSuccessStories)
    RecyclerView recyclerSuccessStories;
    Adapteradvertisement adapteradvertisement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_stories);
        ButterKnife.inject(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerSuccessStories.setLayoutManager(layoutManager);
        adapteradvertisement = new Adapteradvertisement(getApplicationContext());
        recyclerSuccessStories.setAdapter(adapteradvertisement);


    }
}
