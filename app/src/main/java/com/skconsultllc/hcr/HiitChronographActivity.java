package com.skconsultllc.hcr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HiitChronographActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiit_chronograph);
        TextView clockView = findViewById(R.id.clockView);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        float textSize = screenWidth * 0.08f;

        clockView.setTextSize(textSize);

        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String currentTime = timeFormat.format(calendar.getTime());

                clockView.setText(currentTime);

                handler.postDelayed(this, 1000);

            }
        });


    }
}