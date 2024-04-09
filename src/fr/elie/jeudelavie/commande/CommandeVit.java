package fr.elie.jeudelavie.commande;

import fr.elie.jeudelavie.cellule.Cellule;
import fr.elie.jeudelavie.commande.Commande;

public class CommandeVit extends Commande {

    private final Cellule cellule;

    public CommandeVit(Cellule cellule)
    {
        this.cellule = cellule;
    }

    @Override
    public void executer() {
        super.executer();
        this.cellule.vit();
    }
}
