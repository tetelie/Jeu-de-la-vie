package fr.elie.jeudelavie.color;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;

import java.awt.*;

public class RandomColorMode extends ColorMode{

    public RandomColorMode(String name, JeuDeLaVie jeu) {
        super(name, jeu);
    }

    @Override
    public Color getCelluleColor(Cellule cellule) {
        return new Color((int)(Math.random() * 0x1000000));
    }
}
