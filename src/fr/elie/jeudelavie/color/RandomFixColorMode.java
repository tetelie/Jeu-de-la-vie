package fr.elie.jeudelavie.color;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;

import java.awt.*;
import java.util.HashMap;

public class RandomFixColorMode extends ColorMode{
    public RandomFixColorMode(String name, JeuDeLaVie jeu) {
        super(name, jeu);
    }

    HashMap<Cellule, Color> colors = new HashMap<>();

    @Override
    public Color getCelluleColor(Cellule cellule) {

        if(!colors.containsKey(cellule)) {
            colors.put(cellule, new Color((int) (Math.random() * 0x1000000)));
        }
        return colors.get(cellule);
    }
}
