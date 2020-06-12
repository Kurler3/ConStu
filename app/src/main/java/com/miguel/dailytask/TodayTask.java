package com.miguel.dailytask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TodayTask extends AppCompatActivity {
    TextView title;
    ListView taskList;
    Button addTaskBtn;
    Button removeTaskBtn;
    ArrayList<String> tasksTitles;
    ArrayList<Boolean> checked;
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-YYYY");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_task);
        tasksTitles = new ArrayList<String>();
        tasksTitles.add("No tasks");
        checked = new ArrayList<Boolean>();
        checked.add(false);
        title = (TextView) findViewById(R.id.todaysTaskTitle);
        title.setText(dayFormat.format(Notes.getCurrentDate())); //using already created functions from other classes
        addTaskBtn = (Button) findViewById(R.id.addTaskListBtn);
        removeTaskBtn = (Button) findViewById(R.id.removeTaskBtn);
        taskList = (ListView) findViewById(R.id.taskList);
        //set custom adapter
        taskList.setAdapter(new TodayTaskListAdapter(this,tasksTitles));

    }
    public void addTask(View v){
        //launches small pop up that takes input for a task title
    }
    public void removeTask(View v){
        //launches a small pop up scroll with the titles of the tasks and allows the user to select one and click save
    }
}
