package fr.elie.jeudelavie;

import fr.elie.jeudelavie.cellule.Cellule;

public class VisiteurClassique extends Visiteur{

    public VisiteurClassique(JeuDeLaVie jeu) {
        super(jeu, "Classique");
    }

    @Override
    public void visiteCelluleVivante(Cellule cellule) {
        super.visiteCelluleVivante(cellule);
        int nbVoisins = cellule.nombreVoisinesVivantes(getJeu());
        if(nbVoisins < 2 || nbVoisins > 3)
        {
            getJeu().ajouteCommande(new CommandeMeurt(cellule));
        }
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
