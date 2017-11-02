package com.example.user.ydacademy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.et_username)
    EditText edtUsername;
    @InjectView(R.id.et_password)
    EditText edtPassword;
    @InjectView(R.id.btnLogin)
    Button btnLogin;
    @InjectView(R.id.cv)
    CardView cardView;
    @InjectView(R.id.txtForgotPassword)
    TextView txtForgotPassword;
    @InjectView(R.id.relativeLogin)
    RelativeLayout relativeLayout;
    UrlRequest urlRequest;
    String username, password, name, id, class1;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            File f = new File(
                    "/data/data/com.xoxytech.ostello/shared_prefs/YourSharedPreference.xml");
            if (f.exists()) {
                Log.d("TAG", "SharedPreferences Name_of_your_preference : exist");
                SharedPreferences sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                String username = sp.getString("USERNAME", null);
                if (username != null)
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else
                Log.d("TAG", "Setup default preferences");

        } catch (Exception e) {
            e.printStackTrace();
        }
        ButterKnife.inject(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.btnLogin, R.id.txtForgotPassword,}) /* , R.id.fab*/
    public void onClick(View view) {
        switch (view.getId()) {
           /* case R.id.fab:
               getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;*/

            case R.id.btnLogin:

                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                id = sp.getString("ID", null);
                name = sp.getString("USERNAME", null);
                class1 = sp.getString("CLASS", null);

                urlRequest = UrlRequest.getObject();
                urlRequest.setContext(LoginActivity.this);
                urlRequest.setUrl("http://yashodeepacademy.co.in/login_verification.php?username=" + username + "&password=" + password);
                urlRequest.getResponse(new ServerCallback() {
                                           @Override
                                           public void onSuccess(String response) {
                                               try {
                                                   Log.d("Response", response);
                                                   if (!response.contains("Invalid phone number or password")) {
                                                       JSONArray jsonArray = new JSONArray(response);
                                                       for (int i = 0; i < jsonArray.length(); i++) {
                                                           JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                           id = jsonObject.getString("id");
                                                           class1 = jsonObject.getString("class");
                                                           name = jsonObject.getString("name");
                                                       }
                                                       SharedPreferences.Editor editor = sp.edit();
                                                       editor.putString("ID", id);
                                                       editor.putString("CLASS", class1);
                                                       editor.putString("USERNAME", name);
                                                       editor.commit();
                                                       Explode explode = new Explode();
                                                       explode.setDuration(500);
                                                       getWindow().setExitTransition(explode);
                                                       getWindow().setEnterTransition(explode);
                                                       ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
                                                       Intent intent = new Intent();
                                                       intent.putExtra("data", true);
                                                       setResult(RESULT_OK, intent);
                                                       finish();
                                                       return;
                                                   } else {
                                                       //Toast.makeText(LoginActivity.this,"Invalid login",Toast.LENGTH_LONG).show();
                                                       Snackbar.make(relativeLayout, "Invalid Login", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                                       Log.d("Invalidlogin", "onSuccess: ");
                                                   }
                                               } catch (JSONException e) {
                                                   e.printStackTrace();
                                               }

                                               Log.d("Id", id);
                                               Log.d("Username", name);
                                               Log.d("Class***", class1);


                                           }
                                       }
                );


                // Intent i2 = new Intent(this,LoginSuccessActivity.class);
                //startActivity(i2, oc2.toBundle());
                break;

            case R.id.txtForgotPassword:

                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this).setCancelable(true);
                View detailView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_forgot_password, null, false);
                builder.setView(detailView);
                //ButterKnife.inject(this, detailView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("data", false);
        setResult(RESULT_OK, intent);
        finish();
    }
}
