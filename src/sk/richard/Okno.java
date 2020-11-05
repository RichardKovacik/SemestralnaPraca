package sk.richard;

import javax.swing.*;

public class Okno {
    private final JFrame jFrame;

    public Okno(){
        jFrame=new JFrame("Snake Game");
        jFrame.add(new Hra());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.pack();
    }
    //metoda zobrazi okno do stredu obrazovky
    public void zobrazOkno(){
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }
}
