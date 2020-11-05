package sk.richard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Hra extends JPanel implements ActionListener {

    HernaPlocha plocha;
    Hadik hadik;
    Jablko jablko;
    AbstraktneCastiHry castiHry;
    Timer cas;

    public Hra(){
        this.hadik=new Hadik();
        this.jablko=new Jablko();
        this.plocha=new HernaPlocha();
        this.castiHry=this.hadik.getCastiHry();
        this.setPreferredSize(new Dimension(plocha.getSirkaPlochy(), plocha.getVyskaPlochy())); //this vyuyiva metody z triedy JPanel
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new KontrolaPohybu());
        jablko.setHadik(this.hadik);
        zacniHru();

    }

    public void zacniHru(){
        cas=new Timer(this.castiHry.getRychlostHry(),this);
        jablko.generujNoveJablko();
        this.castiHry.setHraBezi(true);
        cas.start();
    }

    public void gameOver(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica",Font.BOLD,80));
        FontMetrics metrics= getFontMetrics(g.getFont());
        g.drawString("Game Over",(plocha.getSirkaPlochy()-metrics.stringWidth("Game Over"))/2,plocha.getVyskaPlochy()/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //pokial hra bezi
        if (this.castiHry.isHraBezi()){
            hadik.pohybHada();
            if (jablko.skotrolujJablko()){//ak had zje jablko zvysi sa rychlost hry(teda zmensi sa cas medzi zobrazeniami)
                cas.setDelay(cas.getDelay()-5);
            }
            hadik.skontrolujKoliziu();
        }
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        zobrazVsetkyObjekty(g);
    }

    public void zobrazVsetkyObjekty(Graphics g){
        if (this.castiHry.isHraBezi()) {
            plocha.zobrazMriezku(g);
            jablko.zobrazJablko(g);
            hadik.zobrazHadika(g);
            jablko.zobrazSkore(g);
        }else {
            cas.stop();
            gameOver(g);
        }
    }

   // vnutrona trieda na kotrolu pohybu
    public class KontrolaPohybu extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                // ak hrac stalci sipku dolava
                case KeyEvent.VK_LEFT:
                    /*hadik sa posunie smerom dolava, len ak povodny smer ktorym momentalne ide nie je pravy,
                    pretoze moze menit smer presne o 90 stupnov (keby sa posunie doprava zmeni smer o 180 stupnov)
                     */
                    if (!hadik.getSmer().equals("doprava")){
                        hadik.setSmer("dolava");
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (!hadik.getSmer().equals("dolava")){
                        hadik.setSmer("doprava");
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (!hadik.getSmer().equals("dole")){
                        hadik.setSmer("hore");
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (!hadik.getSmer().equals("hore")){
                        hadik.setSmer("dole");
                    }
                    break;
            }


        }
    }

}
