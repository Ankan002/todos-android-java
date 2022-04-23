package com.exponents.todos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;

public class Details extends AppCompatActivity {

    String todoDetails;
    AppCompatTextView todoDetailsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        starter();

        createHooks();

        stringSetter();
    }

    private void starter(){
        Intent intent = getIntent();
        todoDetails = intent.getStringExtra("todo");
    }

    private void createHooks(){
        todoDetailsView = findViewById(R.id.todo_details);
    }

    private void stringSetter(){
        todoDetailsView.setText(todoDetails);
    }
}