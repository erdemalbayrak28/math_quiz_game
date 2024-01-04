package com.example.mathquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class QuizGame extends AppCompatActivity {
    TextView score,life,time,question;
    EditText answer;
    Button ok,next,pause;
    Random randomnum = new Random();
    int num1,num2;
    int userAnswer,correctAnswer;
    int userScore = 0;
    int userLife=3;
    ImageView img;

    CountDownTimer timer;
    private static final long START_TIME_IN_MILIS = 20000;
    Boolean timer_running;
    long time_left_in_milis = START_TIME_IN_MILIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);

        score = findViewById(R.id.txt_score);
        life = findViewById(R.id.txt_life);
        time = findViewById(R.id.txt_time);
        question = findViewById(R.id.txt_question);
        answer = findViewById(R.id.edt_answer);
        ok = findViewById(R.id.btn_ok);
        next = findViewById(R.id.btn_next);
        img = findViewById(R.id.image_view);

        gameContinue();
        gameSubstraciton();
        gameMultiplication();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = Integer.valueOf(answer.getText().toString());
                pauseTimer();

                if(userAnswer == correctAnswer){
                    userScore = userScore + 10;
                    score.setText("" + userScore);
                    question.setText("Congratulations,Your answer is correct.");

                }
                else{
                    userLife = userLife -1;
                    life.setText("" + userLife);
                    question.setText("Sorry,Your answer is wrong.");
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText("");
                resetTimer();


                if (userLife <= 0) {
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
                    Intent intent_result = new Intent(QuizGame.this, result.class);
                    intent_result.putExtra("score",userScore);
                    startActivity(intent_result);
                    finish();
                }
                else {
                    int randomOperation = randomnum.nextInt(4);

                    switch (randomOperation){
                        case 0:
                            gameContinue();
                            break;
                        case 1:
                            gameSubstraciton();
                            break;
                        case 2:
                            gameMultiplication();
                            break;
                    }
                }
            }
        });
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MediaPlayer mp = MediaPlayer.create(QuizGame.this,R.raw.allaturca);
                mp.start();
                return false;
            }
        });

    }

    public void gameContinue(){
        num1 = randomnum.nextInt(100);
        num2 = randomnum.nextInt(100);

        correctAnswer = num1 + num2;

        question.setText(num1 + " + "+num2);

        startTimer();
    }
    public void gameSubstraciton(){
        num1 = randomnum.nextInt(100);
        num2 = randomnum.nextInt(100);

        correctAnswer = num1 - num2;

        question.setText(num1+"-"+num2);

        startTimer();
    }

    public void gameMultiplication(){
        num1 = randomnum.nextInt(20);
        num2 = randomnum.nextInt(10);

        correctAnswer = num1 * num2;

        question.setText(num1+"*"+num2);

        startTimer();
    }



    public void startTimer() {
        timer = new CountDownTimer(time_left_in_milis,1000) {
            @Override
            public void onTick(long l) {
                time_left_in_milis = l;
                updateText();
            }

            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife = userLife -1;
                life.setText(""+userLife);
                question.setText("Sorry,Times Up!");

            }
        }.start();

        timer_running = true;
    }

    public void updateText() {
        int second =(int) (time_left_in_milis/1000) % 60;
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);

    }

    public void pauseTimer() {
        timer.cancel();
        timer_running = false;

    }

    public void resetTimer() {
        time_left_in_milis = START_TIME_IN_MILIS;
        updateText();


    }



}