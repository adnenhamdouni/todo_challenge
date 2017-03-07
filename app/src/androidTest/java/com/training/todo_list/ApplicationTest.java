package com.training.todo_list;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.managers.TodoTypeManager;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.model.models.TodoType;

import java.util.Date;
import java.util.UUID;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private TodoManager tTodoManager;
    private TodoTypeManager tTodoTypeManager;
    private Todo mTodo;


    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        createApplication();
        tTodoManager = new TodoManager();
        tTodoTypeManager = new TodoTypeManager();
    }

    public void testSaveTodos() throws Exception {

        TodoType tTypeEmergency = new TodoType("Emergency", "#b94a48", UUID.randomUUID());
        TodoType tTypeBeforeYesterday = new TodoType("Before yesterday", "#f89406", UUID.randomUUID());
        TodoType tTypeNormal = new TodoType("Normal", "#3a87ad", UUID.randomUUID());

        Todo tTodo1 = new Todo("Buy milk and eggs",
                new Date(1420106400000L), tTypeNormal.id(), true, UUID.randomUUID());
        Todo tTodo2 = new Todo("Call Superman to repair the internet",
                new Date(1443693600000L), tTypeEmergency.id(), false, UUID.randomUUID());

        tTodoTypeManager.save(tTypeEmergency);
        tTodoTypeManager.save(tTypeBeforeYesterday);
        tTodoTypeManager.save(tTypeNormal);

        tTodoManager.save(tTodo1);
        tTodoManager.save(tTodo2);

        assertNotNull(tTodoManager.all());
    }

    public void testgetAllTodos() throws Exception {

        assertNotNull(tTodoManager.all());
    }

    public void testEditTodo() throws Exception {
        mTodo = tTodoManager.all().get(0);
        mTodo.setSDescription("hello");

        assertEquals(mTodo.description(), "hello");
    }

}