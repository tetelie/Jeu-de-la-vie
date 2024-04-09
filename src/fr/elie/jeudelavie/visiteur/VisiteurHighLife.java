package fr.elie.jeudelavie.visiteur;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;
import fr.elie.jeudelavie.commande.CommandeMeurt;
import fr.elie.jeudelavie.commande.CommandeVit;
import fr.elie.jeudelavie.visiteur.Visiteur;

public class VisiteurHighLife extends Visiteur {


    public VisiteurHighLife(JeuDeLaVie jeu) {
        super(jeu, "High Life");
    }

    @Override
    public void visiteCelluleVivante(Cellule cellule) {
        super.visiteCelluleVivante(cellule);
        int nbVoisins = cellule.nombreVoisinesVivantes(getJeu());
        if(nbVoisins != 2 && nbVoisins != 3)
        {
            getJeu().ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    @Override
    public void visiteCelluleMorte(Cellule cellule) {
        super.visiteCelluleMorte(cellule);
        int nbVoisins = cellule.nombreVoisinesVivantes(getJeu());
        if(nbVoisins == 3 || nbVoisins == 6)
        {
            getJeu().ajouteCommande(new CommandeVit(cellule));
        }
    }
}
