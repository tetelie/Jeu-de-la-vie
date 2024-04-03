package fr.elie.jeudelavie;

public class Cellule {

    private CelluleEtat etat;
    private int x;
    private int y;


    public Cellule(int x, int y, CelluleEtat etat)
    {
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    public int nombreVoisinesVivantes(JeuDeLaVie jeu) {
        int nbVoisinesVivantes = 0;
        for (int x = this.x - 1; x <= this.x + 1; x++) {
            for (int y = this.y - 1; y <= this.y + 1; y++) {
                if (x >= 0 && x < jeu.getxMax() && y >= 0 && y < jeu.getyMax()) {
                    if (!(x == this.x && y == this.y)) { // Exclure la cellule elle-même
                        Cellule cellule = jeu.getGrilleXY(x, y);
                        if (cellule.estVivante()) nbVoisinesVivantes++;
                    }
                }
            }
        }
        return nbVoisinesVivantes;
    }


    public void vit() {
        etat = etat.vit();
    }

    public void meurt() {
        etat = etat.meurt();
    }

    public boolean estVivante() {
        return etat.estVivante();
    }

    public void accepte(Visiteur visiteur)
    {
        this.etat.accepte(visiteur, this);
    }
}
