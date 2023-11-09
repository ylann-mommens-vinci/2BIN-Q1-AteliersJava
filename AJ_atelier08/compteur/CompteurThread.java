package compteur;

public class CompteurThread extends Thread {

    private final String nom;
    private final int max;

    //Cette variable de classe permet de retenir quel CompteurThread
    //a fini de compter le premier.
    private static CompteurThread gagnant;

    public CompteurThread(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {
        //1. Compter jusqu'à max -> A chaque incrémentation, afficher le nom du compteur et son compte actuel.
        for (int i = 0; i <= max ; i++) {
            System.out.println(nom+": "+i);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException exception){
                exception.printStackTrace();
            }
        }
        //2. Quand le compte est terminé, afficher que le compteur a finit de compter

        System.out.println(nom+" a fini de compter jusqu'a "+max);
        if (gagnant==null) gagnant = this;
    }

    public static CompteurThread getGagnant() {
        return gagnant;
    }
}
