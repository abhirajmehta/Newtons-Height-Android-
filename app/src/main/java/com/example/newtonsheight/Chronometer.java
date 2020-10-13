package com.example.newtonsheight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class Chronometer implements Runnable {

    private static final long MIL_TO_MIN = 60000;
    private static final long MIL_TO_HR = 360000;

    private Context mContext;

    private long mStartTime;

    private boolean mIsRunning;
    private TextView mTxtHeight;


    private long since;
    public int millis;
    public int seconds;
    private int minutes;
    private int hours;

    public Chronometer(Context mContext) {
        this.mContext = mContext;
    }

    public void startTimer(){

        mStartTime = System.currentTimeMillis();
        mIsRunning = true;


    }

    public void stopTimer(){

        mIsRunning = false;

    }

    @SuppressLint({"DefaultLocale", "ResourceType"})
    @Override
    public void run() {

        while (mIsRunning){

            since = System.currentTimeMillis() - mStartTime;

            millis = (int) (since % 1000);
            seconds = (int) ((since / 1000) % 60);
            minutes = (int) ((since / MIL_TO_MIN) % 60);
            hours = (int) ((since / MIL_TO_HR) % 24);


            ((MainActivity) mContext).updateTimer(String.format(

                    "%02d:%02d:%02d:%03d",hours,minutes,seconds,millis

            ));
        }

        float mils = (float) (millis / 1000.0);
        float time = seconds + mils;
        float height = (time * time) * 16;
        float heightMeters = (height * 0.3048f) ;

        ((MainActivity) mContext).showHeight(String.format(

                "%f ft",height

        ));

        ((MainActivity) mContext).showHeightInMeters(String.format(

                "%f m",heightMeters

        ));

    }

}
