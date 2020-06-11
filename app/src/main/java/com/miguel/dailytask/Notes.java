package com.miguel.dailytask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Notes extends AppCompatActivity {
    TextView test;
    int saveChangesKey = 1;
    ListView weekDisplay;
    Calendar calendar;
    currentWeekAdapter listAdapter;
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-YYYY");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        weekDisplay = (ListView) findViewById(R.id.weekDisplay);
        listAdapter=new currentWeekAdapter(this,currentWeek(getCurrentDateCalendar()));
        weekDisplay.setAdapter(listAdapter);
        test = (TextView) findViewById(R.id.testChangedDate);
    }
    public void changeWeek(View v){
         //launches the calendar activity  and returns the day choose by the user
        Intent i = new Intent(this, CalendarAct.class);
        startActivityForResult(i,saveChangesKey);
        //after having the day choosen by the user, it should update the list on this class's layout.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode==1){
        Date updatedDate = (Date) data.getSerializableExtra("date");
        //after this it should make a new list with this date, but i will test with a textview first
           test.setText(dayFormat.format(updatedDate));
           //use setCalendar to create the new Calendar and pass in the updatedDate
           weekDisplay.setAdapter(new currentWeekAdapter(this, currentWeek(setCalendar(updatedDate))));
       }
    }
    private Calendar setCalendar(Date date){
        Calendar t = Calendar.getInstance();
        t.set(date.getYear(),date.getMonth(),date.getDate());
        return t;
    }
    private Calendar getCurrentDateCalendar(){
        return Calendar.getInstance();
    }
    public static String[] currentWeek(Calendar calendar){ //returns a string array with the dates from the start to the end of the current week
        String[] datesCurrentWeek = new String[7];
        //current week in year
        int currentWeek =calendar.get(Calendar.WEEK_OF_YEAR); //gets the current week of the year
        int firstDay=calendar.getFirstDayOfWeek(); //gets the first day of the current week
        //date of first day of the current week
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        for(int i = 0;i<7;i++){
            datesCurrentWeek[i]=dayFormat.format(calendar.getTime());
            calendar.add(Calendar.DATE,1);
        }
        return datesCurrentWeek;
    }

}
