package Model;

import java.awt.Color;
import java.awt.Graphics;


public abstract class Forme {
	 protected int x, y;
	 protected Color couleur;
	 public Forme(int x, int y, Color couleur) {
		 this.x = x;
	     this.y = y;
	     this.couleur = couleur;
	    }

	 public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	public abstract void dessinerDansGrille(char[][] grille);
	public abstract void dessiner(Graphics g);
	public abstract double similariteAvec(Forme autre); // retourne un score entre 0 et 1
	public abstract String descriptionTextuelle();

}
