package fr.elie.jeudelavie.commande;

import fr.elie.jeudelavie.cellule.Cellule;
import fr.elie.jeudelavie.commande.Commande;

public class CommandeMeurt extends Commande {

    private final Cellule cellule;

    public CommandeMeurt(Cellule cellule)
    {
        this.cellule =  cellule;
    }

    @Override
    public void executer() {
        super.executer();
        cellule.meurt();
    }
}
