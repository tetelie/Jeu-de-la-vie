package fr.elie.jeudelavie;

public interface CelluleEtat {

    public void accepte(Visiteur visiteur, Cellule cellule);

    public CelluleEtat vit();
    public CelluleEtat meurt();

    public boolean estVivante();

}
