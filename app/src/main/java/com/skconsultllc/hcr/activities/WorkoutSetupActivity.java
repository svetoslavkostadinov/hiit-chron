package com.skconsultllc.hcr.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.skconsultllc.hcr.R;
import com.skconsultllc.hcr.adapters.WorkoutStageListAdapter;
import com.skconsultllc.hcr.models.HiitAction;
import com.skconsultllc.hcr.models.HiitActionBuilder;
import com.skconsultllc.hcr.models.HiitActionType;

import java.util.ArrayList;


public class WorkoutSetupActivity extends AppCompatActivity {

    private ArrayList<HiitAction> workoutRoutine;
    private WorkoutStageListAdapter workoutStageListAdapter;

    private boolean isPortrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isPortrait = true;
        setContentView(R.layout.activity_workout_setup_portrait);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isPortrait = false;
            setContentView(R.layout.activity_workout_setup_landscape);
        }

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
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton relaxRadioButton = findViewById(R.id.radioRelax);
        radioGroup.check(R.id.radioWorkout);
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
                Intent intent = new Intent(WorkoutSetupActivity.this, HiitChronographActivity.class);
                intent.putExtra("workoutRoutineList", workoutRoutine);
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

                Log.i("INFO", "The add button has been clicked");

            }
        });

        ListView workoutAgendaList = findViewById(R.id.listWorkoutStages);
        this.workoutStageListAdapter = new WorkoutStageListAdapter(this, workoutRoutine);
        workoutAgendaList.setAdapter(workoutStageListAdapter);

    }

    private void insertToWorkoutRoutineList(HiitAction workout) {
        int indexToInsert = this.workoutRoutine.size() - 1;
        this.workoutRoutine.add(indexToInsert, workout);
        this.workoutStageListAdapter.notifyDataSetChanged();
        Log.i("TAG", "New item was added");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE && isPortrait) {
            isPortrait = false;
            setContentView(R.layout.activity_workout_setup_landscape);
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT && !isPortrait) {
            isPortrait = true;
            setContentView(R.layout.activity_workout_setup_portrait);
        }

    }
}