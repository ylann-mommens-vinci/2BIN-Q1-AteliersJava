package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;

    private Produit produit1;
    private Produit produit2;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixPub = new Prix(TypePromo.PUB,0.1);
        prixSolde = new Prix(TypePromo.SOLDE,0.5);

        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);

        prixPub.definirPrix(3,15);

        produit1 = new Produit("Sac Dior","Dior","Sac");
        produit2 = new Produit("Ceinture Gucci","Gucci","Accessoires");

        produit1.ajouterPrix(LocalDate.of(2023, 1, 1),prixAucune);
        produit1.ajouterPrix(LocalDate.of(2023, 2, 1),prixPub);
        produit1.ajouterPrix(LocalDate.of(2023, 3, 1),prixSolde);
    }
    @Test
    @DisplayName("Test du constructeur de Produit et des cas null")
    void testConstructeurNull(){
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Produit(null, "x", "x")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Produit("x", null, "x")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Produit("x", "x", null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Produit(null, "x", null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Produit(null, null, "x")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Produit(null, null, null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Produit("x", null, null))
        );
    }
    @Test
    @DisplayName("Test du constructeur de Produit et des cas vide")
    void testConstructeurVide(){
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Produit("","x","x")),
            () -> assertThrows(IllegalArgumentException.class, () -> new Produit("x","","x")),
            () -> assertThrows(IllegalArgumentException.class, () -> new Produit("x","x","")),
            () -> assertThrows(IllegalArgumentException.class, () -> new Produit("x","","")),
            () -> assertThrows(IllegalArgumentException.class, () -> new Produit("","x","")),
            () -> assertThrows(IllegalArgumentException.class, () -> new Produit("","","x")),
            () -> assertThrows(IllegalArgumentException.class, () -> new Produit("","",""))
        );
    }

    @Test
    void getMarque() {
        assertEquals("Dior",produit1.getMarque());
        assertEquals("Gucci",produit2.getMarque());
    }

    @Test
    void getNom() {
        assertEquals("Sac Dior",produit1.getNom());
        assertEquals("Ceinture Gucci", produit2.getNom());
    }

    @Test
    void getRayon() {
        assertEquals("Accessoires",produit2.getRayon());
        assertEquals("Sac",produit1.getRayon());
    }

    @Test
    void ajouterPrix() {
    }

    @Test
    void getPrix() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}