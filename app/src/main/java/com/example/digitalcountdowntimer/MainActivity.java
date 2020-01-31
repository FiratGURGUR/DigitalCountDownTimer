package com.example.digitalcountdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView zaman;
    long startTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zaman = findViewById(R.id.time);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");
        zaman.setTypeface(tf);


        start_countdown_timer();



    }





    private void start_countdown_timer()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
        formatter.setLenient(false);


        String endTime = "18.06.2020, 15:05:36";
        long milliseconds=0;


        Date endDate;
        try {
            endDate = formatter.parse(endTime);
            milliseconds = endDate.getTime();

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        startTime = System.currentTimeMillis();


         new CountDownTimer(milliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                startTime=startTime-1;
                Long serverUptimeSeconds =
                        (millisUntilFinished - startTime) / 1000;

                String daysLeft = String.format("%d", serverUptimeSeconds / 86400);
                //txtViewDays.setText(daysLeft);
                Log.d("daysLeft",daysLeft);

                String hoursLeft = String.format("%d", (serverUptimeSeconds % 86400) / 3600);
                //txtViewHours.setText(hoursLeft);
                Log.d("hoursLeft",hoursLeft);

                String minutesLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) / 60);
                //txtViewMinutes.setText(minutesLeft);
                Log.d("minutesLeft",minutesLeft);

                String secondsLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) % 60);
                //txtViewSecond.setText(secondsLeft);
                Log.d("secondsLeft",secondsLeft);

                zaman.setText(daysLeft+ " gün " + hoursLeft + " : " + minutesLeft + " : " + secondsLeft );

            }

            @Override
            public void onFinish() {

            }
        }.start();


    }


}
