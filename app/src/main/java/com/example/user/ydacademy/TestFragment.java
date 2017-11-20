package com.example.user.ydacademy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    String exam;
    String subject;
    Intent intent, intent1;
    @InjectView(R.id.section1)
    View section1;
    @InjectView(R.id.section2)
    View section2;
    @InjectView(R.id.section3)
    View section3;
    @InjectView(R.id.section4)
    View section4;
    @InjectView(R.id.header1)
    TextView header1;
    @InjectView(R.id.header2)
    TextView header2;
    @InjectView(R.id.header3)
    TextView header3;
    @InjectView(R.id.header4)
    TextView header4;
    @InjectView(R.id.txtPhysics)
    TextView txtPhysics;
    @InjectView(R.id.txtPhysics1)
    TextView txtPhysics1;
    @InjectView(R.id.txtPhysics2)
    TextView txtPhysics2;
    @InjectView(R.id.txtPhysics3)
    TextView txtPhysics3;
    @InjectView(R.id.txtChemistry)
    TextView txtChemistry;
    @InjectView(R.id.txtChemistry1)
    TextView txtChemistry1;
    @InjectView(R.id.txtChemistry2)
    TextView txtChemistry2;
    @InjectView(R.id.txtChemistry3)
    TextView txtChemistry3;
    @InjectView(R.id.txtBiology)
    TextView txtBiology;
    @InjectView(R.id.txtBiology1)
    TextView txtBiology1;
    /*    @InjectView(R.id.txtBiology2) TextView txtBiology2;*/
    @InjectView(R.id.txtBiology3)
    TextView txtBiology3;
    /*@InjectView(R.id.txtMaths) TextView txtMaths;*/
    @InjectView(R.id.txtMaths1)
    TextView txtMaths1;
    @InjectView(R.id.txtMaths2)
    TextView txtMaths2;
    @InjectView(R.id.txtMaths3)
    TextView txtMaths3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        intent = new Intent(getActivity(), ChapterActivity.class);
        intent1 = new Intent(getActivity(), ImpQuestions.class);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick({R.id.header1, R.id.header2, R.id.header3, R.id.header4, R.id.txtPhysics, R.id.txtPhysics1, R.id.txtPhysics2, R.id.txtPhysics3, R.id.txtChemistry, R.id.txtChemistry1, R.id.txtChemistry2, R.id.txtChemistry3, R.id.txtBiology, R.id.txtBiology1, /*R.id.txtBiology2,*/ R.id.txtBiology3, /*R.id.txtMaths,*/ R.id.txtMaths1, R.id.txtMaths2, R.id.txtMaths3})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.header1:
                exam = header1.getText().toString();
                intent.putExtra("Exam", exam);
                if (section1.getVisibility() == View.GONE) {
                    section2.setVisibility(View.GONE);
                    section3.setVisibility(View.GONE);
                    section4.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move_down);
                    section1.setAnimation(animation);
                    section1.setVisibility(View.VISIBLE);
                } else {
                    section1.setVisibility(View.GONE);
                }
                break;
            case R.id.header2:
                exam = header2.getText().toString();
                intent.putExtra("Exam", exam);
                if (section2.getVisibility() == View.GONE) {
                    section3.setVisibility(View.GONE);
                    section1.setVisibility(View.GONE);
                    header1.setVisibility(View.GONE);

                    section4.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move_down);
                    section2.setAnimation(animation);
                    section2.setVisibility(View.VISIBLE);
                } else {
                    section2.setVisibility(View.GONE);
                    header1.setVisibility(View.VISIBLE);

                }
                break;
            case R.id.header3:
                exam = header3.getText().toString();
                intent.putExtra("Exam", exam);
                if (section3.getVisibility() == View.GONE) {
                    section1.setVisibility(View.GONE);
                    section2.setVisibility(View.GONE);
                    section4.setVisibility(View.GONE);
                    header1.setVisibility(View.GONE);
                    header2.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move_down);
                    section3.setAnimation(animation);
                    section3.setVisibility(View.VISIBLE);
                } else {
                    section3.setVisibility(View.GONE);
                    header1.setVisibility(View.VISIBLE);
                    header2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.header4:
                exam = header4.getText().toString();
                intent1.putExtra("Exam", exam);
                if (section4.getVisibility() == View.GONE) {
                    section2.setVisibility(View.GONE);
                    section3.setVisibility(View.GONE);
                    header1.setVisibility(View.GONE);
                    header2.setVisibility(View.GONE);
                    header3.setVisibility(View.GONE);
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move_down);
                    section4.setAnimation(animation);
                    section4.setVisibility(View.VISIBLE);
                    section1.setVisibility(View.GONE);
                } else {
                    section4.setVisibility(View.GONE);
                    header1.setVisibility(View.VISIBLE);
                    header2.setVisibility(View.VISIBLE);
                    header3.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.txtPhysics:
                subject = txtPhysics.getText().toString();
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;
            case R.id.txtPhysics1:
                subject = txtPhysics1.getText().toString();
//                Log.d("Exam",subject);
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;
            case R.id.txtPhysics2:
                subject = txtPhysics2.getText().toString();
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;
            case R.id.txtPhysics3:
                subject = txtPhysics3.getText().toString();
                intent1 = new Intent(getActivity(), ImpQuestions.class);
                intent1.putExtra("Subject", subject);
                startActivity(intent1);
                break;
            case R.id.txtChemistry:
                subject = txtChemistry.getText().toString();
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;
            case R.id.txtChemistry1:
                subject = txtChemistry1.getText().toString();
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;
            case R.id.txtChemistry2:
                subject = txtChemistry2.getText().toString();
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;
            case R.id.txtChemistry3:
                subject = txtChemistry3.getText().toString();
                intent1 = new Intent(getActivity(), ImpQuestions.class);
                intent1.putExtra("Subject", subject);
                startActivity(intent1);
                break;
            case R.id.txtBiology:
                subject = txtBiology.getText().toString();
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;
            case R.id.txtBiology1:
                subject = txtBiology1.getText().toString();
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;

            case R.id.txtBiology3:
                subject = txtBiology3.getText().toString();
                intent1 = new Intent(getActivity(), ImpQuestions.class);
                intent1.putExtra("Subject", subject);
                startActivity(intent1);
                break;

            case R.id.txtMaths1:
                subject = txtMaths1.getText().toString();
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;

            case R.id.txtMaths2:
                subject = txtMaths2.getText().toString();
                intent.putExtra("Subject", subject);
                startActivity(intent);
                break;
            case R.id.txtMaths3:
                subject = txtMaths3.getText().toString();
                intent1 = new Intent(getActivity(), ImpQuestions.class);
                intent1.putExtra("Subject", subject);
                startActivity(intent1);
                break;
        }
    }
}
