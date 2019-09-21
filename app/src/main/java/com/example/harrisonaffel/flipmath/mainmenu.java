package com.example.harrisonaffel.flipmath;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.TextView;

import java.util.Random;

import static android.view.View.INVISIBLE;

public class mainmenu extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage;
    //private TextView title = findViewById(R.id.description);
    boolean  h = true, c = false, i = false, yes = false;
    Fragment fragment;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            ContentFrameLayout fragmentview = findViewById(R.id.fragmentview);


            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    //findViewById(R.id.settings).setVisibility(View.VISIBLE);
                    slideOff(fragmentview);
                    c = false;
                    i = false;
                    return true;




                case R.id.navigation_challenge:
                    if(!c) {

                        // mTextMessage.setText(R.string.divide);
                        //findViewById(R.id.settings).setVisibility(INVISIBLE);
                        fragment = new startChallenge();
                        loadFragment(fragment);
                        slideOff(fragmentview);
                        fragmentview.setVisibility(View.VISIBLE);
                        slideOn(fragmentview);
                        c = true;
                        h = false;
                        i = false;
                    }
                     return true;

                case R.id.navigation_individualOps:
                    if(!i){

                        //findViewById(R.id.settings).setVisibility(INVISIBLE);

                        fragment = new chooseOperation();
                        loadFragment(fragment);
                        slideOff(fragmentview);
                        fragmentview.setVisibility(View.VISIBLE);
                        slideOn(fragmentview);
                        c = false;
                        h = false;
                        i = true;
                        return true;
                    }

            }
            return false;
        }
    };


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentview, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void slideOff(ContentFrameLayout frameLayout){
        Random rand = new Random();

        int val = rand.nextInt(3);
        switch (val){
            case(0):

                ObjectAnimator animation = ObjectAnimator.ofFloat(frameLayout, "translationX",1500f);
                animation.setDuration(250);
                animation.start();
                break;

            case(1):
                animation = ObjectAnimator.ofFloat(frameLayout, "translationX",3500f);
                animation.setDuration(250);
                animation.start();
                break;


            case(2):
                animation = ObjectAnimator.ofFloat(frameLayout, "translationX",-1500f);
                animation.setDuration(250);
                animation.start();
                break;

            case(3):
                animation = ObjectAnimator.ofFloat(frameLayout, "translationY",-1500f);
                animation.setDuration(250);
                animation.start();
                break;
        }

    }


    public void fragSlideOn(){
        ContentFrameLayout fragmentview = findViewById(R.id.fragmentview);
        slideOn(fragmentview);
    }
    public void fragSlideOff(){
        ContentFrameLayout fragmentview = findViewById(R.id.fragmentview);
        slideOff(fragmentview);
    }

    public void slideOn(ContentFrameLayout frameLayout){


        ObjectAnimator animationback = ObjectAnimator.ofFloat(frameLayout, "translationX",1500f);
        ObjectAnimator animationup = ObjectAnimator.ofFloat(frameLayout, "translationX",00f);
        animationup.setDuration(1);
        animationup.start();
        animationback.setDuration(1);
        animationback.start();
        frameLayout.setVisibility(View.VISIBLE);
        ObjectAnimator animation = ObjectAnimator.ofFloat(frameLayout, "translationX",0f);
        animation.setDuration(500);
        animation.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        TextView mainscoredisp = findViewById(R.id.mainscoredisp);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("highscore", 0);
        if(settings.contains("highscore")) {
            int highestscore = settings.getInt("highscore", 0);
            mainscoredisp.setText(String.valueOf(highestscore));
        }else{
            mainscoredisp.setText("0");
        }
    }


    @Override
    public void onClick(View view) {

    }
}
