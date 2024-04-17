package fr.elie.jeudelavie.color;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;

import java.awt.*;

public class LifeReverseColorMode extends ColorMode{



    Color[] colors = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW};

    public LifeReverseColorMode(String name, JeuDeLaVie jeu) {
        super(name, jeu);
    }

    @Override
    public Color getCelluleColor(Cellule cellule) {
        Color color = Color.GREEN;
        if(cellule.getLife() < colors.length)
        {
            color = colors[cellule.getLife()];
        }
        return color;
    }
}
