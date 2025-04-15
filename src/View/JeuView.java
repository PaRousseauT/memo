// === view/JeuView.java ===
package View;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import java.awt.*;
import Model.*;

public class JeuView extends JFrame implements Observer  {
    public ZoneDessinPanel zone1Panel;
    public JPanel zone3Panel;
    public JLabel infoLabel;
    public JButton btnAction;

    public JeuView(JeuModel model) {
        setTitle("Jeu de Mémorisation de Formes");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // === ZONE 2 ===
        infoLabel = new JLabel("Bienvenue !", SwingConstants.CENTER);
        JPanel zone2Panel = new JPanel(new BorderLayout());
        zone2Panel.add(infoLabel, BorderLayout.CENTER);
        add(zone2Panel, BorderLayout.NORTH);

        // === ZONE 1 ===
        zone1Panel = new ZoneDessinPanel(model);
        add(zone1Panel, BorderLayout.CENTER);

        // === ZONE 3 ===
        zone3Panel = new JPanel();
        zone3Panel.setLayout(new BoxLayout(zone3Panel, BoxLayout.Y_AXIS));
        zone3Panel.add(new JLabel("Choix de formes:"));

        JButton btnRectangle = new JButton("Rectangle");
        btnRectangle.addActionListener(e -> zone1Panel.setFormeActive("rectangle"));
        zone3Panel.add(btnRectangle);

        JButton btnCercle = new JButton("Cercle");
        btnCercle.addActionListener(e -> zone1Panel.setFormeActive("cercle"));
        zone3Panel.add(btnCercle);

        add(zone3Panel, BorderLayout.EAST);
        JButton btnEffacer = new JButton("Effacer les formes");
        btnEffacer.addActionListener(e -> model.resetJoueur());

        zone3Panel.add(btnEffacer);

        // === ZONE 4 ===
        JPanel zone4Panel = new JPanel();
        btnAction = new JButton("Démarrer");
        zone4Panel.add(btnAction);
        add(zone4Panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}