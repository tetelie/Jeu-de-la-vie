package fr.elie.jeudelavie.visiteur;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;

import java.util.ArrayList;
import java.util.List;

public class Visiteur {

    private JeuDeLaVie jeu;
    private String name;

    public static List<Visiteur> visiteurs;

    static {
        visiteurs = new ArrayList<>();
    }

    public Visiteur(JeuDeLaVie jeu, String name)
    {
        this.jeu = jeu;
        this.name = name;
        visiteurs.add(this);
    }

    public void visiteCelluleVivante(Cellule cellule) {

    }

    public void visiteCelluleMorte(Cellule cellule) {

    }

    protected JeuDeLaVie getJeu() {
        return jeu;
    }

    public String getName() {
        return name;
    }

    public static List<Visiteur> getVisiteurs() {
        return visiteurs;
    }

    public static  Visiteur getVisiteurFromName(String name)
    {
        for(Visiteur visiteur : visiteurs)
        {
            if(visiteur.getName().equals(name))
            {
                return visiteur;
            }
        }
        return null;
    }
}
