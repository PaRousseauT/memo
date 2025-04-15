import Model.*;
import View.*;

import java.awt.Color;

import javax.swing.SwingUtilities;

import Controller.*;
public class Main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			JeuModel model = new JeuModel();
			model.genererFormesModeleAleatoires(3, 400, 300);

			JeuView view = new JeuView(model);
			JeuController controller = new JeuController(model, view);

        });
		
		/*
		 JeuModel model = new JeuModel();
		 JeuVueConsole vue = new JeuVueConsole();
		 JeuConsoleController controller = new JeuConsoleController(model, vue);
		 model.ajouterFormeModele(new RectangleForme(10, 10, 5, 10, Color.GREEN));
		 
		 // 1. Afficher les formes � m�moriser
	     vue.afficherFormesModele(model.getFormesModele(), 100, 30);

	     // 2. Attendre un peu puis effacer
	     try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     vue.nettoyerConsole();

	     // 3. Demander les formes au joueur
	     controller.demanderFormesJoueur();

	     // 4. Afficher les formes du joueur
	     vue.afficherFormesModele(model.getFormesJoueur(), 100, 30);
	        

	     // Calcul du score
	     double score = model.calculerScore();
	     
	     vue.afficheScore(score);
	     */
	     
	}

}
