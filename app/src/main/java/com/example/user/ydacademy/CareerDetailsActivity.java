package com.example.user.ydacademy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CareerDetailsActivity extends AppCompatActivity {
    TextView txtName, txtDesc, txtScope, txtRelevantCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_details);
        txtName = (TextView) findViewById(R.id.txtName);
        txtDesc = (TextView) findViewById(R.id.txtDesc);
        txtScope = (TextView) findViewById(R.id.txtScope);
        txtRelevantCourses = (TextView) findViewById(R.id.txtRelevantCourses);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        txtName.setText(name);
        if (name.equals("Art Teacher Diploma")) {
            txtDesc.setText(R.string.description1);
            txtScope.setText(R.string.scope1);

        }
        if (name.equals("Commercial Art Diploma")) {
            txtDesc.setText(R.string.description2);
            txtScope.setText(R.string.scope2);
            txtRelevantCourses.setText(R.string.relevant_courses1);
        }
        if (name.equals("Diploma in Beauty culture and Hair dressing")) {
            txtDesc.setText(R.string.description3);
            txtScope.setText(R.string.scope3);
            // txtRelevantCourses.setText(R.string.relevant_courses1);
        }
        if (name.equals("Diploma in Garment technology")) {
            txtDesc.setText(R.string.description4);
            txtScope.setText(R.string.scope4);
            //txtRelevantCourses.setText(R.string.relevant_courses1);
        }
        if (name.equals("Diploma in Stenography")) {
            txtDesc.setText(R.string.description5);
            txtScope.setText(R.string.scope5);
            // txtRelevantCourses.setText(R.string.relevant_courses1);
        }
        if (name.equals("Engineering Diploma")) {
            txtDesc.setText(R.string.description6);
            txtScope.setText(R.string.scope6);
            txtRelevantCourses.setText(R.string.relevant_courses2);
        }
        if (name.equals("Industrial Training Institutes")) {
            txtDesc.setText(R.string.description7);
            txtScope.setText(R.string.scope7);
            // txtRelevantCourses.setText(R.string.relevant_courses1);
        }
        if (name.equals("Laboratory Technician Diploma")) {
            txtDesc.setText(R.string.description8);
            txtScope.setText(R.string.scope8);
            //txtRelevantCourses.setText(R.string.relevant_courses1);
        }
        if (name.equals("B.Sc. in Hospitality and Hotel Administration")) {
            txtDesc.setText(R.string.description11);
            txtScope.setText(R.string.scope11);
        }
        if (name.equals("Diploma in Dance and Music")) {
            txtDesc.setText(R.string.description12);
            txtScope.setText(R.string.scope12);
            //    txtRelevantCourses.setText(R.string.relevant_courses2);
        }
        if (name.equals("Diploma in Elementary Education")) {
            txtDesc.setText(R.string.description13);
            txtScope.setText(R.string.scope13);
            // txtRelevantCourses.setText(R.string.relevant_courses2);
        }
        if (name.equals("Diploma in travel and tourism")) {
            txtDesc.setText(R.string.description14);
            txtScope.setText(R.string.scope14);
            //txtRelevantCourses.setText(R.string.relevant_courses2);
        }
        if (name.equals("L.L.B (Hons) Integrated Course")) {
            txtDesc.setText(R.string.description15);
            txtScope.setText(R.string.scope15);
            txtRelevantCourses.setText(R.string.relevant_courses15);
        }
        if (name.equals("Bachelor of Ayurveda, Medicine and Surgery (B.A.M.S.)")) {
            txtDesc.setText(R.string.description16);
            txtScope.setText(R.string.scope16);
            txtRelevantCourses.setText(R.string.relevant_courses16);
        }
        if (name.equals("Bachelor of Dental Surgery (B. D. S.)")) {
            txtDesc.setText(R.string.description17);
            txtScope.setText(R.string.scope17);
            txtRelevantCourses.setText(R.string.relevant_courses17);
        }
        if (name.equals("Bachelor of Homeopathic Medicine and Surgery (B.H.M.S.)")) {
            txtDesc.setText(R.string.description18);
            txtScope.setText(R.string.scope18);
            txtRelevantCourses.setText(R.string.relevant_courses18);
        }
        if (name.equals("Bachelor of Medicine and Surgery (M.B.B.S)")) {
            txtDesc.setText(R.string.description19);
            txtScope.setText(R.string.scope19);
            txtRelevantCourses.setText(R.string.relevant_courses19);
        }
        if (name.equals("Bachelor of Science")) {
            txtDesc.setText(R.string.description20);
            txtScope.setText(R.string.scope20);
            txtRelevantCourses.setText(R.string.relevant_courses20);
        }
        if (name.equals("Bachelor of Science Nursing ")) {
            txtDesc.setText(R.string.description21);
            txtScope.setText(R.string.scope21);
            txtRelevantCourses.setText(R.string.relevant_courses21);
        }
        if (name.equals("Bachelor of Veterinary Science and Animal Husbandry (B.V.Sc.and AH)")) {
            txtDesc.setText(R.string.description22);
            txtScope.setText(R.string.scope22);
            txtRelevantCourses.setText(R.string.relevant_courses22);
        }
        if (name.equals("Bachelor of Architecture (B. Arch.)")) {
            txtDesc.setText(R.string.description23);
            txtScope.setText(R.string.scope23);
            txtRelevantCourses.setText(R.string.relevant_courses23);
        }
        if (name.equals("Bachelor of Computer Applications (BCA)")) {
            txtDesc.setText(R.string.description24);
            txtScope.setText(R.string.scope24);
            txtRelevantCourses.setText(R.string.relevant_courses24);
        }
        if (name.equals("Bachelor of Engineering (BE/B Tech)")) {
            txtDesc.setText(R.string.description25);
            txtScope.setText(R.string.scope25);
            txtRelevantCourses.setText(R.string.relevant_courses25);
        }
        if (name.equals("Bachelor of Planning")) {
            txtDesc.setText(R.string.description26);
            txtScope.setText(R.string.scope26);
            txtRelevantCourses.setText(R.string.relevant_courses26);
        }
        if (name.equals("Cinematographer")) {
            txtDesc.setText(R.string.description27);
            // txtScope.setText(R.string.scope);
            //txtRelevantCourses.setText(R.string.relevant_courses2);
        }
        if (name.equals("Bachelor of Science in Dairy Technology (B. Sc. Dairy Technology)")) {
            txtDesc.setText(R.string.description28);
            txtScope.setText(R.string.scope28);
            txtRelevantCourses.setText(R.string.relevant_courses28);
        }
        if (name.equals("B. Tech. in Agriculture")) {
            txtDesc.setText(R.string.description29);
            txtScope.setText(R.string.scope29);
            txtRelevantCourses.setText(R.string.relevant_courses29);
        }


    }
}
