package com.miguel.dailytask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Notes extends AppCompatActivity {
    static final int saveChangesKey = 1;
    Button changeCurrentBtn;
    Button changeWeekBtn;
    ListView weekDisplay;
    Calendar calendar;
    currentWeekAdapter listAdapter;
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-YYYY");
    public static String chosenDate = dayFormat.format(getCurrentDate());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        weekDisplay = (ListView) findViewById(R.id.weekDisplay);
        changeCurrentBtn = (Button) findViewById(R.id.currentWeekBtn);
        changeWeekBtn = (Button) findViewById(R.id.changeWeekBtn);
        listAdapter=new currentWeekAdapter(this,currentWeek(getCurrentDateCalendar()));
        weekDisplay.setAdapter(listAdapter);

    }
    public void changeCurrent(View v){
        Calendar t = Calendar.getInstance();
        chosenDate=dayFormat.format(getCurrentDate());
        weekDisplay.setAdapter(new currentWeekAdapter(this,currentWeek(t)));
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
       if(requestCode==saveChangesKey){
           Date updatedDate = (Date) data.getSerializableExtra("date");
           //after this it should make a new list with this date
           // use setCalendar to create the new Calendar and pass in the updatedDate
           chosenDate = dayFormat.format(updatedDate);
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
    public static Date getCurrentDate(){
        Calendar t = Calendar.getInstance();
        return new Date(t.get(Calendar.YEAR),t.get(Calendar.MONTH),t.get(Calendar.DAY_OF_MONTH));
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
