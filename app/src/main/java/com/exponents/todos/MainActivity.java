package com.exponents.todos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    AppCompatButton addTodoButton, removeTodo;
    EditText newTodo;
    ListView todos;

    ArrayList<String> todoList = new ArrayList<>();
    ArrayAdapter<String> todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createHooks();

        removeTodo.setVisibility(View.GONE);

        actions();
    }

    private void createHooks(){
        addTodoButton = findViewById(R.id.addTodo);
        newTodo = findViewById(R.id.todoText);
        todos = findViewById(R.id.todos);
        removeTodo = findViewById(R.id.removeAllTodo);
    }

    private void actions(){
        addTodoButton.setOnClickListener(this);
        removeTodo.setOnClickListener(this);
    }

    @Override
    public void onClick(View button) {
        if(button.getId() == R.id.addTodo){
            String inputText = newTodo.getText().toString();
            if(inputText.isEmpty()) return;


            todoList.add(0, inputText);

            todoAdapter = new ArrayAdapter<String>(this, R.layout.list_component, todoList);
            todos.setAdapter(todoAdapter);
            removeTodo.setVisibility(View.VISIBLE);

            newTodo.setText("");
        }

        if(button.getId() == R.id.removeAllTodo){
            todoList.clear();
            todoAdapter = new ArrayAdapter<String>(this, R.layout.list_component, todoList);
            todos.setAdapter(todoAdapter);

            removeTodo.setVisibility(View.GONE);
        }
    }
}