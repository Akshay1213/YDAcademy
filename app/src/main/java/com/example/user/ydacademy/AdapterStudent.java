package com.example.user.ydacademy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Collections;
import java.util.List;

/**
 * Created by android on 11/6/17.
 */

public class AdapterStudent extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    List<DataStudent> data = Collections.emptyList();
    MyHolder myHolder;
    DataStudent dataStudent;
    private Context context;
    private LayoutInflater inflater;

    public AdapterStudent(Context context, List<DataStudent> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "hey am clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        myHolder = (MyHolder) holder;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_student, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final MyHolder myHolder = (MyHolder) holder;
        final int pos = position;
        final ProgressDialog loading = new ProgressDialog(context);
        loading.setProgress(5);
        dataStudent = data.get(position);
        myHolder.name.setText(dataStudent.name);
        Log.d("name", dataStudent.name);
        myHolder.description.setText(dataStudent.description);

        Glide.with(context).load("http://yashodeepacademy.co.in/studentacheivers/" + dataStudent.id + ".jpg").asBitmap().override(600, 600)
                .placeholder(null).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                onBindViewHolder(holder, pos);
                myHolder.progressBar.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                myHolder.progressBar.setVisibility(View.GONE);
                return false;

            }
        }).error(null)
                .into(myHolder.imageStudent);


        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHolder.getItemId();
                Log.d("Name", dataStudent.name);
                Intent intent = new Intent(context, AchieversDescription.class);
                intent.putExtra("Url", "http://yashodeepacademy.co.in/studentacheivers/" + dataStudent.id + ".jpg");
                intent.putExtra("Name", dataStudent.name);
                intent.putExtra("Id", dataStudent.id);
                intent.putExtra("Description", dataStudent.description);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageStudent;
        TextView name, description;
        ProgressBar progressBar;
        CardView cardView;

        public MyHolder(View itemView) {
            super(itemView);
            imageStudent = itemView.findViewById(R.id.iimageStudent);
            name = itemView.findViewById(R.id.txt_name);
            description = itemView.findViewById(R.id.txt_description);
            progressBar = itemView.findViewById(R.id.progress);
            cardView = itemView.findViewById(R.id.cardStudent);
        }
    }
}
