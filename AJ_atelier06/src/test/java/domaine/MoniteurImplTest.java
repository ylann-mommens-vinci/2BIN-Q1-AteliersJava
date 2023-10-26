package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {
    private Moniteur moniteur;
    private Sport sportCompetent;
    private Stage stageValide;
    @BeforeEach
    void setUp() {
        moniteur = new MoniteurImpl("Didier");
        sportCompetent = new SportStub(true);
        stageValide = new StageStub(8,sportCompetent,null);
    }
    private void amenerALEtat(int etat, Moniteur moniteur) {
        for (int i = 1; i <= etat; i++) {
            StageStub stageEnPlus =new StageStub(i, sportCompetent, null);
            moniteur.ajouterStage(stageEnPlus);
        }
    }


    @Test
    @DisplayName("TC1 - ajouterStage()")
    void test1() {
        assertAll("TC1",
                () -> assertEquals(0,moniteur.nombreDeStages()),    //Etat 0
                () -> assertTrue(moniteur.ajouterStage(stageValide)),        //Ajout du stage
                () -> assertTrue(moniteur.contientStage(stageValide)),       //contient le stage
                () -> assertEquals(1, moniteur.nombreDeStages())    //Etat suivant
        );
    }

    @Test
    @DisplayName("TC2 - ajouterStage()")
    void test2() {
        amenerALEtat(1,moniteur);
        assertAll("TC2",
                () -> assertEquals(1, moniteur.nombreDeStages()),   //Etat 1
                () -> assertTrue(moniteur.ajouterStage(stageValide)),        //Ajout du stage
                () -> assertTrue(moniteur.contientStage(stageValide)),       //contient le stage
                () -> assertEquals(2, moniteur.nombreDeStages())    //Etat suivant -> 2
        );
    }

    @Test
    @DisplayName("TC3 - ajouterStage()")
    void test3() {
        amenerALEtat(2,moniteur);
        assertAll("TC3",
                () -> assertEquals(2,moniteur.nombreDeStages()),    //Etat 2
                () -> assertTrue(moniteur.ajouterStage(stageValide)),        //Ajout du stage
                () -> assertTrue(moniteur.contientStage(stageValide)),       //contient le stage
                () -> assertEquals(3, moniteur.nombreDeStages())    //Etat suivant -> 3
        );
    }
    @Test
    @DisplayName("TC4 - ajouterStage()")
    void test4() {
        amenerALEtat(3,moniteur);
        assertAll("TC4",
                () -> assertEquals(3,moniteur.nombreDeStages()),    //Etat 2
                () -> assertTrue(moniteur.ajouterStage(stageValide)),        //Ajout du stage
                () -> assertTrue(moniteur.contientStage(stageValide)),       //contient le stage
                () -> assertEquals(4,moniteur.nombreDeStages())     //Etat suivant -> 4
        );
    }
    @Test
    @DisplayName("TC5 - supprimerStage()")
    void test5() {
        amenerALEtat(3, moniteur);                                      //on l'amene a l'etat 3
        moniteur.ajouterStage(stageValide);                                  //On lui ajoute un stage (état 4)
        assertAll(
                () -> assertTrue(moniteur.supprimerStage(stageValide)),     //on verifie que la suppression renvoie True
                () -> assertFalse(moniteur.contientStage(stageValide)),     //On verifie que le moniteur n'ai plus le stage
                () -> assertEquals(3, moniteur.nombreDeStages())   //on verifie qu'il lui reste que 3 stage
        );
    }

    @Test
    @DisplayName("TC6 - supprimerStage()")
    void test6() {
        amenerALEtat(2,moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(
                () -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertFalse(moniteur.contientStage(stageValide)),
                () -> assertEquals(2,moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC7 - supprimerStage()")
    void test7(){
        amenerALEtat(1,moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(
                () -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertFalse(moniteur.contientStage(stageValide)),
                () -> assertEquals(1,moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC8 - supprimerStage()")
    void test8() {
        moniteur.ajouterStage(stageValide);
        assertAll(
                () -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertFalse(moniteur.contientStage(stageValide)),
                () -> assertEquals(0,moniteur.nombreDeStages())
        );
    }

    @Test
    @DisplayName("TC9 - ajouterStage()")
    void test9() {
        amenerALEtat(3,moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stageValide)),
                () ->assertEquals(4, moniteur.nombreDeStages())
        );
    }
}