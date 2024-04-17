package fr.elie.jeudelavie.color;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;

import java.awt.*;

public class FutureColorMode extends ColorMode{


    public FutureColorMode(String name, JeuDeLaVie jeu) {
        super(name, jeu);
    }

    @Override
    public Color getCelluleColor(Cellule cellule) {

        if(getJeu().getVisiteur().visiteCelluleVivante(cellule))
        {
            return Color.DARK_GRAY;
        }
        return Color.RED;
    }
}
