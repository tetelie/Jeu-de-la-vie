package fr.elie.jeudelavie;

import fr.elie.jeudelavie.cellule.Cellule;
import fr.elie.jeudelavie.cellule.CelluleEtatMorte;
import fr.elie.jeudelavie.cellule.CelluleEtatVivant;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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

    public static boolean p = true;

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setSize(900,950);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Jeu de la vie");
        frame.setLayout(null);
        JeuDeLaVie jeu = new JeuDeLaVie(100,100);
        jeu.initialiseGrille();
        JeuDeLaVieUI jeuDeLaVieUI = new JeuDeLaVieUI(jeu);
        JeuDeLaVieConsole jeuDeLaVieConsole = new JeuDeLaVieConsole(jeu);
        jeuDeLaVieUI.setBounds(0,20,frame.getWidth(),frame.getHeight());
        jeu.attacheObservateur(jeuDeLaVieUI);
        jeu.attacheObservateur(jeuDeLaVieConsole);

        JLabel txt = new JLabel();


        JSlider vitesse = new JSlider();
        vitesse.setMinimum(1);
        vitesse.setMaximum(15);
        vitesse.setBounds((frame.getWidth()/4) * 2,0, frame.getWidth()/4, 20);
        vitesse.setValue(1);
        vitesse.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txt.setText("vitesse: " + vitesse.getValue());
            }
        });


        txt.setText("vitesse: " + vitesse.getValue());
        txt.setBounds((frame.getWidth()/4) * 3,0, frame.getWidth()/4, 20);
        txt.setHorizontalTextPosition(SwingConstants.CENTER);
        JButton play = new JButton();

        JButton next = new JButton();
        next.setText("next");
        next.setBounds(frame.getWidth()/4, 0, frame.getWidth()/4, 20);
        next.setEnabled(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!p) jeu.calculerGenerationSuivante();
            }
        });

        play.setText("pause");
        play.setSize(frame.getWidth()/4, 20);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p = p ? false : true;
                next.setEnabled(!p);
                play.setText(p ? "pause" : "play");

            }
        });
        frame.add(play);
        frame.add(next);
        frame.add(vitesse);
        frame.add(txt);

        frame.add(jeuDeLaVieUI);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                jeu.calculerGenerationSuivante();
            }
        });


        frame.setVisible(true);

        while(true)
        {
            Thread.sleep(1000/vitesse.getValue());
            if(p) jeu.calculerGenerationSuivante();
        }
    }
}
