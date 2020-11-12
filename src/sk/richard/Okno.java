package sk.richard;

import javax.swing.JFrame;

public class Okno {
    private final JFrame platno;

    public Okno(){
        platno =new JFrame("Snake Game");
        platno.add(new Hra());
        platno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        platno.setResizable(false);
        platno.pack();
    }
    //metoda zobrazi okno do stredu obrazovky
    public void zobrazOkno(){
        platno.setVisible(true);
        platno.setLocationRelativeTo(null);
    }
}
