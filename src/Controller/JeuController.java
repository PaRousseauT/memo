
package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


//Gère le déroulement du jeu (10 tours), l’interaction entre modèle et vue, et le calcul des scores.

public class JeuController {

    private enum EtatJeu { ATTENTE, EN_JEU, FINI }
    private EtatJeu etat = EtatJeu.ATTENTE;

    private final JeuModel model;
    private final JeuView view;
    private final List<Double> scores = new ArrayList<>();
    private int tourActuel = 0;
    private final int nbToursTotal = 10;

    public JeuController(JeuModel model, JeuView view) {
        this.model = model;
        this.view = view;

        model.addObserver(view);

        view.btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (etat) {
                    case ATTENTE:
                        lancerJeu();
                        break;
                    case EN_JEU:
                        validerScore();
                        break;
                    case FINI:
                        if (tourActuel < nbToursTotal) {
                            recommencer();
                        } else {
                            redemarrerPartieComplete();
                        }
                        break;
                }
            }
        });
    }

    private void lancerJeu() {
        etat = EtatJeu.EN_JEU;

        model.resetJoueur();
        model.genererFormesModeleAleatoires(3, 400, 300);
        view.zone1Panel.resetZone();

        view.zone1Panel.setVisibleZone(true);
        view.zone1Panel.setPhaseJoueur(false);

        view.infoLabel.setText("Phase de mémorisation...");
        view.btnAction.setEnabled(false);

        System.out.println("[CONSOLE] Formes à mémoriser :");
        for (Forme f : model.getFormesModele()) {
            System.out.println(f.descriptionTextuelle());
        }

        Timer timer = new Timer(5000, e -> {
            view.zone1Panel.setPhaseJoueur(true);
            view.zone1Panel.repaint();

            view.infoLabel.setText("À vous de jouer !");
            view.btnAction.setText("Valider");
            view.btnAction.setEnabled(true);

            System.out.println("[CONSOLE] Phase de dessin du joueur active");
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void validerScore() {
        etat = EtatJeu.FINI;

        System.out.println("[CONSOLE] Formes du joueur :");
        for (Forme f : model.getFormesJoueur()) {
            System.out.println(f.descriptionTextuelle());
        }

        double score = model.calculerScore();
        scores.add(score);
        tourActuel++;

        System.out.println("[CONSOLE] Score obtenu : " + score);

        if (tourActuel < nbToursTotal) {
            view.infoLabel.setText("Score : " + score + " | Tour " + tourActuel + "/" + nbToursTotal);
            view.btnAction.setText("Tour suivant");
        } else {
            double somme = 0;
            for (double s : scores) somme += s;
            double moyenne = somme / scores.size();

            view.infoLabel.setText("Score : " + score + " | Moyenne finale : " + moyenne);
            view.btnAction.setText("Rejouer partie complète");
        }

        view.zone1Panel.setVisibleZone(false);
    }

    private void recommencer() {
        etat = EtatJeu.ATTENTE;
        model.resetJoueur();
        view.zone1Panel.resetZone();
        view.infoLabel.setText("Nouvelle manche !");
        view.btnAction.setText("Démarrer");
    }

    private void redemarrerPartieComplete() {
        scores.clear();
        tourActuel = 0;
        view.infoLabel.setText("Nouvelle partie de 10 tours !");
        view.btnAction.setText("Démarrer");
        etat = EtatJeu.ATTENTE;
    }
} 