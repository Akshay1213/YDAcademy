package com.example.user.ydacademy;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Collections;
import java.util.List;

/**
 * Created by android on 10/31/17.
 */

public class AdapterStaff extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    List<DataStaff> data = Collections.emptyList();

    MyHolder myHolder;
    private Context context;
    private LayoutInflater inflater;

    // create constructor to innitilize context and data sent frm MainActivity
    public AdapterStaff(Context context, List<DataStaff> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        myHolder = (MyHolder) holder;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_staff, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        final MyHolder myHolder = (MyHolder) holder;
        final int pos = position;
        DataStaff dataStaff = data.get(position);

        myHolder.name.setText(dataStaff.name);
        myHolder.degree.setText(dataStaff.degree);
        myHolder.exp.setText(dataStaff.exp);
        myHolder.description.setText(dataStaff.description);
        Glide.with(context).load("http://yashodeepacademy.co.in/teachersimages/t" + dataStaff.id + ".jpg").asBitmap().override(600, 600)
                .placeholder(R.drawable.sorryimagenotavailable).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                onBindViewHolder(holder, pos);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).error(R.drawable.sorryimagenotavailable)
                .into(myHolder.ivstaff1);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        ImageView ivstaff1;
        TextView name, degree, exp, description;

        public MyHolder(View itemView) {
            super(itemView);
            ivstaff1 = itemView.findViewById(R.id.ivstaff1);
            name = itemView.findViewById(R.id.txt_name);
            degree = itemView.findViewById(R.id.txt_degree);
            exp = itemView.findViewById(R.id.txt_exp);
            description = itemView.findViewById(R.id.txt_description);
        }
    }
}
