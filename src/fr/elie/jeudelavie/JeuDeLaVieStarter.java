package fr.elie.jeudelavie;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JeuDeLaVieStarter implements Observateur{

    private JeuDeLaVie jeu;

    public JeuDeLaVieStarter(JeuDeLaVie jeu, JeuDeLaVieUI jeuDeLaVieUI)
    {
        this.jeu = jeu;
        JFrame frame = new JFrame();
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4,2));
        JSlider densite = new JSlider();
        JLabel densiteTxt = new JLabel("", SwingConstants.CENTER);
        JLabel longueurTxt = new JLabel("Longueur");
        JLabel hauteurTxt = new JLabel("Hauteur");
        JTextField longeur = new JTextField(jeu.getxMax());
        JTextField hauteur = new JTextField(jeu.getyMax());

        JButton play = new JButton("Play");
        JButton quit = new JButton("Quit");

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
        frame.add(densite);
        frame.add(densiteTxt);
        frame.add(play);
        frame.add(quit);
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                frame.setVisible(false);
                double d = (double) densite.getValue() /100;
                jeu.setDensite(d);
                jeu.setxMax(Integer.parseInt(longeur.getText()));
                jeu.setyMax(Integer.parseInt(hauteur.getText()));
                System.out.println(d);
                jeu.initialiseGrille();
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
                jeuDeLaVieUI.setVisible(true);
            }
        });

        frame.setVisible(true);

    }


    @Override
    public void actualise() {

    }
}
