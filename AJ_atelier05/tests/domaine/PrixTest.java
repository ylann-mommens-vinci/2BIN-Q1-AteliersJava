package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {
    Prix prixAucune;
    Prix prixPub;
    Prix prixSolde;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixPub = new Prix(TypePromo.PUB,0.1);
        prixSolde = new Prix(TypePromo.SOLDE,0.1);

        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);

        prixPub.definirPrix(3,15);
    }
    @ParameterizedTest
    @DisplayName("Test du constructeur de Prix")
    @ValueSource( ints = {0,-1})
    void testConstructeur(int valeur) {
        // Vérifiez que le constructeur de la classe Prix lance une IllegalArgumentException si le type de la promo passée en paramètre est null.
        assertThrows(IllegalArgumentException.class, () -> new Prix(null,0.1))
        ;
        // Vérifiez que le constructeur de la classe Prix lance une IllegalArgumentException si la valeur passée en paramètre est <= 0
        //          (faites un test paramétré afin de faire le test avec plusieurs valeurs).
        assertThrows(IllegalArgumentException.class, () -> new Prix(TypePromo.SOLDE,valeur));
    }

    @Test
    @DisplayName("Test du getter de typePromo avec les bonnes valeurs")
    void getTypePromo() {
        assertNull(prixAucune.getTypePromo());

        assertEquals(TypePromo.PUB,prixPub.getTypePromo());
        assertEquals(TypePromo.SOLDE,prixSolde.getTypePromo());
    }

    @Test
    @DisplayName("Test du getter de valeurPromo avec les bonnes valeurs")
    void getValeurPromo() {
        assertEquals(0,prixAucune.getValeurPromo());
        assertEquals(0.1,prixSolde.getValeurPromo());
        assertEquals(0.1,prixPub.getValeurPromo());
    }

    @ParameterizedTest
    @DisplayName("Test definirPrix() avec mauvaise quantite <=0")
    @ValueSource(ints = {0, -10})
    void definirPrixQuantite(int quantite) {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.definirPrix(quantite,10));
    }

    @ParameterizedTest
    @DisplayName("Test definirPrix() avec mauvaise valeur <=0")
    @ValueSource(ints = {0, -10})
    void definirPrix(int valeur) {
        assertThrows(IllegalArgumentException.class, () -> prixAucune.definirPrix(10,valeur));
    }

    @Test
    @DisplayName("Test definirPrix() avec bonne valeur")
    void definirPrix() {
        assertEquals(10, prixAucune.getPrix(10));
        prixAucune.definirPrix(10,6);
        assertEquals(6,prixAucune.getPrix(10));
    }

    @ParameterizedTest
    @DisplayName("Test getPrix() si quantite <= 0")
    @ValueSource(ints = {0,-1})
    void getPrixQuantite(int quantite) {
        assertThrows(IllegalArgumentException.class,() -> prixAucune.definirPrix(10,quantite));
    }

    @Test
    @DisplayName("Test getPrix() si quantite de prixPub= 2")
    void getPrixPub() {
        assertThrows(QuantiteNonAutoriseeException.class,() -> prixPub.getPrix(2));
    }
    @Test
    @DisplayName("Test getPrix() si quantite  de prixSolde= 2")
    void getPrixSolde() {
        assertThrows(QuantiteNonAutoriseeException.class,() -> prixSolde.getPrix(1));
    }
}