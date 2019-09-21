package com.example.harrisonaffel.flipmath;

import android.content.Intent;
import android.os.Bundle;
import android.sax.TextElementListener;
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


public class singleOpFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private chooseOperation.OnFragmentInteractionListener mListener;

    public singleOpFragment() {
        // Required empty public constructor
    }

    private problem generateProblem(int operation){
        //todo; add difficulty handling
        problem p = new problem(operation);
        Random rand = new Random();
        int left, right;
        left = rand.nextInt(50);
        right = rand.nextInt(50);
        return new problem(left, right ,operation);
    }
    problem currentproblem;
    private void playGame(int operation){
        currentproblem = generateProblem(operation);
        try{
            TextView l = getView().findViewById(R.id.leftnum2);
            TextView r = getView().findViewById(R.id.rightnum2);
            TextView o = getView().findViewById(R.id.operation2);
            l.setText(String.valueOf(currentproblem.left));
            r.setText(String.valueOf(currentproblem.right));

            switch (currentproblem.operation){
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
        }catch (NullPointerException np){

        }
    }


    int operation = 0;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment singleOpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static singleOpFragment newInstance(String param1, String param2) {
        singleOpFragment fragment = new singleOpFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

boolean started = false;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button submit = getActivity().findViewById(R.id.problemsubmit2);
        Button quit = getActivity().findViewById(R.id.exit2);

        int operation = Integer.parseInt(getActivity().getIntent().getStringExtra("operation"));
        if(!started) {
            playGame(operation);
            started = true;
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView secretsolution = getActivity().findViewById(R.id.secretscore2);
                TextView totalAttempts = getActivity().findViewById(R.id.totalAttempts2);
                TextView roundNum = getActivity().findViewById(R.id.roundNum2);

                int addone;
                int totalattmpt;

                addone = Integer.parseInt(secretsolution.getText().toString()) + 1;
                totalattmpt = Integer.parseInt(totalAttempts.getText().toString()) + 1;


                TextView submission = getActivity().findViewById(R.id.result2);
                String result = submission.getText().toString();

                try {
                    if (Integer.parseInt(result) == currentproblem.solution) {
                        totalAttempts.setText(String.valueOf(totalattmpt));
                        secretsolution.setText(String.valueOf(addone));
                        singleOp round = (singleOp) getActivity();
                        ContentFrameLayout fragment = getActivity().findViewById(R.id.problemfragment2);

                        round.play();
                        singleOpFragment newview = new singleOpFragment();
                        ((singleOp) getActivity()).loadFragment(newview);

                        int num = Integer.parseInt(roundNum.getText().toString());
                        num++;
                        checkWin(num);
                        roundNum.setText(String.valueOf(num));
                    } else {

                        totalattmpt = Integer.parseInt(totalAttempts.getText().toString()) + 1;
                        totalAttempts.setText(String.valueOf(totalattmpt));
                        int num = Integer.parseInt(roundNum.getText().toString());
                        num++;
                        checkWin(num);
                        roundNum.setText(String.valueOf(num));
                        singleOp round = (singleOp) getActivity();
                        round.play();
                    }


                }catch (NumberFormatException fe){

                }
            }

        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getActivity(), mainmenu.class);
                startActivity(home);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_op, container, false);
    }

    private void checkWin(int num){
        if(num>=11){
            Intent gameover = new Intent(getActivity(), summary.class);
            TextView attempts = getActivity().findViewById(R.id.totalAttempts2);
            TextView score = getActivity().findViewById(R.id.secretscore2);
            String val = score.getText().toString();
            String attmpts = attempts.getText().toString();
            gameover.putExtra("score", val);
            gameover.putExtra("Attempts",attmpts);
            startActivity(gameover);
        }
    }


}
