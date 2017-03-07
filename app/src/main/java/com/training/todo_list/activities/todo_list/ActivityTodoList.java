package com.training.todo_list.activities.todo_list;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.training.todo_list.R;
import com.training.todo_list.adapters.TodoAdapter;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.utils.event.TodoEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ActivityTodoList extends AppCompatActivity {

    private TodoManager mTodoManager;

    private TodoAdapter mAdapter;
    private List<Todo> mTodoList;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_act_todo_list);

        String pDescription = "TODO description ";
        Calendar cal = Calendar.getInstance();
        Date pDayCreation = cal.getTime();
        UUID pIdTodoType = UUID.randomUUID();
        boolean pBIsDone = false;
        UUID pId = UUID.randomUUID();

        mTodoManager = new TodoManager();
        mTodoManager.create(pDescription, pDayCreation, pIdTodoType, pBIsDone);

        mTodoList = new ArrayList<>();

        initViews();

    }

    private void initViews() {

        mRecyclerView = (RecyclerView) findViewById(R.id.todo_list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setVisibility(View.VISIBLE);

        mAdapter = new TodoAdapter(this, mTodoList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTodoList = mTodoManager.all();
        mAdapter.updateTodoList(mTodoList);
        mAdapter.notifyDataSetChanged();
    }

    public void askAddTodo(View pView) {
        //Toast.makeText(this, "Ask add todo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ActivityTodoList.this, MainActivity.class);
        intent.putExtra("action", "add");
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TodoEvent.MyEvent event) {
        if (event.getAction().equals("edit")) {
            Intent intent = new Intent(ActivityTodoList.this, MainActivity.class);
            intent.putExtra("action", "edit");
            intent.putExtra("position", event.getPosition());
            startActivity(intent);
        }
    }


    public void askSurprise(View pView) {
        AlertDialog.Builder tBuilder = new AlertDialog.Builder(this);
        tBuilder.setTitle(R.string.dialog_title_surprise);
        tBuilder.setMessage(R.string.dialog_message_surprise);
        tBuilder.setPositiveButton(R.string.confirm, null);
        tBuilder.show();
    }
}
