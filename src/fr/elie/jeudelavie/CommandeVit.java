package fr.elie.jeudelavie;

import fr.elie.jeudelavie.cellule.Cellule;

public class CommandeVit extends Commande{

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
