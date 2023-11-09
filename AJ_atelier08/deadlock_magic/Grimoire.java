package deadlock_magic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Grimoire {



    public enum Incantation {
        FLAMBOIEMENT_ARDENT,
        DANSE_DES_COMETES,
        ECLAT_DU_PHENIX,
        RESONNANCE_DES_SAGES,
        MURMURES_GIVRES,
        VORTEX_ANCESTRAL
    }

    public enum Ceremonie {
        SYMPOSIUM_DES_ILLUSIONS_PRISMATIQUES(Incantation.FLAMBOIEMENT_ARDENT, Incantation.ECLAT_DU_PHENIX, Incantation.MURMURES_GIVRES),
        ASSEMBLEE_DES_ONDES_FRISSONNATES(Incantation.MURMURES_GIVRES, Incantation.VORTEX_ANCESTRAL, Incantation.FLAMBOIEMENT_ARDENT),
        RITUEL_DU_CREPUSCAL_ABYSSAL(Incantation.RESONNANCE_DES_SAGES, Incantation.MURMURES_GIVRES, Incantation.FLAMBOIEMENT_ARDENT);

        final Incantation incantation1;
        final Incantation incantation2;
        final Incantation incantation3;

        Ceremonie(Incantation incantation1, Incantation incantation2, Incantation incantation3) {
            this.incantation1 = incantation1;
            this.incantation2 = incantation2;
            this.incantation3 = incantation3;
        }

        public void debuter() {
            System.out.println(name() + " débute");

            // Utiliser une méthode pour éviter le deadlock
            prendreIncantationsDansOrdre();

            System.out.println(name() + " lancé !");
        }

        private void prendreIncantationsDansOrdre() {
            List<Incantation> incantations = Arrays.asList(incantation1, incantation2, incantation3);
            incantations.sort(Comparator.comparing(Enum::name));

            Incantation incantationN1 = incantations.get(0) ;
            Incantation incantationN2 = incantations.get(1);
            Incantation incantationN3 = incantations.get(2);

            //Une seul incantation peut etre prise a la fois
            synchronized (Grimoire.class) {
                //Un magicien doit acquérir le verrou sur cette incantation avant de pouvoir continuer.
                // Si un autre magicien détient déjà le verrou sur cette incantation, le magicien actuel attendra jusqu'à ce que le verrou soit libéré
                synchronized (incantationN1) {
                    System.out.println(incantationN1 + " pris pour " + name());
                    mediter();
                    synchronized (incantationN2) {
                        System.out.println(incantationN2 + " pris pour " + name());
                        mediter();
                        synchronized (incantationN3) {
                            System.out.println(incantationN3 + " pris pour " + name());
                            mediter();
                        }
                    }
                }
            }
        }


        public void mediter(){
            System.out.println("Méditation");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
