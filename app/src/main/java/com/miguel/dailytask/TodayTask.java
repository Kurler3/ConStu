package com.miguel.dailytask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    int indexOfSelectedItem=-1; //doesnt really matter what the initial value is tbh
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
        taskList.setAdapter(new TodayTaskListAdapter(this,tasksTitles));
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                indexOfSelectedItem=position;
            }
        });

    }
    public void updateTaskList(){
        taskList.setAdapter(new TodayTaskListAdapter(this,tasksTitles));
    }
    public void addTask(View v){
        //launches small pop up that takes input for a task title (startActivityForResult)
        startActivityForResult(new Intent(this,AddTaskButton.class),ADD_KEY);
    }
    public void removeTask(View v){
        //removes the selected item in the list(it is highlighted)
        String taskToBeRemoved;
        if(indexOfSelectedItem!=-1) {
            taskToBeRemoved = tasksTitles.get(indexOfSelectedItem);
            Intent i = new Intent(this, RemoveTaskPopUp.class); //launches a small pop to confirm
            i.putExtra("title",taskToBeRemoved);
            startActivityForResult(i,REMOVE_KEY);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_KEY){
            String inputedTaskTitle = data.getStringExtra("input");
            tasksTitles.add(inputedTaskTitle);
            listIsEmpty.setText("");
            updateTaskList(); //updates the list
        }
        else if(requestCode==REMOVE_KEY){
            int confirm_button_choice = (int) data.getIntExtra("confirm",2);
            if(confirm_button_choice==RemoveTaskPopUp.REMOVE_CONFIRMED){
               tasksTitles.remove(indexOfSelectedItem);
               if(tasksTitles.isEmpty()){
                 listIsEmpty.setText("List is empty");
               }
               updateTaskList();
            }


        }


    }
}
