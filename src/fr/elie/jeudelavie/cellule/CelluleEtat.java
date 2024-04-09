package fr.elie.jeudelavie.cellule;

import fr.elie.jeudelavie.visiteur.Visiteur;

public interface CelluleEtat {

    public void accepte(Visiteur visiteur, Cellule cellule);

    public CelluleEtat vit();
    public CelluleEtat meurt();

    public boolean estVivante();

}
