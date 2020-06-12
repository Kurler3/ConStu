package com.miguel.dailytask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class TodayTaskListAdapter extends ArrayAdapter<String> {
    TextView TaskTitleView;
    CheckBox doneBox;
    public TodayTaskListAdapter(@NonNull Context context, ArrayList<String> resource) {
        super(context,R.layout.activity_today_task_list_adapter, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.activity_today_task_list_adapter,parent,false);
        String taskTitle = getItem(position);
        TaskTitleView = (TextView) customView.findViewById(R.id.taskTitle);
        TaskTitleView.setText(taskTitle);
        return customView;
    }
}
