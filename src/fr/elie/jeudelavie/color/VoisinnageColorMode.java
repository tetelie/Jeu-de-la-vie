package fr.elie.jeudelavie.color;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;

import java.awt.*;

public class VoisinnageColorMode extends ColorMode{


    public VoisinnageColorMode(String name, JeuDeLaVie jeu) {
        super(name, jeu);
    }

    Color[] colors = new Color[]{Color.BLACK, Color.DARK_GRAY, Color.red, Color.MAGENTA, Color.pink, Color.orange, Color.yellow, Color.GREEN, Color.CYAN};

    @Override
    public Color getCelluleColor(Cellule cellule) {

        return colors[cellule.nombreVoisinesVivantes(getJeu())];
    }
}
