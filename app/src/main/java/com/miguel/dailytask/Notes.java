package com.miguel.dailytask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Notes extends AppCompatActivity {
    ListView weekDisplay;
    Calendar calendar;
    currentWeekAdapter listAdapter;
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-YYYY");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        weekDisplay = (ListView) findViewById(R.id.weekDisplay);
        listAdapter=new currentWeekAdapter(this,currentWeek());
        weekDisplay.setAdapter(listAdapter);
    }

    public static String[] currentWeek(){ //returns a string array with the dates from the start to the end of the current week
        String[] datesCurrentWeek = new String[7];


        //current week in year
        Calendar funcCalendar =Calendar.getInstance();
        int currentWeek =funcCalendar.get(Calendar.WEEK_OF_YEAR); //gets the current week of the year
        int firstDay=funcCalendar.getFirstDayOfWeek(); //gets the first day of the current week
        //date of first day of the current week
        funcCalendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        for(int i = 0;i<7;i++){

            datesCurrentWeek[i]=dayFormat.format(funcCalendar.getTime());
            funcCalendar.add(Calendar.DATE,1);
        }
        return datesCurrentWeek;
    }
}
