package fr.elie.jeudelavie.visiteur;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;
import fr.elie.jeudelavie.commande.CommandeMeurt;
import fr.elie.jeudelavie.commande.CommandeVit;

public class VisiteurMotifRepliquant extends Visiteur {

    public VisiteurMotifRepliquant(JeuDeLaVie jeu) {
        super(jeu, "Motif Répliquant");
    }

    @Override
    public boolean visiteCelluleVivante(Cellule cellule) {
        super.visiteCelluleVivante(cellule);
        int nbVoisins = cellule.nombreVoisinesVivantes(getJeu());
        if(nbVoisins % 2 == 0)
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
        if(nbVoisins % 2 == 1)
        {
            getJeu().ajouteCommande(new CommandeVit(cellule));
        }
    }
}
