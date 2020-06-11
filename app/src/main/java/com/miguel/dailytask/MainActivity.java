package com.miguel.dailytask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Thread.*;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener , GestureDetector.OnGestureListener {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-YYYY");
    private static boolean isInterrupted;

    TextView welcomeText;
    TextView currentDate;
    TextView currentTimeDisplay;
    private GestureDetector detector;
    ConstraintLayout layoutReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome, Miguel");

        currentDate = (TextView) findViewById(R.id.currentDateText);
        currentTimeDisplay = (TextView) findViewById(R.id.currentTime);
        //formatting the current date to be able to display it in the text View
        Date currentDay = Calendar.getInstance().getTime();
        String currentTime = dayFormat.format(currentDay);
        // there is a more simple way to do this
        currentDate.setText(currentTime);
        //gestor detector
        detector = new GestureDetector(this,this);
        layoutReference = (ConstraintLayout) findViewById(R.id.mainScreen);
        layoutReference.setOnTouchListener(this); //whenever the user makes any movement on the screen, it instantly launches the new activity

        //Create a thread to update the time every second.
        updateTime(); //function that starts the thread in the background
    }

    public void updateTime(){
        isInterrupted=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isInterrupted){
                    try {
                        sleep(1000); //updates every 1 sec
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                 currentTimeDisplay.setText(getCurrentTime());
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private static String getCurrentTime(){
        Date currentTime = Calendar.getInstance().getTime();
        return timeFormat.format(currentTime).toString();
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
        detector.onTouchEvent(event);

        return false;
    }
    @Override
    public boolean onDown(MotionEvent e) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        return false;
    }


}
