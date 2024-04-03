package fr.elie.jeudelavie;

public class JeuDeLaVieConsole implements Observateur{

    private JeuDeLaVie jeu;

    int generation = 1;

    public JeuDeLaVieConsole(JeuDeLaVie jeu)
    {
        this.jeu = jeu;
    }

    @Override
    public void actualise() {
            int vivantes = 0;
            for(int x = 0; x < jeu.getxMax(); x++)
            {
                for(int y = 0; y < jeu.getyMax(); y++)
                {
                    if(jeu.getGrilleXY(x,y).estVivante()) vivantes++;
                }
            }
            System.out.println("Generation: " + generation + " | Nombre de cellules vivantes: " + vivantes);
            generation++;
    }

}
