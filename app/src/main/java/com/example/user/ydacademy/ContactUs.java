package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ContactUs extends AppCompatActivity {


    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    Button b1;
    int flag = 0;
    String msg;
    private EditText e1;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        actionBarSetup();
     /*   b1 = (Button) findViewById(R.id.btnSubmit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msg = "";
                e1 = (EditText) findViewById(R.id.name);
                String name = e1.getText().toString();
                if (name.length() == 0) {
                    e1.setError("Please enter name");
                    flag = 1;
                } else {
                    msg += "Name:" + e1.getText() + "\n";
                }
                Spinner spinnerClass = (Spinner) findViewById(R.id.spinner1);
                msg = "Class:" + spinnerClass.getSelectedItem().toString() + "\n";
                Log.d("Spinner", spinnerClass.getSelectedItem().toString());

                e1 = (EditText) findViewById(R.id.email);
                String mail = e1.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!mail.matches(emailPattern)) {
                    e1.setError("Invalid email");
                    flag = 1;
                } else {
                    msg += "Email:" + e1.getText() + "\n";
                }

                e1 = (EditText) findViewById(R.id.phone);

                String number = e1.getText().toString();
                if (number.length() < 10 || number.length() == 0) {
                    e1.setError("Invalid number");
                    flag = 1;
                } else {
                    msg += "Phone No:" + e1.getText() + "\n";
                }

                e1 = (EditText) findViewById(R.id.description);
                String comment = e1.getText().toString();
                if (comment.length() == 0) {
                    e1.setError("Please enter comment");
                    flag = 1;
                } else {
                    msg += "Comment:" + e1.getText() + "\n";
                }
                if (flag == 0) {
                    String[] to = {"ostallohostels@gmail.com"};
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL, to);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Contact Us");
                    intent.putExtra(Intent.EXTRA_TEXT, msg);

                    try {
                        startActivity(Intent.createChooser(intent, "Send Email"));
                        // Snackbar.make(findViewById(R.id.drawer_layout), "Mail sent", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        Log.d("******", "Finished sending email...");
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(ContactUs.this, "Mail not sent", Toast.LENGTH_SHORT).show();
                    }
                   *//* finally {
                        finish();
                    }*//*
                }
                flag = 0;

            }
        });*/
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Home/Contact Us");
        }
    }

}
