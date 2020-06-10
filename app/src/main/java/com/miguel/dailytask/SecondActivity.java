package com.miguel.dailytask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Button notesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        notesBtn = (Button) findViewById(R.id.weeklyNotes);

    }

    public void startNotes(View v){
        Intent i = new Intent(this, Notes.class);
        startActivity(i);
    }
}
