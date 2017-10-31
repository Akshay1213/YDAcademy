package com.example.user.ydacademy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.phelat.fun.Control.FunControl;
import com.phelat.fun.Layouts.Funny;
import com.phelat.fun.Widget.FunnyButton;

import java.util.Collections;
import java.util.List;

/**
 * Created by android on 10/23/17.
 */

public class AdapterResult extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    protected boolean amuser = true;
    List<DataResult> data = Collections.emptyList();
    DataResult current;
    int currentPos = 0;
    RequestQueue requestQueue;
    String hostel_id = "", phone = "", status = "", body;
    MyHolder myHolder;
    String userans;
    private Context context;
    private LayoutInflater inflater;
    private int prevpos;


    // create constructor to innitilize context and data sent frm MainActivity
    public AdapterResult(Context context, List<DataResult> data) {
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
        View view = inflater.inflate(R.layout.container_result, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        final MyHolder myHolder = (MyHolder) holder;
        DataResult dataResult = data.get(position);

        Log.d("Yeee.", dataResult.userans + " " + dataResult.result + " " + "position" + position);
        myHolder.queNUmber.setText((position + 1) + ".");
        myHolder.optionA.setChecked(false);
        myHolder.optionB.setChecked(false);
        myHolder.optionC.setChecked(false);
        myHolder.optionD.setChecked(false);
        myHolder.iv_A.setImageBitmap(null);
        myHolder.iv_B.setImageBitmap(null);
        myHolder.iv_C.setImageBitmap(null);
        myHolder.iv_D.setImageBitmap(null);
        myHolder.iv_A.setImageBitmap(null);
        myHolder.iv_B.setImageBitmap(null);
        myHolder.iv_C.setImageBitmap(null);
        myHolder.iv_D.setImageBitmap(null);


        if (dataResult.userans == dataResult.result) {

            Log.d("Yee", "onBindViewHolder: " + position);
            switch (dataResult.result) {
                case 'A':
                    myHolder.optionA.setChecked(true);
                    myHolder.iv_A.setImageResource(R.drawable.ic_check_24dp);
                    break;
                case 'B':
                    myHolder.optionB.setChecked(true);
                    myHolder.iv_B.setImageResource(R.drawable.ic_check_24dp);
                    break;
                case 'C':
                    myHolder.optionC.setChecked(true);
                    myHolder.iv_C.setImageResource(R.drawable.ic_check_24dp);
                    break;
                case 'D':
                    myHolder.optionD.setChecked(true);
                    myHolder.iv_D.setImageResource(R.drawable.ic_check_24dp);
                    break;
            }

        } else if ((dataResult.userans != dataResult.result && dataResult.userans != 'E')) {
            Log.d("Yee", "onBindViewHolder:of else " + position);
            switch (dataResult.userans) {
                case 'A':
                    myHolder.optionA.setChecked(true);
                    myHolder.iv_A.setImageResource(R.drawable.ic_clear_black_24dp);
                    break;
                case 'B':
                    myHolder.optionB.setChecked(true);
                    myHolder.iv_B.setImageResource(R.drawable.ic_clear_black_24dp);
                    break;
                case 'C':
                    myHolder.optionC.setChecked(true);
                    myHolder.iv_C.setImageResource(R.drawable.ic_clear_black_24dp);
                    break;
                case 'D':
                    myHolder.optionD.setChecked(true);
                    myHolder.iv_D.setImageResource(R.drawable.ic_clear_black_24dp);
                    break;
            }

        }
        if ((dataResult.userans == 'E') || (dataResult.userans != dataResult.result))
            switch (dataResult.result) {
                case 'A':
                    myHolder.iv_A.setImageResource(R.drawable.ic_check_24dp);
                    break;
                case 'B':
                    myHolder.iv_B.setImageResource(R.drawable.ic_check_24dp);
                    break;
                case 'C':
                    myHolder.iv_C.setImageResource(R.drawable.ic_check_24dp);
                    break;
                case 'D':
                    myHolder.iv_D.setImageResource(R.drawable.ic_check_24dp);
                    break;

            }

        Glide.with(context).load(dataResult.imageURL).asBitmap().override(600, 600)
                .placeholder(R.drawable.sorryimagenotavailable)
                .error(R.drawable.sorryimagenotavailable)
                .into(myHolder.ivresult);
        Glide.with(context).load(dataResult.description_url).asBitmap().override(600, 600)
                .placeholder(R.drawable.sorryimagenotavailable)
                .error(R.drawable.sorryimagenotavailable)
                .into(myHolder.ivdescription);
        myHolder.funnyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHolder.funControl.expand();
            }

        });
        myHolder.ivresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHolder.funControl.collapse();
            }
        });
        myHolder.funnyContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHolder.funControl.collapse();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        ImageView ivresult, iv_A, iv_B, iv_C, iv_D, ivdescription;
        TextView queNUmber;
        RadioGroup radiogroup;
        RadioButton optionA, optionB, optionC, optionD;

        private Funny funny;

        private FunnyButton funnyButton;

        private LinearLayout funnyContainer;

        private FunControl funControl;


        public MyHolder(View itemView) {
            super(itemView);
            ivresult = itemView.findViewById(R.id.ivresult);
            ivdescription = itemView.findViewById(R.id.imageview_description);
            queNUmber = itemView.findViewById(R.id.txt_queNumber);

            iv_A = itemView.findViewById(R.id.imageview_A);
            iv_B = itemView.findViewById(R.id.imageview_B);
            iv_C = itemView.findViewById(R.id.imageview_C);
            iv_D = itemView.findViewById(R.id.imageview_D);

            radiogroup = itemView.findViewById(R.id.radioGroup);
            optionA = itemView.findViewById(R.id.radiobutton_A);
            optionB = itemView.findViewById(R.id.radiobutton_B);
            optionC = itemView.findViewById(R.id.radiobutton_C);
            optionD = itemView.findViewById(R.id.radiobutton_D);

            optionA.setEnabled(false);
            optionB.setEnabled(false);
            optionC.setEnabled(false);
            optionD.setEnabled(false);

            funny = itemView.findViewById(R.id.mFunny);
            funnyButton = itemView.findViewById(R.id.mFunnyButton);
            funnyContainer = itemView.findViewById(R.id.mFunContainer);
            funControl = new FunControl.Builder()
                    .setFunnyLayout(funny)
                    .setFunnyButton(funnyButton)
                    .setContainer(funnyContainer)
                    .setAnimationDuration(400)
                    .setGravityToExpand(Gravity.CENTER)
                    .build();

        }

    }

}
