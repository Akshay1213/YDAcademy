package com.example.user.ydacademy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by android on 10/13/17.
 */

public class Adapteradvertisement extends RecyclerView.Adapter<Adapteradvertisement.MyHolder> {

    Context context;


    public Adapteradvertisement(Context context) {
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_advertisement, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView txtDescription;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            imageView = itemView.findViewById(R.id.imageView1);
        }
    }
}
