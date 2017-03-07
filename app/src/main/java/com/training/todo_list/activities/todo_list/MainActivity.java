package com.training.todo_list.activities.todo_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.training.todo_list.R;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.models.Todo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private String mAction;
    private Todo mTodo;
    private List<Todo> mTodoList;

    private EditText mTodoEditText;

    private TodoManager mTodoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTodoEditText = (EditText) findViewById(R.id.todo_edittext);

        mAction = getIntent().getStringExtra("action");

        mTodoManager = new TodoManager();

        mTodoList = mTodoManager.all();

        if (mAction.equals("edit")){
            int position = getIntent().getIntExtra("position", 0);
            mTodo = mTodoList.get(position);
            mTodoEditText.setText(mTodo.description());

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todoTitle = mTodoEditText.getText().toString();

                if (mAction.equals("edit")){


                    mTodo.setSDescription(todoTitle);

                } else {
                    Calendar cal = Calendar.getInstance();
                    Date pDayCreation = cal.getTime();
                    UUID pIdTodoType = UUID.randomUUID();
                    boolean pBIsDone = false;
                    UUID pId = UUID.randomUUID();
                    Todo todo = new Todo(todoTitle, pDayCreation, pIdTodoType, pBIsDone, pId);
                    mTodoList.add(todo);
                    mTodoManager.save(todo);
                }

                finish();
            }
        });
    }

}
