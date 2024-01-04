package com.example.mathquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Result;

public class result extends AppCompatActivity {

    TextView result;
    Button btn_play_again,btn_exit;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.txt_result);
        btn_play_again = findViewById(R.id.btn_play);
        btn_exit = findViewById(R.id.btn_id_exit);


        Intent intent_result = getIntent();
        score = intent_result.getIntExtra("score",0);
        String userScore = String.valueOf(score);
        result.setText("Your Score: "+userScore);


        btn_play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_main = new Intent(result.this,MainActivity.class);
                startActivity(intent_main);
                finish();


            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }
}