package com.skconsultllc.hcr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.service.autofill.CharSequenceTransformation;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.skconsultllc.hcr.R;
import com.skconsultllc.hcr.models.HiitAction;

import java.util.ArrayList;

public class HiitChronographActivity extends AppCompatActivity {

    private TextView clockView;
    private TextView typeView;
    private int activityIterations;
    private int routineIndex;
    ArrayList<HiitAction> workoutRoutineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        routineIndex = 0;
        activityIterations = activityIterations == 0 ? 0 : 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiit_chronograph);
        clockView = findViewById(R.id.countdownView);
        typeView = findViewById(R.id.typeView);
        Intent intent = getIntent();
        workoutRoutineList = intent.getParcelableArrayListExtra("workoutRoutineList");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        float textSize = screenWidth * 0.04f;
        clockView.setTextSize(textSize);
        if (workoutRoutineList != null && activityIterations ==0) {
            startCountdownTimer(workoutRoutineList);
        }

    }

    private void startCountdownTimer(ArrayList<HiitAction> workoutRoutineList) {

        HiitAction routine = workoutRoutineList.get(routineIndex);
        long duration = routine.getDuration();
        long millisecondsDuration = duration * 1000L;

        CountDownTimer countDownTimer = new CountDownTimer(millisecondsDuration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                typeView.setText(routine.getHiitActionType().getType());
                clockView.setText(String.valueOf(Math.round(millisUntilFinished * 0.001f)));
            }

            @Override
            public void onFinish() {
                if (routineIndex < workoutRoutineList.size() - 1) {
                    routineIndex++;
                    startCountdownTimer(workoutRoutineList);
                } else {
                    activityIterations = 1;
                    typeView.setText("ALL DONE!");
                    clockView.setText("Good Job!");
                }

            }
        };

        countDownTimer.start();
    }
}