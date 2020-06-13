package com.miguel.dailytask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TodayTask extends AppCompatActivity {
    final int ADD_KEY=1;
    final int REMOVE_KEY=2;
    TextView listIsEmpty;
    TextView title;
    ListView taskList;
    Button addTaskBtn;

    static ArrayList<String> tasksTitles;
    ArrayList<Boolean> checked;
    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-YYYY");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_task);
        tasksTitles = new ArrayList<String>();
        checked = new ArrayList<Boolean>();
        checked.add(false);
        title = (TextView) findViewById(R.id.todaysTaskTitle);
        listIsEmpty = (TextView) findViewById(R.id.listIsEmpty);
        listIsEmpty.setText("List is empty");
        title.setText(dayFormat.format(Notes.getCurrentDate())); //using already created functions from other classes
        addTaskBtn = (Button) findViewById(R.id.addTaskListBtn);

        taskList = (ListView) findViewById(R.id.taskList);
        //set custom adapter



    }
    public void addTask(View v){
        //launches small pop up that takes input for a task title (startActivityForResult)
        startActivityForResult(new Intent(this,AddTaskButton.class),ADD_KEY);
    }
    public void removeTask(View v){
        //removes the selected item in the list(it is highlighted)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_KEY){
            String inputedTaskTitle = data.getStringExtra("input");
            tasksTitles.add(inputedTaskTitle);
            listIsEmpty.setText("");
            taskList.setAdapter(new TodayTaskListAdapter(this,tasksTitles)); //updates the list
        }


    }
}
