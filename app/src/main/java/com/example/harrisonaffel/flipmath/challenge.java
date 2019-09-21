package com.example.harrisonaffel.flipmath;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ContentFrameLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class challenge extends Fragment {

    private ChallengeViewModel mViewModel;


    private problem generateProblem(int lowerdifficulty, int upperdifficulty) {
        Random rand = new Random();
        int left, right, operation;
        operation = rand.nextInt(3);

        if(operation == 2){
            left = rand.nextInt(10);
            right = rand.nextInt(12);
            return new problem(left, right, operation);
        }

            left = rand.nextInt(Integer.parseInt(getActivity().getString(R.string.defaultDifficulty)));
            right = rand.nextInt(Integer.parseInt(getActivity().getString(R.string.defaultDifficulty)));

        return new problem(left, right, operation);
    }

    public void submit() {

    }



    public void playgame() {


        currentproblem = generateProblem(0, 75);
        try {
            TextView l = getView().findViewById(R.id.leftnum);
            TextView r = getView().findViewById(R.id.rightnum);
            TextView o = getView().findViewById(R.id.operation);
            l.setText(String.valueOf(currentproblem.left));
            r.setText(String.valueOf(currentproblem.right));
            switch (currentproblem.operation) {
                case (0):
                    o.setText("+");
                    break;

                case (1):
                    o.setText("-");
                    break;

                case (2):
                    o.setText("x");
                    break;

                case (3):
                    o.setText("/");
                    break;
            }
        } catch (NullPointerException np) {
            //do literally nothing at all.
        }

    }




    public static challenge newInstance() {
        return new challenge();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.challenge_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChallengeViewModel.class);
        playgame();


    }

    problem currentproblem;

    public problem getCurProb() {
        return currentproblem;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button start = getActivity().findViewById(R.id.problemsubmit);
        start.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    TextView submission = getActivity().findViewById(R.id.result);
                    if(submission.getText().toString().length() == 0){
                        return;
                    }

                    String result = submission.getText().toString();

                    TextView secretsolution = getActivity().findViewById(R.id.secretscore);
                    TextView totalAttempts = getActivity().findViewById(R.id.totalAttempts);
                    TextView roundNum = getActivity().findViewById(R.id.roundNum);
                    int addone;
                    int totalattmpt;
                    TextView s = getActivity().findViewById(R.id.lives);
                    int lives = Integer.parseInt(s.getText().toString());
                        addone = Integer.parseInt(secretsolution.getText().toString()) + 1;
                        totalattmpt = Integer.parseInt(totalAttempts.getText().toString()) + 1;

                        if(Integer.parseInt(result) == (currentproblem.solution)){


                            totalAttempts.setText(String.valueOf(totalattmpt));
                            secretsolution.setText(String.valueOf(addone)) ;


                            challengeRound round = (challengeRound) getActivity();
                            ContentFrameLayout fragment = getActivity().findViewById(R.id.problemfragment);
                            ((challengeRound) getActivity()).slideOff(fragment);
                            round.play();
                            ((challengeRound) getActivity()).slideOn(fragment);
                            int num = Integer.parseInt(roundNum.getText().toString());
                            num++;
                            roundNum.setText(String.valueOf(num));


                        }else{
                            totalAttempts.setText(String.valueOf(totalattmpt));
                            challengeRound round = (challengeRound) getActivity();
                            lives--;
                            TextView livesdisp = getActivity().findViewById(R.id.lives);
                            livesdisp.setText(String.valueOf(lives));
                            ContentFrameLayout fragmentview = getActivity().findViewById(R.id.fragmentview);


                            ((challengeRound) getActivity()).slideOff(fragmentview);
                            round.play();

                            int num = Integer.parseInt(roundNum.getText().toString());
                            num++;

                            checkLose(lives);
                            roundNum.setText(String.valueOf(num));


                        }

                }
            });

        Button quit = getActivity().findViewById(R.id.exit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainmenu = new Intent(getActivity(), com.example.harrisonaffel.flipmath.mainmenu.class);
                startActivity(mainmenu);
            }
        });
    }

    private void checkLose(int num){
        if(num<=0){


            Intent gameover = new Intent(getActivity(), summary.class);
            TextView attempts = getActivity().findViewById(R.id.totalAttempts);
            TextView score = getActivity().findViewById(R.id.secretscore);
            String val = score.getText().toString();
            String attmpts = attempts.getText().toString();
            gameover.putExtra("score", val);
            gameover.putExtra("Attempts",attmpts);


            SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("highscore", 0);
            int highestscore = settings.getInt("highscore", 0);


            if(Integer.parseInt(val) > highestscore){
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("highscore", Integer.parseInt(val));

                // Apply the edits!
                editor.apply();

            }else {
                startActivity(gameover);
            }
        }
    }

}
