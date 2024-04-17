package fr.elie.jeudelavie.visiteur;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;
import fr.elie.jeudelavie.commande.CommandeMeurt;
import fr.elie.jeudelavie.commande.CommandeVit;

public class VisiteurExplosionChaos extends Visiteur {

    public VisiteurExplosionChaos(JeuDeLaVie jeu) {
        super(jeu, "Explosion & chaos");
    }

    @Override
    public boolean visiteCelluleVivante(Cellule cellule) {
        super.visiteCelluleVivante(cellule);
        int nbVoisins = cellule.nombreVoisinesVivantes(getJeu());
        getJeu().ajouteCommande(new CommandeMeurt(cellule));
        return false;
    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {
        super.visiteCelluleMorte(cellule);
        int nbVoisins = cellule.nombreVoisinesVivantes(getJeu());
        if(nbVoisins == 2)
        {
            getJeu().ajouteCommande(new CommandeVit(cellule));
        }
    }
}
