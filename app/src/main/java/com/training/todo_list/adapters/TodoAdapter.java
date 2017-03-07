package com.training.todo_list.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.training.todo_list.R;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.utils.event.TodoEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by adnenhamdouni on 07/03/2017.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private Context context;
    private List<Todo> itemsData;

    public TodoAdapter(Context context, List<Todo> itemsData) {
        this.itemsData = itemsData;
        this.context = context;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TodoViewHolder viewHolder = new TodoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_todo, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TodoViewHolder holder, final int position) {

        Log.v("todo", "TodoAdapter:onBindViewHolder ====> position = "+position);

        holder.mTodoTitle.setText(itemsData.get(position).description());

        holder.mTodoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TodoEvent.MyEvent editTodoEvent = new TodoEvent.MyEvent("edit", position);
                EventBus.getDefault().post(editTodoEvent);
            }
        });

    }

    public void updateTodoList(List<Todo> todoslist) {
        itemsData.clear();
        itemsData.addAll(todoslist);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mTodoLayout;
        ImageView mTodoImage;
        TextView mTodoTitle;


        public TodoViewHolder(View itemView) {
            super(itemView);

            mTodoLayout = (LinearLayout) itemView.findViewById(R.id.todo_layout);
            mTodoImage = (ImageView) itemView.findViewById(R.id.todo_delete);
            mTodoTitle = (TextView) itemView.findViewById(R.id.todo_title);

        }
    }

}
