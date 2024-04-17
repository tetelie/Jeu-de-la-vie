package fr.elie.jeudelavie.color;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;

import java.awt.*;

public class CyanColorMode extends ColorMode{
    public CyanColorMode(String name, JeuDeLaVie jeu) {
        super(name, jeu);
    }

    @Override
    public Color getCelluleColor(Cellule cellule) {
        return Color.CYAN;
    }
}
