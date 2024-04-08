package fr.elie.jeudelavie;

import fr.elie.jeudelavie.cellule.Cellule;

public class VisiteurDayNight extends Visiteur{
    public VisiteurDayNight(JeuDeLaVie jeu) {
        super(jeu, "Day & Night");
    }

    @Override
    public void visiteCelluleVivante(Cellule cellule) {
        super.visiteCelluleVivante(cellule);
        int nbVoisins = cellule.nombreVoisinesVivantes(getJeu());
        if(nbVoisins <= 2 || nbVoisins == 5)
        {
            getJeu().ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {
        super.visiteCelluleMorte(cellule);
        int nbVoisins = cellule.nombreVoisinesVivantes(getJeu());
        if(nbVoisins == 3 || nbVoisins >= 6)
        {
            getJeu().ajouteCommande(new CommandeVit(cellule));
        }
    }
}
