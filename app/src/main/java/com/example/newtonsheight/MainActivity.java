package com.example.newtonsheight;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtTimer;
    private Button mBtnStart;
    private Button mBtnStop;
    private Button mBtnReset;
    private TextView mTxtHeight;
    private TextView mTxtHeightInM;

    private Context mContext;
    private Chronometer mChronometer;
    private Thread mThreadChrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mContext = this;

        mTxtTimer = findViewById(R.id.timer);
        mBtnStart = findViewById(R.id.startBtn);
        mBtnStop = findViewById(R.id.stopBtn);
        mBtnReset = findViewById(R.id.resetBtn);
        mTxtHeight = findViewById(R.id.height);
        mTxtHeightInM = findViewById(R.id.heightInMeters);

        mTxtHeight.setVisibility(View.GONE);
        mTxtHeightInM.setVisibility(View.GONE);
        mBtnReset.setVisibility(View.GONE);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mChronometer == null){

                    mChronometer = new Chronometer(mContext);
                    mThreadChrono = new Thread(mChronometer);
                    mThreadChrono.start();
                    mChronometer.startTimer();

                }


            }
        });

        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mChronometer != null){

                    mChronometer.stopTimer();
                    mThreadChrono.interrupt();
                    mThreadChrono = null;
                    mChronometer = null;
                    mBtnReset.setVisibility(View.VISIBLE);

                }
            }
        });

        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mChronometer == null){

                    mTxtTimer.setText("00:00:00:000");
                    mTxtHeight.setVisibility(View.GONE);
                    mTxtHeightInM.setVisibility(View.GONE);
                    mBtnReset.setVisibility(View.GONE);

                }

            }
        });

    }


    public void updateTimer(final String time){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTxtTimer.setText(time);
            }
        });

    }

    public void showHeight(final String height){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTxtHeight.setText(height);
                mTxtHeight.setVisibility(View.VISIBLE);
            }
        });

    }

    public void showHeightInMeters(final String height){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTxtHeightInM.setText(height);
                mTxtHeightInM.setVisibility(View.VISIBLE);
            }
        });

    }

    public void onClick(View view) {

        Intent intent = new Intent(this,DisplayConditionsActivity.class);
        startActivity(intent);

    }
}
