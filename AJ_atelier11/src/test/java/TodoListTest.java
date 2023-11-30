import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoListTest {

    private TodoList todoList;
    private TodoList allReadyFilledTodoList;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        todoList = new TodoList();
        allReadyFilledTodoList = new TodoList();

        task1 = new Task("titre 1","description 1");
        task2 = new Task("titre 2","description 2");

        allReadyFilledTodoList.addTask(task1);
        allReadyFilledTodoList.addTask(task2);
    }

    @Test
    void addTask() {
        assertAll(
                () -> assertTrue(todoList.addTask(task1)),
                () -> assertTrue(todoList.containsTask(task1))
        );
    }

    @Test
    void addNullTask() {
        assertAll(
                () -> assertFalse(todoList.addTask(null)),
                () -> assertFalse(todoList.containsTask(null))
        );
    }

    @Test void addExistingTask() {
        todoList.addTask(task1);
        Task task1Bis = new Task("titre 1","description 1");

        assertFalse(todoList.addTask(task1Bis));
    }

    @Test
    void removeTask() {
        todoList.addTask(task1);
        assertAll(
                () -> assertTrue(todoList.removeTask(task1)),
                () -> assertFalse(todoList.containsTask(task1))
        );
    }

    @Test
    void removeUnexistingTask() {
        todoList.addTask(task1);
        assertFalse(todoList.removeTask(task2));
    }

    @Test
    void removeClonedTask() {
        todoList.addTask(task1);
        Task task1Cloned = new Task("titre 1","description 1");

        assertAll(
                () -> assertTrue(todoList.removeTask(task1Cloned)),
                () -> assertFalse(todoList.containsTask(task1))
        );
    }

    @Test
    void findTask() {
        assertEquals(task1, allReadyFilledTodoList.findTask(task1));
    }

    @Test
    void findUnexistingTask() {
        Task task3 = new Task("title 3", "description task 3");

        assertNull(allReadyFilledTodoList.findTask(task3));
    }

    @Test
    void updateTodoListTask() {
        Task task1Clone = new Task("title 1", "description task 1");
        Task taskOne = new Task("title one", "description task one");

        assertAll(
                () -> assertTrue(allReadyFilledTodoList.updateTask(task1, taskOne)),
                () -> assertTrue(allReadyFilledTodoList.containsTask(taskOne)),
                () -> assertFalse(allReadyFilledTodoList.containsTask(task1Clone))
        );
    }

    @Test
    void updateTodoListUnexistingTask() {
        Task unexistingTask = new Task("title 4", "description task 4");
        Task taskOne = new Task("title one", "description task one");

        assertAll(
                () -> assertFalse(allReadyFilledTodoList.updateTask(unexistingTask, taskOne)),
                () -> assertFalse(allReadyFilledTodoList.containsTask(unexistingTask))
        );
    }

    @Test
    void updateTodoListwithNullTask() {
        assertFalse(allReadyFilledTodoList.updateTask(task2, null));
    }

    @Test
    void updateTodoListWhenCompletedTask() {
        Task completedTask = new Task("title", "description task");
        completedTask.complete();
        Task updatedTask = new Task("title updated", "description task updated");


        assertAll(
                () -> assertFalse(todoList.updateTask(completedTask, updatedTask)),
                () -> assertFalse(todoList.containsTask(updatedTask))
        );
    }
}
