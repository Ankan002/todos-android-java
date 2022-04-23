package com.exponents.todos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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

            try {
                InputMethodManager imm = (InputMethodManager)getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
            catch (Exception e){
                System.out.println(e);
            }

            todos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this, R.style.MyDialogTheme);
                    alert.setMessage("Choose what you want to do for todo: "+todoList.get(i)+ "?");
                    alert.setCancelable(true);
                    alert.setPositiveButton(
                            "Details",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(getApplicationContext(), Details.class);
                                    intent.putExtra("todo", todoList.get(i));
                                    startActivity(intent);
                                }
                            }
                    );
                    alert.setNeutralButton(
                            "Done",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    todoList.remove(i);
                                    todoAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list_component, todoList);
                                    todos.setAdapter(todoAdapter);

                                    if(todoList.size() == 0) removeTodo.setVisibility(View.GONE);
                                }
                            }
                    );
                    alert.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }
                    );
                    alert.create();
                    alert.show();
                }
            });

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