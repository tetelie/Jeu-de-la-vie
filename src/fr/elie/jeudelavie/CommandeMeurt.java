package fr.elie.jeudelavie;

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
