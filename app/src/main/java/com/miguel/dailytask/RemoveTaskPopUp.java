package com.miguel.dailytask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.BundleCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RemoveTaskPopUp extends AppCompatActivity {
    TextView taskTitle;
    Button confirmRemoveBtn;
    Button cancelRemoveBtn;
    final static int REMOVE_CONFIRMED=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_task_pop_up);
        //get the data sent by the launcher
        String getTaskTitle = getIntent().getStringExtra("title");
        //change the dimensions of it


        //set the textView to the title of the task clicked
        taskTitle = (TextView) findViewById(R.id.taskToBeRemovedTitle);
        taskTitle.setText(getTaskTitle);
        //make buttons do their thing
        confirmRemoveBtn = (Button) findViewById(R.id.confirmRemoveBtn);
        cancelRemoveBtn = (Button) findViewById(R.id.cancelRemoveBtn);
        confirmRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent();
                    i.putExtra("confirm",REMOVE_CONFIRMED);
                    setResult(RESULT_OK,i);
                    finish();
            }
        });
        cancelRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




}
