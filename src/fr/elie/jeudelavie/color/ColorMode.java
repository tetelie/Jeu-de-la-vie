package fr.elie.jeudelavie.color;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ColorMode {

    static List<ColorMode> colors = new ArrayList<>();

    private String name;

    private JeuDeLaVie jeu;

    public ColorMode(String name, JeuDeLaVie jeu)
    {
        this.name = name;
        this.jeu = jeu;
        colors.add(this);
    }


    public Color getCelluleColor(Cellule cellule)
    {
        return Color.DARK_GRAY;
    }

    public static List<ColorMode> getColors() {
        return colors;
    }

    public static ColorMode getColorModeFromName(String name)
    {
        for(ColorMode c : colors)
        {
            if(c.name.equals(name))
            {
                return c;
            }
        }
        return null;
    }

    public JeuDeLaVie getJeu() {
        return jeu;
    }

    public String getName() {
        return name;
    }
}
