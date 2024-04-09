package fr.elie.jeudelavie.cellule;

import fr.elie.jeudelavie.visiteur.Visiteur;

public class CelluleEtatVivant implements CelluleEtat {

    private static CelluleEtatVivant instance = new CelluleEtatVivant();


    @Override
    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visiteCelluleVivante(cellule);
    }

    @Override
    public CelluleEtat vit() {
        return CelluleEtatVivant.getInstance();
    }

    @Override
    public CelluleEtat meurt() {
        return CelluleEtatMorte.getInstance();
    }

    @Override
    public boolean estVivante() {
        return true;
    }

    public static CelluleEtatVivant getInstance() {
        return instance;
    }
}
