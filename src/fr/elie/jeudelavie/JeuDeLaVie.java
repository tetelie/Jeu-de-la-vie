package fr.elie.jeudelavie;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JeuDeLaVie implements Observable {

    private Cellule[][] grille;
    private int xMax;
    private int yMax;

    private Visiteur visiteur;

    private List<Observateur> observateurs;
    private List<Commande> commandes;

    public JeuDeLaVie(int xMax, int yMax)
    {
        this.xMax = xMax;
        this.yMax = yMax;
        observateurs = new ArrayList<>();
        commandes = new ArrayList<>();
        visiteur = new VisiteurClassique(this);
    }

    public void initialiseGrille()
    {
        grille = new Cellule[xMax][yMax];
        for(int x = 0; x < xMax; x++)
        {
            for(int y = 0; y < yMax; y++)
            {
                double rand = Math.random();
                if(rand >= ((double) 1/2))
                {
                    grille[x][y] = new Cellule(x, y, CelluleEtatVivant.getInstance());
                }else{
                    grille[x][y] = new Cellule(x ,y, CelluleEtatMorte.getInstance());
                }
            }
        }
    }

    public Cellule getGrilleXY(int x, int y)
    {
        return grille[x][y];
    }

    public int getxMax() {
        return xMax;
    }

    public int getyMax() {
        return yMax;
    }

    @Override
    public void attacheObservateur(Observateur o) {
        observateurs.add(o);
    }

    @Override
    public void detacheObservateur(Observateur o) {
        observateurs.remove(o);
    }

    @Override
    public void notifieObservateur() {

    }

    public void ajouteCommande(Commande commande)
    {
        commandes.add(commande);
    }

    public void executeCommandes()
    {
        for(Commande commande : commandes)
        {
            commande.executer();
        }
    }

    public void distribueVisiteur()
    {
        for (int x = 0; x < this.xMax; x++) {
            for (int y = 0; y < this.yMax; y++) {
                getGrilleXY(x,y).accepte(visiteur);
            }
        }
    }

    public void calculerGenerationSuivante()
    {
        commandes.clear();
        distribueVisiteur();
        executeCommandes();
        observateurs.forEach(Observateur::actualise);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(400,430);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Jeu de la vie");
        frame.setLayout(null);
        JeuDeLaVie jeu = new JeuDeLaVie(10,10);
        jeu.initialiseGrille();
        JeuDeLaVieUI jeuDeLaVieUI = new JeuDeLaVieUI(jeu);
        JeuDeLaVieConsole jeuDeLaVieConsole = new JeuDeLaVieConsole(jeu);
        jeuDeLaVieUI.setBounds(0,0,frame.getWidth(),frame.getHeight());
        jeu.attacheObservateur(jeuDeLaVieUI);
        jeu.attacheObservateur(jeuDeLaVieConsole);

        frame.add(jeuDeLaVieUI);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                jeu.calculerGenerationSuivante();
            }
        });


        frame.setVisible(true);
    }
}
