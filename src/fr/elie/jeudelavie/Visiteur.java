package fr.elie.jeudelavie;

import fr.elie.jeudelavie.cellule.Cellule;

public class Visiteur {

    private JeuDeLaVie jeu;

    public Visiteur(JeuDeLaVie jeu)
    {
        this.jeu = jeu;
    }

    public void visiteCelluleVivante(Cellule cellule)
    {

    }

    public void visiteCelluleMorte(Cellule cellule)
    {

    }

    protected JeuDeLaVie getJeu() {
        return jeu;
    }
}
