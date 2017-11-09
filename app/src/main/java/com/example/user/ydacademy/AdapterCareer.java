package com.example.user.ydacademy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by android on 11/9/17.
 */

public class AdapterCareer extends BaseAdapter {
    List<DataCareer> list;
    Context context;

    public AdapterCareer(List<DataCareer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CareerView careerView;
        if (view == null) {
            careerView = new CareerView(context);
        } else {
            careerView = (CareerView) view;
        }
        careerView.setDataCareer(list.get(i));
        return careerView;
    }
}
