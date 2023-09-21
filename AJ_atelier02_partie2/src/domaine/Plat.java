package domaine;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Plat {
    private String nom;
    private int nbPersonnes;
    private  Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes;

    private HashSet<IngredientQuantifie> ingredients;
    private ArrayList<Instruction> recette;

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout){
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.dureeEnMinutes = Duration.ofMinutes(0);

        recette = new ArrayList<>();
        ingredients =new HashSet<>();
    }

    public String getNom() {
        return nom;
    }
    public int getNbPersonnes() {
        return nbPersonnes;
    }
    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }
    public Cout getCout() {
        return cout;
    }
    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    /**
     * insère l’instruction à la position précisée, position commençant à 1.
     * @param position
     * @param instruction
     * @IllegalArgumentException si la position passée est inférieure ou égal à 0
*                                  ou est trop grande par rapport au nombre d’instructions déjà présentes
     */
    public void insererInstruction(int position, Instruction instruction){
        if(position<=0 ||position> recette.size())
            throw new IllegalArgumentException();
    }

    /**
     * ajoute l’instruction en dernier.
     * @param instruction
     */
    public void ajouterInstruction (Instruction instruction){
        recette.add(instruction);
    }

    /**
     * remplace l’instruction à la position précisée par celle en paramètre.
     * @param position
     * @param instruction
     * @return renvoie l’instruction qui a été remplacée
     */
    public Instruction remplacerInstruction (int position, Instruction instruction){
        recette.add(position,instruction);
        return recette.get(position);
    }

    /**
     * supprimer l’instruction à la position précisée
     * @param position
     * @return renvoie l’instruction qui a été supprimée
     */
    public Instruction supprimerInstruction (int position){
        return recette.remove(position);
    }

    /**
     * fournit un itérateur d’instructions ne permettant pas de supprimer des instructions
     *      (la méthode remove de l’itérateur renvoyé doit lancer une UnsupportedOperationException)
     * @return
     */
    public Iterator<Instruction> instructions(){
        //TODO a  verif
        return recette.iterator();
    }


    /**
     * crée un IngrédientQuantifie et l’ajoute si l’ingrédient n’est pas encore présent.
     * @param ingredient
     * @param quantite
     * @param unite
     * @return Cela renvoie false si l’ingrédient est déjà présent
     */
    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){
        IngredientQuantifie iq = new IngredientQuantifie(ingredient,quantite,unite);

        if(ingredients.contains(iq))
            return false;

        ingredients.add(iq);
        return true;
    }

    /**
     * idem précédente.
     * l’unité mise par défaut est NEANT
     * @param ingredient
     * @param quantite
     * @return
     */
    public boolean ajouterIngredient(Ingredient ingredient, int quantite){
        IngredientQuantifie iq = new IngredientQuantifie(ingredient,quantite,Unite.NEANT);
        if(ingredients.contains(iq))
            return false;

        ingredients.add(iq);
        return true;
    }

    /**
     * modifie l’unité et la quantité de l’ingrédient quantifié correpondant à l’ingrédient passé en paramètre.
     * @param ingredient
     * @param quantite
     * @param unite
     * @return renvoie false si l’ingredient n’est pas présent.
     */
    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){
        //TODO a verif
        if(!ingredients.contains(ingredient))
            return false;
        //On boucle dans le hashSet
        for (IngredientQuantifie iq : ingredients) {
            Ingredient x = iq.getIngredient();
            //Quand on tombe sur l'ingredient on le modifie
            if(x.equals(ingredient)){
                iq.setQuantite(quantite);
                iq.setUnite(unite);
                return true;
            }
        }
        return false;
    }

    /**
     * supprime l’ingrédient quantifié correspondant à l’ingrédient passé en paramètre.
     * @param ingredient
     * @return  renvoie false si l’ingredient n’est pas présent
     */
    public boolean supprimerIngredient(Ingredient ingredient){
        //TODO a verif
        if(ingredients.contains(ingredient)){
            return ingredients.remove(ingredient);
        }
        return false;
    }

    /**
     * @param ingredient
     * @return  renvoie l’ingrédient quantifié correspondant à l’ingrédient
     *          ou null si l’ingredient n’est pas présent
     */
    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
        //TODO a verif
        for (IngredientQuantifie iq: ingredients) {
            if(iq.getIngredient().equals(ingredient)){
                return iq;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        for (IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }


    public enum Difficulte{
        X,XX,XXX,XXXX,XXXXX;

        @Override
        public String toString() {
            return super.toString().replaceAll("X","*");
        }
    }
    public enum Cout{
        $("€"),$$("€€"),$$$("€€€"),$$$$("€€€€"),$$$$$("€€€€€");
        private String symbole;
        Cout(String symbole){
            this.symbole=symbole;
        }

        public String getSymbole() {
            return symbole;
        }

        public String toString() {
            return getSymbole();
        }
    }
}
