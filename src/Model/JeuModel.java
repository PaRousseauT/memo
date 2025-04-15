// === model/JeuModel.java ===
package Model;

import java.util.Observable;
import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//Contient les données du jeu : score, formes du modèle et du joueur. Gère aussi les notifications via Observer.

public class JeuModel extends Observable {
    private double score = 0;
    private List<Forme> formesModele = new ArrayList<>();
    private List<Forme> formesJoueur = new ArrayList<>();

    public void ajouterFormeModele(Forme f) {
        formesModele.add(f);
        setChanged();
        notifyObservers();
    }

    public void ajouterFormeJoueur(Forme f) {
        formesJoueur.add(f);
        setChanged();
        notifyObservers();
    }
 // Compare les formes joueur vs modèle pour calculer un score sur 100

    public double calculerScore() {
    	if (this.formesJoueur.size() ==0) {return 0;}
        List<Forme> formesJoueurRestantes = new ArrayList<>(formesJoueur);
        double total = 0;

        for (Forme formeModele : formesModele) {
            Forme meilleurMatch = null;
            double meilleurScore = 0;

            for (Forme tentative : formesJoueurRestantes) {
                double sim = formeModele.similariteAvec(tentative);
                if (sim > meilleurScore) {
                    meilleurScore = sim;
                    meilleurMatch = tentative;
                }
            }

            if (meilleurMatch != null) {
                formesJoueurRestantes.remove(meilleurMatch);
                total += meilleurScore;
            }
        }
        return formesModele.isEmpty() ? 0 : 100.0 * total / formesModele.size();
    }

    public void resetJoueur() {
        score = 0;
        formesJoueur.clear();
        setChanged();
        notifyObservers();
    }
 // Génère une liste de formes aléatoires à mémoriser pour un tour

    public void genererFormesModeleAleatoires(int nbFormes, int largeurZone, int hauteurZone) {
        formesModele.clear();
        Random rand = new Random();

        for (int i = 0; i < nbFormes; i++) {
            int x = rand.nextInt(largeurZone);
            int y = rand.nextInt(hauteurZone);

            if (rand.nextBoolean()) {
                // Rectangle
                ajouterFormeModele(new RectangleForme(x, y, 50, 30, Color.RED));
            } else {
                // Cercle
                ajouterFormeModele(new CercleForme(x, y, 20, Color.BLUE));
            }
        }
    }

    public List<Forme> getFormesModele() { return formesModele; }
    public List<Forme> getFormesJoueur() { return formesJoueur; }
} 
