package com.example.user.ydacademy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by android on 11/9/17.
 */

public class CareerView extends LinearLayout {
    TextView textView;
    DataCareer dataCareer;

    public CareerView(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.spinner_layout, null);
        textView = findViewById(R.id.txtSpinner);
        addView(view);
    }

    public void setDataCareer(DataCareer dataCareer) {
        this.dataCareer = dataCareer;
        textView.setText(dataCareer.title);
    }


}
