package com.skconsultllc.hcr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.skconsultllc.hcr.models.HiitAction;
import com.skconsultllc.hcr.models.HiitActionBuilder;
import com.skconsultllc.hcr.models.HiitActionType;

import java.util.ArrayList;


public class WorkoutSetupActivity extends AppCompatActivity {

    private ArrayList<HiitAction> workoutRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_setup);

        this.workoutRoutine = new ArrayList<>();

        HiitActionBuilder builder = new HiitActionBuilder();

        // Create Warmup and Cool off entries
        workoutRoutine.add(
                builder
                        .setHiitActionType(HiitActionType.WARMUP)
                        .setDuration(15)
                        .build()
        );

        workoutRoutine.add(
                builder
                        .setHiitActionType(HiitActionType.COOL_DOWN)
                        .setDuration(15)
                        .build()
        );

        Button addNewWorkoutButton = findViewById(R.id.addNewWorkoutStageButton);
        RadioButton relaxRadioButton = findViewById(R.id.radioRelax);
        NumberPicker minutesPicker = findViewById(R.id.minutesPicker);
        minutesPicker.setMinValue(0);
        minutesPicker.setMaxValue(59);
        minutesPicker.setValue(minutesPicker.getMinValue());
        NumberPicker secondsPicker = findViewById(R.id.secondsPicker);
        secondsPicker.setMinValue(0);
        secondsPicker.setMaxValue(59);
        secondsPicker.setValue(secondsPicker.getMinValue());

        Button startWorkoutButton = findViewById(R.id.startWorkoutButton);
        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( WorkoutSetupActivity.this, HiitChronographActivity.class);
                startActivity(intent);
            }
        });

        addNewWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                int minutes = minutesPicker.getValue();
                int seconds = secondsPicker.getValue();

                int duration = minutes * 60 + seconds;

                insertToWorkoutRoutineList(
                        relaxRadioButton.isChecked() ?
                                builder
                                        .setHiitActionType(HiitActionType.RELAX)
                                        .setDuration(duration)
                                        .build()
                                :
                                builder
                                        .setHiitActionType(HiitActionType.WORKOUT)
                                        .setDuration(duration)
                                        .build()
                );

                Log.i("TAG", "The add button has been clicked");

            }
        });
    }

    private void insertToWorkoutRoutineList(HiitAction workout) {
        int indexToInsert = this.workoutRoutine.size() - 1 ;
        this.workoutRoutine.add(indexToInsert, workout);
    }



}