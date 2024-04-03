package fr.elie.jeudelavie.cellule;

import fr.elie.jeudelavie.Visiteur;

public class CelluleEtatMorte implements CelluleEtat{

    private static CelluleEtatMorte instance = new CelluleEtatMorte();

    @Override
    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visiteCelluleMorte(cellule);
    }

    @Override
    public CelluleEtat vit() {
        return CelluleEtatVivant.getInstance();
    }

    @Override
    public CelluleEtat meurt() {
        return this;
    }

    @Override
    public boolean estVivante() {
        return false;
    }

    public static CelluleEtatMorte getInstance() {
        return instance;
    }
}
