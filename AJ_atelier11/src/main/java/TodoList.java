import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<String> tasks = new ArrayList<>();
    public boolean addTask(String t){
        if (t == null)
            return false;
        if(t.isBlank())
            return false;
        if(tasks.contains(t))
            return false;
        return tasks.add(t);
    }

    public boolean containsTask(String t) {
        return tasks.contains(t);
    }

    public boolean removeTask(String t) {
        if(t == null) return false;
        if(t.isBlank()) return false;
        if(!tasks.contains(t)) return false;
        return tasks.remove(t);
    }
}
