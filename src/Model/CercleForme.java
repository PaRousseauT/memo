package Model;

import java.awt.Color;
import java.awt.Graphics;

public class CercleForme extends Forme {
    private int rayon;

    public CercleForme(int x, int y, int rayon, Color couleur) {
        super(x, y, couleur);
        this.rayon = rayon;
    }
    
    @Override
    public void dessinerDansGrille(char[][] grille) {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                double distance = Math.sqrt(Math.pow(i - x, 2) + Math.pow(j - y, 2));
                if (distance <= rayon) {
                    grille[i][j] = '1'; 
                }
            }
        }
    }


    @Override
    public void dessiner(Graphics g) {
        g.setColor(couleur);
        g.fillOval(x - rayon, y - rayon, rayon * 2, rayon * 2);
    }

    @Override
    public double similariteAvec(Forme autre) {
        if (!(autre instanceof CercleForme)) return 0;

        CercleForme c = (CercleForme) autre;
        double dx = Math.abs(x - c.x);
        double dy = Math.abs(y - c.y);
        double dr = Math.abs(rayon - c.rayon);

        // Tol�rances arbitraires � ajuster selon ton jeu
        double simX = Math.max(0, 1 - dx / 50.0); // 0 si �cart > 50px
        double simY = Math.max(0, 1 - dy / 50.0);
        double simR = Math.max(0, 1 - dr / 20.0); // rayon : tol�rance plus petite

        return (simX + simY + simR) / 3.0;
    }
    @Override
    public String descriptionTextuelle() {
        return "Cercle (x="+x+", y="+y+", rayon="+rayon+")";
    }


}
