package fr.elie.jeudelavie;

import fr.elie.jeudelavie.cellule.Cellule;
import fr.elie.jeudelavie.cellule.CelluleEtatMorte;
import fr.elie.jeudelavie.cellule.CelluleEtatVivant;
import fr.elie.jeudelavie.commande.Commande;
import fr.elie.jeudelavie.observateur.*;
import fr.elie.jeudelavie.visiteur.Visiteur;
import fr.elie.jeudelavie.visiteur.VisiteurClassique;
import fr.elie.jeudelavie.visiteur.VisiteurDayNight;
import fr.elie.jeudelavie.visiteur.VisiteurHighLife;

import java.util.ArrayList;
import java.util.List;

public class JeuDeLaVie implements Observable {

    private Cellule[][] grille;
    private int xMax;
    private int yMax;

    private int generation = 0;
    private double densite = 0.5;

    private Visiteur visiteur;

    private static JeuDeLaVieConsole jeuDeLaVieConsole;

    private List<Observateur> observateurs;
    private List<Commande> commandes;

    public JeuDeLaVie(int xMax, int yMax)
    {
        this.xMax = xMax;
        this.yMax = yMax;
        observateurs = new ArrayList<>();
        commandes = new ArrayList<>();
        visiteur = new VisiteurClassique(this);
        new VisiteurDayNight(this);
        new VisiteurHighLife(this);
    }

    public void initialiseGrille()
    {
        generation = 0;
        grille = new Cellule[xMax][yMax];
        for(int x = 0; x < xMax; x++)
        {
            for(int y = 0; y < yMax; y++)
            {
                double rand = Math.random();
                if(rand < densite)
                {
                    grille[x][y] = new Cellule(x, y, CelluleEtatVivant.getInstance());
                }else{
                    grille[x][y] = new Cellule(x ,y, CelluleEtatMorte.getInstance());
                }
            }
        }
        jeuDeLaVieConsole.actualise();

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
        generation++;
        commandes.clear();
        distribueVisiteur();
        executeCommandes();
        observateurs.forEach(Observateur::actualise);
    }

    public static boolean p = false;

    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public void setyMax(int yMax) {
        this.yMax = yMax;
    }

    public void setDensite(double densite) {
        this.densite = densite;
    }

    public int getGeneration() {
        return generation;
    }

    public static void main(String[] args) throws InterruptedException {
        JeuDeLaVie jeu = new JeuDeLaVie(100,100);
        jeuDeLaVieConsole = new JeuDeLaVieConsole(jeu);
        //jeu.initialiseGrille();
        JeuDeLaVieUI jeuDeLaVieUI = new JeuDeLaVieUI(jeu);
        JeuDeLaVieStarter jeuDeLaVieStarter = new JeuDeLaVieStarter(jeu, jeuDeLaVieUI);
        jeu.attacheObservateur(jeuDeLaVieUI);
        jeu.attacheObservateur(jeuDeLaVieConsole);
        jeu.attacheObservateur(jeuDeLaVieStarter);
    }
}
