package com.duksung.android_software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView winnerTv1, winnerTv2, winnerTv3;
    int yourScore, myScore;
    String winnerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        winnerTv1 = (TextView) findViewById(R.id.winner_tv1);
        winnerTv2 = (TextView) findViewById(R.id.winner_tv2);
        winnerTv3 = (TextView) findViewById(R.id.winner_tv3);

        yourScore = getIntent().getIntExtra("yourScore", 0);
        myScore = getIntent().getIntExtra("myScore", 0);

        winnerTv2.setText("your score: " + yourScore);
        winnerTv3.setText("my score: " + myScore);

        if (yourScore > myScore) { winnerText = "You Won!";}
        else if (yourScore < myScore) { winnerText = "I Won!"; }
        else if (yourScore == myScore) { winnerText = "Draw!"; }

        winnerTv1.setText(winnerText);


    }
}