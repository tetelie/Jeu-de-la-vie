package fr.elie.jeudelavie.visiteur;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;
import fr.elie.jeudelavie.commande.CommandeMeurt;
import fr.elie.jeudelavie.commande.CommandeVit;
import fr.elie.jeudelavie.visiteur.Visiteur;

public class VisiteurDayNight extends Visiteur {
    public VisiteurDayNight(JeuDeLaVie jeu) {
        super(jeu, "Day & Night");
    }

    @Override
    public boolean visiteCelluleVivante(Cellule cellule) {
        super.visiteCelluleVivante(cellule);
        int nbVoisins = cellule.nombreVoisinesVivantes(getJeu());
        if(nbVoisins <= 2 || nbVoisins == 5)
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
        if(nbVoisins == 3 || nbVoisins >= 6)
        {
            getJeu().ajouteCommande(new CommandeVit(cellule));
        }
    }
}
