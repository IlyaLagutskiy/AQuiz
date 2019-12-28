package com.example.aquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int numberOfAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button play = findViewById(R.id.ButtonPlay);
        final Button score = findViewById(R.id.ButtonScore);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.Easy:
                        play.setEnabled(true);
                        numberOfAnswers = 2;
                        Toast.makeText(getApplicationContext(), "" + numberOfAnswers,
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Medium:
                        play.setEnabled(true);
                        numberOfAnswers = 3;
                        Toast.makeText(getApplicationContext(), "" + numberOfAnswers,
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Hard:
                        play.setEnabled(true);
                        numberOfAnswers = 4;
                        Toast.makeText(getApplicationContext(), "" + numberOfAnswers,
                                Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intentQuiz = new Intent(MainActivity.this, Quiz.class);
                    intentQuiz.putExtra(KEYS.NUMBER_OF_ANSWERS, numberOfAnswers);
                    startActivity(intentQuiz);
                }
                catch(Exception ex){
                    Log.d(KEYS.LOGS_MAIN, ex.getMessage());
                }
            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentScore = new Intent(MainActivity.this, Score.class);
                startActivity(intentScore);

            }
        });
    }

}
