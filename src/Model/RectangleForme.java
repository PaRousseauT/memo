package Model;

import java.awt.Color;
import java.awt.Graphics;

public class RectangleForme extends Forme {
    private int largeur, hauteur;

    public RectangleForme(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
    
    @Override
    public void dessinerDansGrille(char[][] grille) {
        for (int i = x; i < x + largeur && i < grille.length; i++) {
            for (int j = y; j < y + hauteur && j < grille[0].length; j++) {
                if (i >= 0 && j >= 0) {
                    grille[i][j] = '1'; 
                }
            }
        }
    }


    @Override
    public void dessiner(Graphics g) {
        g.setColor(couleur);
        g.fillRect(x, y, largeur, hauteur);
    }

    @Override
    public double similariteAvec(Forme autre) {
        if (!(autre instanceof RectangleForme)) return 0;

        RectangleForme r = (RectangleForme) autre;
        double dx = Math.abs(x - r.x);
        double dy = Math.abs(y - r.y);
        double dw = Math.abs(largeur - r.largeur);
        double dh = Math.abs(hauteur - r.hauteur);

        // Tol�rances : plus la diff�rence est faible, plus la similarit� est haute
        double simX = Math.max(0, 1 - dx / 50.0);
        double simY = Math.max(0, 1 - dy / 50.0);
        double simW = Math.max(0, 1 - dw / 50.0);
        double simH = Math.max(0, 1 - dh / 50.0);

        return (simX + simY + simW + simH) / 4.0;
    }
    @Override
    public String descriptionTextuelle() {
        return "Rectangle (x="+x+", x="+y+",x="+largeur+",x="+hauteur+")";
    }


}