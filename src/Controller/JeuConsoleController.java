package Controller;

import Model.*;
import View.JeuVueConsole;

import java.awt.Color;
import java.util.Scanner;

public class JeuConsoleController {
    private JeuModel model;
    private JeuVueConsole vue;
    private Scanner scanner;

    public JeuConsoleController(JeuModel model, JeuVueConsole vue) {
        this.model = model;
        this.vue = vue;
        this.scanner = new Scanner(System.in);
    }

    public void demanderFormesJoueur() {
        System.out.print("Combien de formes veux-tu créer ? ");
        int nbFormes = scanner.nextInt();

        for (int i = 0; i < nbFormes; i++) {
            System.out.println("Forme #" + (i + 1));
            System.out.print("Type de forme (rectangle/cercle) : ");
            String type = scanner.next();

            System.out.print("x : ");
            int x = scanner.nextInt();
            System.out.print("y : ");
            int y = scanner.nextInt();

            if (type.equalsIgnoreCase("rectangle")) {
                System.out.print("Largeur : ");
                int largeur = scanner.nextInt();
                System.out.print("Hauteur : ");
                int hauteur = scanner.nextInt();

                model.ajouterFormeJoueur(new RectangleForme(x, y, largeur, hauteur, Color.GREEN));
            } else if (type.equalsIgnoreCase("cercle")) {
                System.out.print("Rayon : ");
                int rayon = scanner.nextInt();

                model.ajouterFormeJoueur(new CercleForme(x, y, rayon, Color.GREEN));
            } else {
                System.out.println("Type non reconnu. Forme ignorée.");
                i--; // Refaire la saisie
            }
            System.out.println();
        }
    }
}