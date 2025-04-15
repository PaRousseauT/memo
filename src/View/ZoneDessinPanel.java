// === view/ZoneDessinPanel.java ===
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Model.*;

public class ZoneDessinPanel extends JPanel {
    private JeuModel model;
    private String formeActive = "rectangle";
    private boolean phaseJoueur = false;
    private boolean visible = false;

    public ZoneDessinPanel(JeuModel model) {
        this.model = model;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!phaseJoueur || !visible) return;

                int x = e.getX();
                int y = e.getY();
                Forme f = null;

                if (formeActive.equals("rectangle")) {
                    f = new RectangleForme(x, y, 50, 30, Color.GREEN);
                } else if (formeActive.equals("cercle")) {
                    f = new CercleForme(x, y, 20, Color.BLUE);
                }

                if (f != null) {
                    model.ajouterFormeJoueur(f);
                    repaint();
                }
            }
        });
    }

    public void setFormeActive(String forme) {
        this.formeActive = forme;
    }

    public void setPhaseJoueur(boolean actif) {
        this.phaseJoueur = actif;
        repaint();
    }

    public void setVisibleZone(boolean visible) {
        this.visible = visible;
        repaint();
    }

    public void resetZone() {
        model.resetJoueur();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!visible) return;

        java.util.List<Forme> aAfficher = phaseJoueur ? model.getFormesJoueur() : model.getFormesModele();
        for (Forme f : aAfficher) {
            f.dessiner(g);
        }
    }
}