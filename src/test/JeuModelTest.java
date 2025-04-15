// === TESTS UNITAIRES POUR LE PROJET ===

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Model.JeuModel;
import Model.*;
import java.awt.*;

public class JeuModelTest {

    @Test
    public void testSimilariteCercleIdentiques() {
        Forme c1 = new CercleForme(100, 100, 20, Color.BLUE);
        Forme c2 = new CercleForme(100, 100, 20, Color.BLUE);
        assertEquals(1.0, c1.similariteAvec(c2), 0.001); // même position et taille
    }

    @Test
    public void testSimilariteCercleEloignes() {
        Forme c1 = new CercleForme(100, 100, 20, Color.BLUE);
        Forme c2 = new CercleForme(200, 200, 20, Color.BLUE);
        assertTrue(c1.similariteAvec(c2) < 0.5); // éloignés => score faible
    }

    @Test
    public void testSimilariteRectangleIdentiques() {
        Forme r1 = new RectangleForme(50, 50, 50, 30, Color.RED);
        Forme r2 = new RectangleForme(50, 50, 50, 30, Color.RED);
        assertEquals(1.0, r1.similariteAvec(r2), 0.001);
    }

    @Test
    public void testCalculScoreSimple() {
        JeuModel model = new JeuModel();
        model.ajouterFormeModele(new CercleForme(100, 100, 20, Color.BLUE));
        model.ajouterFormeJoueur(new CercleForme(100, 100, 20, Color.BLUE));

        double score = model.calculerScore();
        assertEquals(100.0, score, 0.01); // correspondance parfaite
    }

    @Test
    public void testCalculScoreAvecErreur() {
        JeuModel model = new JeuModel();
        model.ajouterFormeModele(new RectangleForme(100, 100, 50, 30, Color.RED));
        model.ajouterFormeJoueur(new RectangleForme(200, 200, 50, 30, Color.RED));

        double score = model.calculerScore();
        assertTrue(score < 100.0);
    }

    @Test
    public void testGenererFormesModeleAleatoires() {
        JeuModel model = new JeuModel();
        model.genererFormesModeleAleatoires(5, 400, 300);
        assertEquals(5, model.getFormesModele().size());
    }
}