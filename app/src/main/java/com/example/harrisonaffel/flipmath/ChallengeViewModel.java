package com.example.harrisonaffel.flipmath;

import android.arch.lifecycle.ViewModel;

import java.util.Random;





public class ChallengeViewModel extends ViewModel {
    // TODO: Implement the ViewModel




    private problem generateProblem (){
        Random rand = new Random();
        int left, right, operation;
        left = rand.nextInt(100);
        right = rand.nextInt(100);
        operation = rand.nextInt(4);
        return new problem(left, right, operation);
    }
}


