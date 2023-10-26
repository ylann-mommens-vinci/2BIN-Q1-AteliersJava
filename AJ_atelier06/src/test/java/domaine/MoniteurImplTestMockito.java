package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTestMockito {
    private Sport sportCompetent;
    private Stage stageValide;
    private Moniteur moniteur;

    @BeforeEach
    void setUp() {
        //On init le moniteur
        moniteur = new MoniteurImpl("Didier");

        //On cree les mock object
        sportCompetent = Mockito.mock(Sport.class);
        stageValide = Mockito.mock(Stage.class);

        //On init les comportements des mock
        Mockito.when(sportCompetent.contientMoniteur(moniteur)).thenReturn(true);

        Mockito.when(stageValide.getMoniteur()).thenReturn(null);
        Mockito.when(stageValide.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageValide.getNumeroDeSemaine()).thenReturn(10);
    }

    private void amenerALEtat(int etat,Moniteur moniteur){
        for (int i = 0; i <= etat; i++) {
            Stage stageEnPlus = Mockito.mock(Stage.class);

            Mockito.when(stageEnPlus.getMoniteur()).thenReturn(null);
            Mockito.when(stageEnPlus.getNumeroDeSemaine()).thenReturn(i);
            Mockito.when(stageEnPlus.getSport()).thenReturn(sportCompetent);

            moniteur.ajouterStage(stageEnPlus);
        }
    }


    @Test
    @DisplayName("TC1 - ajouterStage()")
    void testMockito1TC1(){
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),

                () -> assertEquals(1,moniteur.nombreDeStages()),
                //On verifie que la méthode enregistrerMoniteur(moniteur) a été appellé sur stage Valide
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }



}
