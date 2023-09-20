import java.util.ArrayList;
import java.util.Iterator;

public abstract class Pizza implements Iterable<Ingredient> {
    public static final double PRIX_BASE = 5;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients;

    public Pizza(String titre, String description){
        this.titre = titre;
        this.description = description;
    }
    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients){
        this(titre,description);
        for (Ingredient i:ingredients) {
            if (ingredients.contains(i))
                throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza");
            this.ingredients.add(i);
        }
    }
    public String getTitre() {
        return titre;
    }
    public String getDescription() {
        return description;
    }
    private boolean ajouter(Ingredient ingredient){
        if(ingredients.contains(ingredient)) return false;
        return ingredients.add(ingredient);
    }
    private boolean supprimer(Ingredient ingredient){
        if(ingredients.contains(ingredient)){
            return ingredients.remove(ingredient);
        }
        return false;
    }
    private double calculerPrix(){
        double prix = PRIX_BASE;
        for (Ingredient i:ingredients) {
            prix +=i.getPrix();
        }
        return prix;
    }
    @Override
    public Iterator<Ingredient> iterator() {
        return ingredients.iterator();
    }
}
