import java.util.Objects;

public class Ingredient {
    private String nom;
    private  double prix;

    public Ingredient(String nom, double prix){
        this.nom = nom;
        setPrix(prix);
    }

    public double getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient that)) return false;
        return Double.compare(that.prix, prix) == 0 && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prix);
    }
}

