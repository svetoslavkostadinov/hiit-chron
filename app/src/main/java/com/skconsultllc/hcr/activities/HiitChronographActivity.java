package com.skconsultllc.hcr.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.skconsultllc.hcr.R;
import com.skconsultllc.hcr.models.HiitAction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HiitChronographActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiit_chronograph);
        TextView clockView = findViewById(R.id.clockView);
        Intent intent = getIntent();
        ArrayList<HiitAction> workoutRoutineList = intent.getParcelableArrayListExtra("workoutRoutineList");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        float textSize = screenWidth * 0.04f;
        clockView.setTextSize(textSize);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.S", Locale.UK);
                String currentTime = timeFormat.format(calendar.getTime());
                SpannableString spannableString = new SpannableString(currentTime);
                spannableString.setSpan(new RelativeSizeSpan(0.5f), currentTime.length() - 2, currentTime.length(), 0);
                clockView.setText(spannableString);
                handler.postDelayed(this, 100);
            }
        });
    }
}