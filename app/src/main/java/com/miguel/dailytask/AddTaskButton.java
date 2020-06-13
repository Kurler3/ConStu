package com.miguel.dailytask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskButton extends AppCompatActivity {
    Button saveTaskTitle;
    Button cancelAddTaskBtn;
    EditText passTaskTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_button);
        saveTaskTitle = (Button) findViewById(R.id.saveTaskTitleBtn);
        cancelAddTaskBtn = (Button) findViewById(R.id.cancelAddTaskBtn);
        passTaskTitle = (EditText) findViewById(R.id.enterTaskTitleView);

        //make it a small pop up window

        saveTaskTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finished the activity if the title inputed is not valid
                if(passTaskTitle.getText().toString().equals("Task Title") ||passTaskTitle.getText().toString()==null)
                    finish();
                String inputedTaskTitle = passTaskTitle.getText().toString();
                Intent i = new Intent();
                i.putExtra("input",inputedTaskTitle);
                setResult(RESULT_OK,i);
                finish();
            }
        });
        cancelAddTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
