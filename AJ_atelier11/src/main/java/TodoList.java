import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Task> tasks = new ArrayList<>();
    public boolean addTask(Task task){
        if (task == null)
            return false;
        if(containsTask(task))
            return false;
        return tasks.add(task);
    }

    public boolean containsTask(Task task) {
        return tasks.contains(task);
    }

    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    public Task findTask(Task task1) {
        return tasks.stream().filter(task -> task.equals(task1)).findFirst().orElse(null);
    }

    public boolean updateTask(Task existingTask, Task updatedTask) {
        if (existingTask == null || updatedTask == null) {
            return false;
        }
        var taskFound = findTask(existingTask);
        if (taskFound == null) {
            return false;
        }
        if (!taskFound.setTitre(updatedTask.getTitre())) {
            return false;
        }
        if (!taskFound.setDescription(updatedTask.getDescription())) {
            return false;
        }
        return true;
    }
}
