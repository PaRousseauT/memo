package View;

import java.util.Arrays;
import java.util.List;

import Model.Forme;
import Model.JeuModel;

public class JeuVueConsole {
	public void afficherFormesModele( List<Forme> Formes, int largeur, int hauteur) {
	    char[][] grille = new char[largeur][hauteur];

	    for (int i = 0; i < grille.length; i++) {
	        Arrays.fill(grille[i], '0');
	    }

	    for (Forme f : Formes) {
	        f.dessinerDansGrille(grille);
	    }

	    afficherGrille(grille);
	}
	private void afficherGrille(char[][] grille) {
	    for (int j = 0; j < grille[0].length; j++) {
	        for (int i = 0; i < grille.length; i++) {
	            System.out.print(grille[i][j]);
	        }
	        System.out.println();
	    }
	}
	public void nettoyerConsole() {
	    for (int i = 0; i < 50; i++) {
	        System.out.println();
	    }
	}
	public void afficheScore(double score) {
		System.out.println("Score du joueur : " + score);
	}
}
