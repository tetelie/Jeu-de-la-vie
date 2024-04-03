package fr.elie.jeudelavie;

import fr.elie.jeudelavie.cellule.Cellule;

public class CommandeMeurt extends Commande{

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
