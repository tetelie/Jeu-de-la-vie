package fr.elie.jeudelavie.observateur;

import fr.elie.jeudelavie.JeuDeLaVie;
import fr.elie.jeudelavie.cellule.Cellule;
import fr.elie.jeudelavie.color.ColorMode;
import fr.elie.jeudelavie.color.LifeColorMode;
import fr.elie.jeudelavie.color.LifeReverseColorMode;
import fr.elie.jeudelavie.color.NoColorMode;
import fr.elie.jeudelavie.visiteur.Visiteur;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class JeuDeLaVieUI extends JComponent implements Observateur, Runnable {

    private final JeuDeLaVie jeu;

    private final JFrame frame;

    private final JPanel controlPanel = new JPanel();

    private final int controlPanelHeight = 60;

    private final JSlider vitesse = new JSlider();

    private JButton play = new JButton();
    private JButton next = new JButton();

    private final int vitesse_max = 100;

    public static boolean p = false;


    public JeuDeLaVieUI(JeuDeLaVie jeuDeLaVie) throws InterruptedException {
        this.jeu = jeuDeLaVie;



        frame = new JFrame();
        frame.setSize(500,600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setTitle("Jeu de la vie");
        
        
        JLabel txt = new JLabel("", SwingConstants.CENTER);
        List<String> mode = new ArrayList<>();
        Visiteur.getVisiteurs().forEach(visiteur1 -> mode.add(visiteur1.getName()));
        List<String> colormode = new ArrayList<>();
        ColorMode.getColors().forEach(color -> colormode.add(color.getName()));

        JComboBox selectionMode = new JComboBox(mode.toArray());

        JButton reset = new JButton();

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        JFormattedTextField w = new JFormattedTextField(formatter);
        JTextArea h = new JTextArea(jeu.getyMax()+"");

        JLabel modeJeuLabel = new JLabel("Règle de jeu",  SwingConstants.CENTER);
        JLabel modeCouleurLabel = new JLabel("Mode de couleur",  SwingConstants.CENTER);
        JComboBox modeCouleur = new JComboBox(colormode.toArray());


        vitesse.setMinimum(1);
        vitesse.setMaximum(vitesse_max);
        vitesse.setValue(1);
        vitesse.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txt.setText("vitesse: " + vitesse.getValue()+"/"+vitesse.getMaximum());
            }
        });


        txt.setText("vitesse: " + vitesse.getValue() +"/"+vitesse.getMaximum());
        selectionMode.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println(selectionMode.getSelectedItem());
                    Visiteur visiteur = Visiteur.getVisiteurFromName((String) selectionMode.getSelectedItem());
                    jeu.setVisiteur(visiteur);
                    //jeu.initialiseGrille();
                    p = false;
                    change(p);
                    frame.repaint();
                    actualise();
                }
            }
        });

        modeCouleur.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ColorMode colorMode = ColorMode.getColorModeFromName((String) modeCouleur.getSelectedItem());
                if(colorMode != null)
                {
                    jeu.setColorMode(colorMode);
                }
                frame.repaint();
            }
        });


        reset.setText("réinitialiser");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p=false;
                change(p);
                jeu.initialiseGrille();
                frame.repaint();
                actualise();
            }
        });

        next.setText("suivant");
        next.setEnabled(true);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!p) jeu.calculerGenerationSuivante();
            }
        });

        play.setText("jouer");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p = p ? false : true;
                change(p);

            }
        });

        controlPanel.setSize(frame.getWidth(), controlPanelHeight);
        controlPanel.setLocation(0,0);
        GridLayout grid = new GridLayout(3,3);
        controlPanel.setLayout(grid);
        controlPanel.add(play);
        controlPanel.add(next);
        controlPanel.add(reset);
        controlPanel.add(txt);
        controlPanel.add(modeJeuLabel);
        controlPanel.add(modeCouleurLabel);
        controlPanel.add(vitesse);
        controlPanel.add(selectionMode);
        controlPanel.add(modeCouleur);


        //controlPanel.add(w);
        //controlPanel.add(h);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(null);
        this.setBounds(0,controlPanelHeight,frame.getWidth(),frame.getHeight());
        displayPanel.add(this);



        frame.add(controlPanel);
        frame.add(displayPanel);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                jeu.calculerGenerationSuivante();
            }
        });

        frame.revalidate();
        frame.repaint();

        frame.setVisible(false);

        Thread ts = new Thread(this);
        ts.start();
    }


    @Override
    public void actualise() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        controlPanel.setSize(frame.getWidth(), controlPanelHeight);
        this.setSize(getParent().getWidth(), getParent().getHeight());
        int size = getParent().getWidth()/jeu.getxMax();
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                if (jeu.getGrilleXY(x, y).estVivante()) {
                    Cellule cellule = jeu.getGrilleXY(x,y);
                    g.setColor(jeu.getColorMode().getCelluleColor(cellule));
                    g.fillOval(x * size, y * size, size, size);
                }
            }
        }
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                Thread.sleep(1000/vitesse.getValue());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(p) jeu.calculerGenerationSuivante();
        }
    }

    private void change(boolean p)
    {
        next.setEnabled(!p);
        play.setText(p ? "pause" : "jouer");
    }

    public JFrame getFrame() {
        return frame;
    }
}
