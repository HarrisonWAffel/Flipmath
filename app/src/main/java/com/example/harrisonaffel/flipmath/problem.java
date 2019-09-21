package com.example.harrisonaffel.flipmath;

import java.util.Random;

public class problem{
    int left, right;
    int operation;
    int solution;
    boolean correct;
    problem(int left, int right, int operation){
        this.left = left;
        this.right = right;
        this.operation = operation;
        this.solution = solve(left, right, operation);
        correct = false;
    }

    public problem(int operation){
        Random rand = new Random();
        this.operation = operation;
        this.left = rand.nextInt(50);
        this.right = rand.nextInt(50);
        this.solution = solve(left, right, operation);
        correct = false;
    }


    private int solve(int left, int right, int operation){

        switch (operation){
            case(0):
                //add
                return left + right;

            case(1):
                //min
                return left - right;

            case(2):
                //mult
                return left * right;
            case(3):
                //div
                return left / right;
        }

        return 0;
    }
}
