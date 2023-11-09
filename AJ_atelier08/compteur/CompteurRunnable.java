package compteur;

import java.util.concurrent.atomic.AtomicInteger;

public class CompteurRunnable implements Runnable {

    private String nom;
    private int max;

    // ajouter un attribut de classe qui retient l'ordre d'arrivée.
    private static AtomicInteger podium = new AtomicInteger();

    @Override
    public void run() {
        // 1. Compter jusqu'à max à chaque incrémentation, afficher le nom du compteur et son compte actuel.
        for (int i = 0; i < max; i++) {
            System.out.println(nom+": "+i);
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        // 2. Quand le compte est terminé, afficher que le compteur a fini de compter et indiquer son ordre d'arrivée.
        System.out.println(nom+" a fini de compter en position "+ podium.incrementAndGet());
    }

    public CompteurRunnable(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

}