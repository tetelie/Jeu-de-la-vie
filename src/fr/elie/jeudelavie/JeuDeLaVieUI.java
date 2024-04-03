package fr.elie.jeudelavie;

import javax.swing.*;
import java.awt.*;

public class JeuDeLaVieUI extends JComponent implements Observateur {

    private JeuDeLaVie jeu;

    public JeuDeLaVieUI(JeuDeLaVie jeuDeLaVie)
    {
        this.jeu = jeuDeLaVie;
    }

    @Override
    public void actualise() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        this.setSize(getParent().getWidth(), getParent().getHeight());
        int size = getParent().getWidth()/jeu.getxMax();
        g.setColor(Color.DARK_GRAY);
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                if (jeu.getGrilleXY(x, y).estVivante()) {
                    g.fillOval(x * size, y * size, size, size);
                }
            }
        }
    }
}
