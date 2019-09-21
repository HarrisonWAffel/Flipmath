package com.example.harrisonaffel.flipmath;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class singleOp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_op);

        Button submit = findViewById(R.id.problemsubmit2);
        play();



    }

    public void play(){
        final singleOpFragment fragmentview = new singleOpFragment();
        loadFragment(fragmentview);

    }


    public void loadFragment(Fragment fragment) {
        // load fragment

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.problemfragment2, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        String operation = getIntent().getStringExtra("operation");
        return super.onCreateView(name, context, attrs);


    }
}
