package com.example.harrisonaffel.flipmath;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;


public class summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        String score;
        String attempts;


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                score = null;
                attempts = null;
            } else {
                score= extras.getString("score");
                attempts = extras.getString("Attempts");
            }
        } else {
            score = (String) savedInstanceState.getSerializable("score");
            attempts = (String) savedInstanceState.getSerializable("Attempts");

        }

        TextView dispScore = findViewById(R.id.dispScore);
        TextView dispAttempts = findViewById(R.id.attemptsdisp);
        TextView dispPercent = findViewById(R.id.percent);

        String percentval = String.valueOf((Double.parseDouble(score) / Double.parseDouble(attempts))*100);

        percentval = percentval.substring(0, 3)+"%";

        dispScore.setText(score);
        dispAttempts.setText(String.valueOf(Integer.parseInt(attempts)-Integer.parseInt(score)));
        dispPercent.setText(percentval);

        Button cont = findViewById(R.id.continuetoMenu);
        final Intent intent = new Intent(this, mainmenu.class);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save score if its highscore and continue back to menu
                startActivity(intent);
            }
        });
    }




}
