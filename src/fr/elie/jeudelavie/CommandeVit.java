package fr.elie.jeudelavie;

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
