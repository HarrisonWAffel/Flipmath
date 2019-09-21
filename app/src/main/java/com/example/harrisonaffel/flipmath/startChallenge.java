package com.example.harrisonaffel.flipmath;

import android.animation.ObjectAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.ContentFrameLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import static android.view.View.INVISIBLE;

public class startChallenge extends Fragment{

    private StartChallengeViewModel mViewModel;

    public static startChallenge newInstance() {
        return new startChallenge();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.start_challenge_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StartChallengeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button start = getActivity().findViewById(R.id.start);
        TextView highscore = getActivity().findViewById(R.id.highscoreDisp);
        SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("highscore", 0);
        int highestscore = settings.getInt("highscore", 0);
        highscore.setText(String.valueOf(highestscore));


        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), challengeRound.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }





}
