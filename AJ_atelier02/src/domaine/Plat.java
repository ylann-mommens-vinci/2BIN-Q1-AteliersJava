package domaine;

import util.Util;

import java.time.Duration;
import java.util.*;

public class Plat {
    private String nom;
    private int nbPersonnes;
    private  Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes = Duration.ofMinutes(0);


    private List<Instruction> recette = new ArrayList<Instruction>();
    private Set<IngredientQuantifie> ingredients = new HashSet<IngredientQuantifie>();

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout){
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
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
        Util.checkStrictlyPositive(position);
        Util.checkObject(instruction);
        if(position > recette.size()+1)
            throw new IllegalArgumentException();

        recette.add(position-1,instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    /**
     * ajoute l’instruction en dernier.
     * @param instruction
     */
    public void ajouterInstruction (Instruction instruction){
        Util.checkObject(instruction);

        recette.add(instruction);
        dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    /**
     * remplace l’instruction à la position précisée par celle en paramètre.
     * @param position
     * @param instruction
     * @return renvoie l’instruction qui a été remplacée
     */
    public Instruction remplacerInstruction (int position, Instruction instruction){
        Util.checkStrictlyPositive(position);
        Util.checkObject(instruction);
        if(position > recette.size()) throw new IllegalArgumentException();

        Instruction i = recette.set(position-1,instruction);
        dureeEnMinutes = dureeEnMinutes.minus(i.getDureeEnMinutes());
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
        return i;
    }

    /**
     * supprimer l’instruction à la position précisée
     * @param position
     * @return renvoie l’instruction qui a été supprimée
     */
    public Instruction supprimerInstruction (int position){
        Util.checkStrictlyPositive(position);
        if (position > recette.size() ) throw new IllegalArgumentException();

        Instruction instructionSupprimee = recette.remove(position-1);
        dureeEnMinutes = dureeEnMinutes.minus(instructionSupprimee.getDureeEnMinutes());
        return instructionSupprimee;
    }

    /**
     * fournit un itérateur d’instructions ne permettant pas de supprimer des instructions
     *      (la méthode remove de l’itérateur renvoyé doit lancer une UnsupportedOperationException)
     * @return
     */
    public Iterator<Instruction> instructions(){
        return Collections.unmodifiableList(recette).iterator();
    }


    /**
     * crée un IngrédientQuantifie et l’ajoute si l’ingrédient n’est pas encore présent.
     * @param ingredient
     * @param quantite
     * @param unite
     * @return Cela renvoie false si l’ingrédient est déjà présent
     */
    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){
        Util.checkObject(unite);
        Util.checkStrictlyPositive(quantite);
        if (trouverIngredientQuantifie(ingredient) != null) return false;

        ingredients.add(new IngredientQuantifie(ingredient,quantite,unite));
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
        return ajouterIngredient(ingredient,quantite,Unite.NEANT);
    }

    /**
     * modifie l’unité et la quantité de l’ingrédient quantifié correpondant à l’ingrédient passé en paramètre.
     * @param ingredient
     * @param quantite
     * @param unite
     * @return renvoie false si l’ingredient n’est pas présent.
     */
    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){
        Util.checkObject(unite);
        Util.checkStrictlyPositive(quantite);
        IngredientQuantifie ingredientQuantifie = trouverIngredientQuantifie(ingredient);
        if (ingredientQuantifie == null) return false;

        ingredientQuantifie.setQuantite(quantite);
        ingredientQuantifie.setUnite(unite);
        return true;
    }

    /**
     * supprime l’ingrédient quantifié correspondant à l’ingrédient passé en paramètre.
     * @param ingredient
     * @return  renvoie false si l’ingredient n’est pas présent
     */
    public boolean supprimerIngredient(Ingredient ingredient){
        IngredientQuantifie ingredientQuantifie = trouverIngredientQuantifie(ingredient);
        if (ingredientQuantifie == null) return false;
        return ingredients.remove(ingredientQuantifie);
    }

    /**
     * @param ingredient
     * @return  renvoie l’ingrédient quantifié correspondant à l’ingrédient
     *          ou null si l’ingredient n’est pas présent
     */
    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
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
        $,$$,$$$,$$$$,$$$$$;
        @Override
        public String toString() {
            return super.toString().replace("$","€");
        }
    }
}
