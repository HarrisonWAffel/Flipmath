package com.example.harrisonaffel.flipmath;

import android.animation.ObjectAnimator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class challengeRound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView attempts = findViewById(R.id.totalAttempts), realscore = findViewById(R.id.secretscore);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_round);
        play();
    }


    @Override
    public void onBackPressed() {
        // do nothing
    }

    Fragment problem;
    public void play(){
        ContentFrameLayout fragmentview = findViewById(R.id.fragmentview);
        problem = new challenge();
        loadFragment(problem);

    }


    public void slideOn(ContentFrameLayout frameLayout){

        ObjectAnimator animationback = ObjectAnimator.ofFloat(frameLayout, "translationX",1500f);
        ObjectAnimator animationup = ObjectAnimator.ofFloat(frameLayout, "translationY",00f);
        animationup.setDuration(1);
        animationup.start();
        animationback.setDuration(1);
        animationback.start();
      //  frameLayout.setVisibility(View.VISIBLE);
        ObjectAnimator animation = ObjectAnimator.ofFloat(frameLayout, "translationX",0f);
        animation.setDuration(500);
        animation.start();
    }

    public void slideOff(ContentFrameLayout frameLayout){
        Random rand = new Random();



                ObjectAnimator animation = ObjectAnimator.ofFloat(frameLayout, "translationX",1500f);
                animation.setDuration(250);
                animation.start();


    }




    public void loadFragment(Fragment fragment) {
        // load fragment
        ContentFrameLayout fragmentview = findViewById(R.id.fragmentview);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.problemfragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


}
