package com.training.todo_list.utils;

import com.training.todo_list.model.models.Todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


/**
 * Created by adnenhamdouni on 07/03/2017.
 */

public class ContentData {

    public static ArrayList<Todo> getTodoList(){

        ArrayList<Todo> todoList = new ArrayList<>();

        for (int i = 0; i<10; i++) {
            String pDescription = "TODO description "+i;
            Calendar cal = Calendar.getInstance();
            Date pDayCreation = cal.getTime();
            UUID pIdTodoType = UUID.fromString("x20");
            boolean pBIsDone = false;
            UUID pId = UUID.fromString("1");
            Todo todo = new Todo(pDescription, pDayCreation, pIdTodoType, pBIsDone, pId);
            todoList.add(todo);
        }

        return todoList;

    }
}
