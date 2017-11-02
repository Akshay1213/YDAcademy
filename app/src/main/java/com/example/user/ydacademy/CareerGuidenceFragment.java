package com.example.user.ydacademy;


import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class CareerGuidenceFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner1, spinner2;
    View section1, section2;
    boolean flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_career_guidence, container, false);
        view.setBackgroundColor(Color.WHITE);
        actionBarSetup();
        spinner1 = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);
        flag = false;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.courses1, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.courses2, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner2.setAdapter(adapter1);
        spinner2.setOnItemSelectedListener(this);

        section1 = view.findViewById(R.id.section1);
        section2 = view.findViewById(R.id.section2);

        View header1 = view.findViewById(R.id.header1);
        header1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (section1.getVisibility() == View.GONE) {
                    section1.setVisibility(View.VISIBLE);
                    section2.setVisibility(View.GONE);
                    spinner1.setVisibility(View.VISIBLE);
                    spinner1.performClick();
                } else {
                    section1.setVisibility(View.GONE);
                    spinner1.setVisibility(View.GONE);

                }
            }
        });
        View header2 = view.findViewById(R.id.header2);
        header2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (section2.getVisibility() == View.GONE) {
                    section2.setVisibility(View.VISIBLE);
                    section1.setVisibility(View.GONE);
                    spinner2.setVisibility(View.VISIBLE);
                    spinner2.performClick();
                } else {
                    spinner2.setVisibility(View.GONE);
                    section2.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        {
            if (!flag) {
                flag = true;
            } else {
                Intent intent = new Intent(getActivity(), CareerDetailsActivity.class);
                intent.putExtra("Name", adapterView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Test/");
        }
    }
}

