package com.example.user.ydacademy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by android on 11/7/17.
 */

public class AdapterPerformance extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    List<DataPerformance> data = Collections.emptyList();

    AdapterStaff.MyHolder myHolder;
    private Context context;
    private LayoutInflater inflater;

    // create constructor to innitilize context and data sent frm MainActivity
    public AdapterPerformance(Context context, List<DataPerformance> data) {
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
        myHolder = (AdapterStaff.MyHolder) holder;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_performance, parent, false);
        AdapterPerformance.MyHolder holder = new AdapterPerformance.MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        final AdapterPerformance.MyHolder myHolder = (AdapterPerformance.MyHolder) holder;
        final int pos = position;
        DataPerformance dataPerformance = data.get(position);

        myHolder.txtExam.setText(dataPerformance.exam);
        myHolder.txtSubject.setText(dataPerformance.subject1);
        myHolder.txtChapter.setText(dataPerformance.chapter);
        myHolder.txtScore.setText(dataPerformance.score);
        myHolder.txtPerformance.setText(dataPerformance.performance);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView txtExam, txtSubject, txtChapter, txtScore, txtPerformance;

        public MyHolder(View itemView) {
            super(itemView);
            txtExam = itemView.findViewById(R.id.txt_exam);
            txtSubject = itemView.findViewById(R.id.txt_subject);
            txtChapter = itemView.findViewById(R.id.txt_chapter);
            txtScore = itemView.findViewById(R.id.txt_Score);
            txtPerformance = itemView.findViewById(R.id.txt_perofrmance);
        }
    }
}
