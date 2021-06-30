package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton,button0,button1,button2,button3, playAgain;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    GridLayout gridLayout;
    int numberOfQuestions = 0;
    ConstraintLayout layout;
    TextView resultTextView, pointsTextView, sumTextView, timerTextView;

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        gridLayout.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        generateQuestions();

        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                gridLayout.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
                timerTextView.setText("0s");
                resultTextView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                resultTextView.setText("Your Score!  " + Integer.toString(score) + "/" +
                        Integer.toString(numberOfQuestions));
            }
        }.start();
    }

    public void generateQuestions(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for (int i=0;i<4;i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a + b);
            }else{
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a+b)
                    incorrectAnswer = rand.nextInt(41);
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setBackgroundColor(Color.parseColor("#2FF812"));
            resultTextView.setText("Correct!");
        }else {
            resultTextView.setBackgroundColor(Color.parseColor("#FF040D"));
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
        generateQuestions();
    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        layout.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(findViewById(R.id.button6));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.button);
        layout = findViewById(R.id.gameLayout);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        gridLayout = findViewById(R.id.gridLayout);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgain = findViewById(R.id.button6);
        button0 = findViewById(R.id.button2);
        button1 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button4);
        button3 = findViewById(R.id.button5);
    }
}