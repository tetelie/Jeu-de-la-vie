package fr.elie.jeudelavie.visiteur;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;
import fr.elie.jeudelavie.commande.CommandeMeurt;
import fr.elie.jeudelavie.commande.CommandeVit;

public class VisiteurLabyrinthe extends Visiteur {

    public VisiteurLabyrinthe(JeuDeLaVie jeu) {
        super(jeu, "Labyrinthe");
    }

    @Override
    public boolean visiteCelluleVivante(Cellule cellule) {
        super.visiteCelluleVivante(cellule);
        int nbVoisins = cellule.nombreVoisinesVivantes(getJeu());
        if(nbVoisins < 2 || nbVoisins > 5)
        {
            getJeu().ajouteCommande(new CommandeMeurt(cellule));
            return false;
        }
        return true;
    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {
        super.visiteCelluleMorte(cellule);
        int nbVoisins = cellule.nombreVoisinesVivantes(getJeu());
        if(nbVoisins == 3)
        {
            getJeu().ajouteCommande(new CommandeVit(cellule));
        }
    }
}
