package com.miguel.dailytask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CalendarAct extends AppCompatActivity {
    CalendarView calendar;
    Button saveChangesBtn;
    Date savedDate;
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-YYYY");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        calendar = (CalendarView) findViewById(R.id.calendarView); //im not sure why this isnt working but its pissing me off
        saveChangesBtn = (Button) findViewById(R.id.saveDateBtn);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //get the date as a string and set savedDate to it.
                savedDate = new Date(year,month,dayOfMonth);

            }
        });

        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent();
                j.putExtra("date",savedDate); //passes in the date choosen in the calendar to this intent's data.
                setResult(RESULT_OK,j);
                finish();
            }
        });
    }




}
