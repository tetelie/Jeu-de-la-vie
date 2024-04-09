package fr.elie.jeudelavie.observateur;

import fr.elie.jeudelavie.JeuDeLaVie;
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




        vitesse.setMinimum(1);
        vitesse.setMaximum(30);
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


        reset.setText("reset");
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

        next.setText("next");
        next.setEnabled(true);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!p) jeu.calculerGenerationSuivante();
            }
        });

        play.setText("play");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p = p ? false : true;
                change(p);

            }
        });

        controlPanel.setSize(frame.getWidth(), controlPanelHeight);
        controlPanel.setLocation(0,0);
        GridLayout grid = new GridLayout(2,3);
        controlPanel.setLayout(grid);
        controlPanel.add(play);
        controlPanel.add(next);
        controlPanel.add(reset);
        controlPanel.add(vitesse);
        controlPanel.add(txt);
        controlPanel.add(selectionMode);
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
        g.setColor(Color.DARK_GRAY);
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                if (jeu.getGrilleXY(x, y).estVivante()) {
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
        play.setText(p ? "pause" : "play");
    }

    public JFrame getFrame() {
        return frame;
    }
}
