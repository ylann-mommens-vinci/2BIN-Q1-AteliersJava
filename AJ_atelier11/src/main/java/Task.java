import java.util.Objects;

public class Task {
    private String titre;
    private String description;
    private boolean completed;

    public Task(String titre, String description){
        if(titre  == null||description==null)
            throw new IllegalArgumentException();
        if(titre.isBlank())
            throw new IllegalArgumentException("Le titre ne peux pas etre vide");

        this.titre = titre;
        this.description = description;
    }

    public boolean complete() {
        if (completed) {
            return false;
        }
        completed = true;
        return true;
    }
    public String getTitre() {
        return titre;
    }
    public String getDescription() {
        return description;
    }
    public boolean setTitre(String newTitle) {
        if (completed) {
            return false;
        }
        if (newTitle == null || newTitle.isBlank()) {
            return false;
        }

        titre = newTitle;
        return true;
    }
    public boolean setDescription(String newDescription) {
        if (newDescription == null)
            return false;
        if (completed) {
            return false;
        }
        description = newDescription;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        if (!Objects.equals(titre, task.titre)) {
            return false;
        }
        return Objects.equals(description, task.description);
    }
    @Override
    public int hashCode() {
        int result = titre != null ? titre.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
