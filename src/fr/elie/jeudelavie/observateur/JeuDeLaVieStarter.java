package fr.elie.jeudelavie.observateur;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;
import fr.elie.jeudelavie.cellule.CelluleEtatVivant;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class JeuDeLaVieStarter implements Observateur {

    private JeuDeLaVie jeu;

    private  JTextArea config;

    public JeuDeLaVieStarter(JeuDeLaVie jeu, JeuDeLaVieUI jeuDeLaVieUI)
    {
        this.jeu = jeu;
        JFrame frame = new JFrame();
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(5,2));
        JSlider densite = new JSlider();
        JLabel densiteTxt = new JLabel();
        JLabel longueurTxt = new JLabel("Longueur");
        JLabel hauteurTxt = new JLabel("Hauteur");
        JTextField longeur = new JTextField(jeu.getxMax());
        JTextField hauteur = new JTextField(jeu.getyMax());
        JCheckBox custom = new JCheckBox("Utilisé une configuration");
        config = new JTextArea("5:5;6:6;7:4;7:5;7:6; # PLANEUR # \n 48:50;49:50;50:50;51:50;52:50; # Pour faire de beaux motifs #");


        JScrollPane scroll = new JScrollPane (config,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        JButton play = new JButton("Démarrer");
        JButton quit = new JButton("Quitter");

        longeur.setText(jeu.getxMax()+"");
        hauteur.setText(jeu.getyMax()+"");



        densite.setMinimum(0);
        densite.setMaximum(100);
        densite.setValue(50);
        densite.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float i = (float) densite.getValue() / 100;
                densiteTxt.setText("densité: " + String.valueOf(i));
            }
        });

        densiteTxt.setText("densité: 0." + densite.getValue());

        frame.add(longueurTxt);
        frame.add(longeur);
        frame.add(hauteurTxt);
        frame.add(hauteur);
        frame.add(densiteTxt);
        frame.add(densite);
        frame.add(custom);
        frame.add(config);

        frame.add(play);
        frame.add(quit);
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //frame.setVisible(false);
                double d = (double) densite.getValue() /100;
                jeu.setDensite(d);
                jeu.setxMax(Integer.parseInt(longeur.getText()));
                jeu.setyMax(Integer.parseInt(hauteur.getText()));
                System.out.println(d);
                if(custom.isSelected())
                {
                    jeu.setConfig(getCellulesFromConfig());
                }else{
                    jeu.setConfig(new ArrayList<>());
                }
                jeu.initialiseGrille();
                jeuDeLaVieUI.getFrame().repaint();

                jeuDeLaVieUI.getFrame().setVisible(true);
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                System.exit(0);
            }
        });

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //jeuDeLaVieUI.getFrame().setLocationRelativeTo(frame);
                jeuDeLaVieUI.setVisible(true);
            }
        });

        frame.setVisible(true);

    }

    public List<Cellule> getCellulesFromConfig()
    {
        List<Cellule> cellules = new ArrayList<>();
        String content = config.getText();



        String[] coo = content.replaceAll("\n", "").replaceAll(" ", "").replaceAll("#[^#]*#", "").split(";");

        for(String s : coo)
        {
            String[] co = s.split(":");
            cellules.add(new Cellule(Integer.parseInt(co[0]), Integer.parseInt(co[1]), CelluleEtatVivant.getInstance()));
        }
        return cellules;

    }


    @Override
    public void actualise() {

    }
}
