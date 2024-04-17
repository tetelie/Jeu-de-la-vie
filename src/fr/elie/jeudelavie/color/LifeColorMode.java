package fr.elie.jeudelavie.color;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;

import java.awt.*;

public class LifeColorMode extends ColorMode{



    Color[] colors = new Color[]{Color.GREEN, Color.YELLOW, Color.orange};

    public LifeColorMode(String name, JeuDeLaVie jeu) {
        super(name, jeu);
    }

    @Override
    public Color getCelluleColor(Cellule cellule) {
        Color color = Color.RED;
        if(cellule.getLife() < colors.length)
        {
            color = colors[cellule.getLife()];
        }
        return color;
    }
}
