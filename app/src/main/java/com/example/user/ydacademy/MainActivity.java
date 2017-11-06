package com.example.user.ydacademy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public FragmentManager fragmentManager;
    @InjectView(R.id.slider)
    SliderLayout sliderShow;
    /*  @InjectView(R.id.cardAboutUs)CardView cardAboutUs;
      @InjectView(R.id.cardAchievers)CardView cardAchievers;
      @InjectView(R.id.cardStaff)CardView cardStaff;
      @InjectView(R.id.cardContactUs)CardView cardContactUs;
      @InjectView(R.id.cardCareer)CardView cardCareer;
      @InjectView(R.id.cardSuccess)CardView cardSuccess;*/
    /* @InjectView(R.id.btn_login)AppCompatButton btn_login;*/
    @InjectView(R.id.btn_career)
    AppCompatButton btn_career;
    @InjectView(R.id.imageClassroom)
    ImageView imageClassroom;
    Menu menu1, navbar_menu;
    @InjectView(R.id.btn_Staff)
    AppCompatButton btnStaff;
    @InjectView(R.id.btn_success)
    AppCompatButton btnsuccess;
    @InjectView(R.id.btn_contactus)
    AppCompatButton btnContactUs;
    @InjectView(R.id.btn_aboutus)
    AppCompatButton btnAboutUs;
    @InjectView(R.id.btn_achiever)
    AppCompatButton btnAchiever;
    SharedPreferences sp;
    String username, id, class1;
    MenuItem menuItem;
    String itemname;
    Intent intent;
    Animation animation;
    private Toast toast;
    private long back_pressed = 0;
    private CareerGuidenceFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        fragmentManager = getSupportFragmentManager();
        for (int i = 1; i <= 5; i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.image("http://yashodeepacademy.co.in/slider/" + i + ".jpg");
            //  textSliderView.image("http://orientalbirdimages.org/images/data/striated_laughingthrush_0001.jpg");
            sliderShow.addSlider(textSliderView);
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.grow);

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (findViewById(R.id.btn_contactus)).performClick();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (
                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        actionBarSetup();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

            }
        });
    }


    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (fragment != null) {
            fragment = null;
            fragmentManager.popBackStack();
        } else if (back_pressed + 2000 > System.currentTimeMillis()) {
            // need to cancel the toast here
            toast.cancel();
            // code for exit
            intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            // ask user to press back button one more time to close app
            toast = Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT);
            toast.show();
        }
        back_pressed = System.currentTimeMillis();

    }


    @OnClick({R.id.imageClassroom, R.id.btn_career, R.id.btn_Staff, R.id.btn_success, R.id.btn_contactus, R.id.btn_aboutus, R.id.btn_achiever})

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageClassroom:

                sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                menuItem = menu1.findItem(R.id.action_login);

                if (menuItem.getTitle().equals("Login")) {
                    Log.d("am i null", "onClick: " + (menuItem == null ? "am null" : "not null"));
                    onOptionsItemSelected(menuItem);
                } else if (sp.getString("CLASS", null).equals("10")) {
                    startActivity(new Intent(this, TenthActivity.class));
                    //onOptionsItemSelected(menuItem);
                } else
                    startActivity(new Intent(this, TabActivity.class));
                break;
            case R.id.btn_career:
                //btn_career.startAnimation(animation);
                fragment = new CareerGuidenceFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.drawer_layout, fragment).addToBackStack(null).commit();
                break;
            case R.id.btn_Staff:
//                btnStaff.startAnimation(animation);
                intent = new Intent(MainActivity.this, StaffActivity.class);
                startActivity(intent);

                break;
            case R.id.btn_success:
//                btnsuccess.startAnimation(animation);
                intent = new Intent(MainActivity.this, SuccessStories.class);
                startActivity(intent);
                break;
            case R.id.btn_contactus:
//                btnContactUs.startAnimation(animation);
                intent = new Intent(MainActivity.this, ContactUs.class);
                startActivity(intent);
                break;
            case R.id.btn_aboutus:
//                btnAboutUs.startAnimation(animation);
                intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);

                break;
            case R.id.btn_achiever:
//                btnAchiever.startAnimation(animation);
                Intent intent = new Intent(MainActivity.this, OurAchievers.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
//        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        menu1 = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        menuItem = item;
        if (id == R.id.action_login) {
            itemname = item.getTitle().toString();
            Log.d("ItemName", itemname);

            if (itemname.equals("Login")) {
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, 100);
            } else {
                menuItem.setTitle("Login");
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                Menu menu = navigationView.getMenu();
                menu.findItem(R.id.nav_login).setTitle("Login");
                SharedPreferences sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USERNAME", null);
                editor.putString("ID", null);
                editor.putString("CLASS", null);
                editor.commit();

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item1) {
        // Handle navigation view item clicks here.
        int id = item1.getItemId();
        if (id == R.id.nav_login) {
            MenuItem item = menu1.findItem(R.id.action_login);
            onOptionsItemSelected(item);
        } else if (id == R.id.nav_classroom) {
            (findViewById(R.id.imageClassroom)).performClick();
        } else if (id == R.id.nav_staff) {
            (findViewById(R.id.btn_Staff)).performClick();
        } else if (id == R.id.nav_careerguidance) {
            (findViewById(R.id.btn_career)).performClick();
        } else if (id == R.id.nav_contactus) {
            (findViewById(R.id.btn_contactus)).performClick();
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://play.google.com/store/apps/details?id=com.xoxytech.ostello");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setTitle("Yashodeep Academy");
            ab.setSubtitle("Home");
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (data.getBooleanExtra("data", false)) {
                menuItem.setTitle("Logout");
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                SharedPreferences sp = getSharedPreferences("YourSharedPreference", Activity.MODE_PRIVATE);
                ((TextView) (navigationView.getHeaderView(0).findViewById(R.id.username))).setText(sp.getString("USERNAME", null));
                ((TextView) (navigationView.getHeaderView(0).findViewById(R.id.standard))).setText(sp.getString("CLASS", null));
                Menu menu = navigationView.getMenu();
                menu.getItem(0).setTitle("Logout");
                Log.d("****", "Item**** ");
            }
        }
    }
}