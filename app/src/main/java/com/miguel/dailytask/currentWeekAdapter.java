package com.miguel.dailytask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.Inflater;

public class currentWeekAdapter extends ArrayAdapter<String> {
    TextView item;
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-YYYY");


    public currentWeekAdapter(@NonNull Context context, String[] weekList) {
        super(context,R.layout.activity_current_week_adapter,weekList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.activity_current_week_adapter,parent,false);
        item = (TextView) customView.findViewById(R.id.dateDisplay);
        String date = getItem(position);
        item.setText(date);

        if(item.getText().toString()==currentDate()) //highlight the current day(not working)
        {
            item.setHighlightColor(Color.BLUE);
        }

        return customView;
    }

    public String currentDate(){
        Date currentDay = Calendar.getInstance().getTime();
        String currentTime = dayFormat.format(currentDay);
        return currentTime;
    }
}
