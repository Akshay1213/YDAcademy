package com.example.user.ydacademy;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class CareerGuidenceFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner1, spinner2;
    View view, section1, section2;
    View header2, header1;
    boolean flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_career_guidence, container, false);

        view.setBackgroundColor(Color.WHITE);

        spinner1 = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);
        flag = false;

        spinner1.setOnItemSelectedListener(this);


        spinner2.setOnItemSelectedListener(this);

        section1 = view.findViewById(R.id.section1);
        section2 = view.findViewById(R.id.section2);

        header1 = view.findViewById(R.id.header1);

        header1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (section1.getVisibility() == View.GONE) {
                    section1.setVisibility(View.VISIBLE);
                    section2.setVisibility(View.GONE);
                    header2.setVisibility(View.GONE);
                    spinner1.setVisibility(View.VISIBLE);
                    spinner1.performClick();
                } else {
                    section1.setVisibility(View.GONE);
                    spinner1.setVisibility(View.GONE);
                    header2.setVisibility(View.VISIBLE);
                }
            }
        });
        header2 = view.findViewById(R.id.header2);
        header2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (section2.getVisibility() == View.GONE) {
                    section2.setVisibility(View.VISIBLE);
                    section1.setVisibility(View.GONE);
                    header1.setVisibility(View.GONE);
                    spinner2.setVisibility(View.VISIBLE);
                    spinner2.performClick();
                } else {
                    spinner2.setVisibility(View.GONE);
                    section2.setVisibility(View.GONE);
                    header1.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        {
            if (i > 0) {
                Intent intent = new Intent(getActivity(), CareerDetailsActivity.class);
                intent.putExtra("Name", adapterView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


}

