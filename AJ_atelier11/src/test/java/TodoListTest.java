import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoListTest {

    private TodoList todoList;

    @BeforeEach
    void setUp() {
        todoList = new TodoList();
    }

    @Test
    void addTask() {
        assertAll(
                () -> assertTrue(todoList.addTask("task 1")),
                () -> assertTrue(todoList.containsTask("task 1"))
        );
    }

    @Test
    void addEmptyTask() {
        assertAll(
                () -> assertFalse(todoList.addTask("")),
                () -> assertFalse(todoList.containsTask("")),
                () -> assertFalse(todoList.addTask(null)),
                () -> assertFalse(todoList.containsTask(null))
        );
    }

    @Test void addExistingTask() {
        todoList.addTask("task 1");
        assertFalse(todoList.addTask("task 1"));
    }

    @Test
    void removeTask() {
        assertAll(
                () -> assertTrue(todoList.addTask("task")),
                () -> assertTrue(todoList.containsTask("task")),
                () -> assertTrue(todoList.removeTask("task")),
                () -> assertFalse(todoList.containsTask("task"))
        );
    }

    @Test
    void removeUnexistingTask() {
        assertAll(
                () -> assertFalse(todoList.removeTask("task")),
                () -> assertFalse(todoList.removeTask("")),
                () -> assertFalse(todoList.removeTask(null))
        );
    }
}
