package sk.richard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Hra extends JPanel implements ActionListener {

    private HernaPlocha plocha;
    private Jablko jablko;
    private AbstraktneCastiHry castiHry;
    private Timer cas;

    //this vyuziva metody z triedy JPanel
    public Hra(){
        this.jablko=new Jablko();
        this.plocha=new HernaPlocha();
        this.castiHry=this.jablko.getHadik().getCastiHry();
        this.setPreferredSize(new Dimension(plocha.getSirkaPlochy(), plocha.getVyskaPlochy()));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new ManazerPohybu());
        zacniHru();

    }


    public void zacniHru(){
        cas=new Timer(this.castiHry.getRychlostHry(),this);
        jablko.generujNoveJablko();
        this.castiHry.setHraBezi(true);
        cas.start();
    }

    //napise na obrazovku Game over nastavenym formatom pisma a velkostou
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
            jablko.getHadik().pohybHada();
            //ak had zje jablko zvysi sa rychlost hry(teda zmensi sa cas medzi jednotlivymi zobrazeniami objektov)
            if (jablko.skotrolujJablko()){
                cas.setDelay(cas.getDelay()-5);
            }
            jablko.getHadik().skontrolujKoliziu();
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
            jablko.getHadik().zobrazHadika(g);
            jablko.zobrazSkore(g);
        }else {
            cas.stop();
            jablko.zobrazSkore(g);
            gameOver(g);
        }
    }

   // vnutrona trieda na kotrolu pohybu
    public class ManazerPohybu extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
                switch (key) {
                    // ak hrac stalci sipku dolava
                    case KeyEvent.VK_LEFT:
                    /*hadik sa posunie smerom dolava, len ak povodny smer ktorym momentalne ide nie je pravy,
                    pretoze moze menit smer presne o 90 stupnov (keby sa posunie doprava zmeni smer o 180 stupnov)
                     */
                        if (!jablko.getHadik().getSmer().equals("doprava")) {
                            jablko.getHadik().setSmer("dolava");
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!jablko.getHadik().getSmer().equals("dolava")) {
                            jablko.getHadik().setSmer("doprava");
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (!jablko.getHadik().getSmer().equals("dole")) {
                            jablko.getHadik().setSmer("hore");
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (!jablko.getHadik().getSmer().equals("hore")) {
                            jablko.getHadik().setSmer("dole");
                        }
                        break;
                }
            }
       }
   }


