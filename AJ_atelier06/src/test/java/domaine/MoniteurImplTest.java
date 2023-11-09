package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import  org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {
    private Moniteur moniteur;
    private Sport sportCompetent;
    private Stage stageValide;
    @BeforeEach
    void setUp() {
        moniteur = new MoniteurImpl("Didier");

        sportCompetent = Mockito.mock(Sport.class);
        Mockito.when(sportCompetent.contientMoniteur(moniteur)).thenReturn(true);

        //On cree le mock
        stageValide = Mockito.mock(Stage.class);
        //On defini le comportement du mock
        Mockito.when(stageValide.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageValide.getMoniteur()).thenReturn(null);
        Mockito.when(stageValide.getNumeroDeSemaine()).thenReturn(8);
    }
    private void amenerALEtat(int etat, Moniteur moniteur) {
        for (int i = 1; i <= etat; i++) {
            Stage stageAjoute = Mockito.mock(Stage.class);

            Mockito.when(stageAjoute.getSport()).thenReturn(sportCompetent);
            Mockito.when(stageAjoute.getMoniteur()).thenReturn(null);
            Mockito.when(stageAjoute.getNumeroDeSemaine()).thenReturn(i);

            moniteur.ajouterStage(stageAjoute);
        }
    }

    @Test
    @DisplayName("TC1 - ajouterStage valide")
    void testMoniteurTC1() {
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(1, moniteur.nombreDeStages()),

                //On verifie que la méthode enregistrerMoniteur a été appellé sur stageValide
                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }
    @Test
    @DisplayName("TC2 - ajouterStage dans une semaine libre")
    void testMoniteurTC2() {
        amenerALEtat(1, moniteur);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(2, moniteur.nombreDeStages()),

                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    @DisplayName("TC3 - ajouterStage dans une semaine libre")
    void testMoniteurTC3() {
        amenerALEtat(2,moniteur);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(3, moniteur.nombreDeStages()),

                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    @DisplayName("TC4 - ajouterStage dans une semaine libre")
    void testMoniteurTC4() {
        amenerALEtat(3,moniteur);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages()),

                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    @DisplayName("TC5 - ajouterStage déjà présent")
    void testMoniteurTC5() {
        amenerALEtat(3,moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stageValide)),
                () -> assertEquals(4,moniteur.nombreDeStages()),

                () -> Mockito.verify(stageValide).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    @DisplayName("TC6 - ajouterStage dans un semaine deja prise")
    void testMoniteurTC6() {
        amenerALEtat(4,moniteur);

        Stage stageMMSemaine = Mockito.mock(Stage.class);
        Mockito.when(stageMMSemaine.getSport()).thenReturn(sportCompetent);
        Mockito.when(stageMMSemaine.getMoniteur()).thenReturn(null);
        Mockito.when(stageMMSemaine.getNumeroDeSemaine()).thenReturn(1);

        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stageMMSemaine)),
                () -> assertFalse(moniteur.contientStage(stageMMSemaine)),
                () -> assertEquals(4, moniteur.nombreDeStages()),

                () -> Mockito.verify(stageMMSemaine,Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }
}