package sk.richard;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Jablko extends JPanel {
    private int jablkoX;
    private int jablkoY;
    private int zjedeneJablka;
    Random random;
    HernaPlocha plocha;
    Blok blok;
    Hadik hadik;

    public Jablko(){
        this.random=new Random();
        this.plocha=new HernaPlocha();
        this.blok=new Blok();
        this.hadik=new Hadik();
    }
    public void generujNoveJablko(){
        jablkoX=random.nextInt((plocha.getSirkaPlochy()/blok.getVelkostBloku()))*blok.getVelkostBloku();
        jablkoY=random.nextInt((plocha.getVyskaPlochy()/blok.getVelkostBloku()))*blok.getVelkostBloku();
    }
    public boolean skotrolujJablko(){
        //ak sa pozicia jablka bude zhodovat s poziciou hlavy hada
        if ((jablkoX==hadik.getX(0))&&(jablkoY==hadik.getY(0))){
            hadik.setDlzkaHada(hadik.getDlzkaHada()+1);
            zjedeneJablka++;
            generujNoveJablko();
            return true;
        }
        return false;


    }
    public void zobrazJablko(Graphics g){
        //zobrazenie jablka na hernu plochu
        g.setColor(Color.red);
        g.fillOval(jablkoX, jablkoY, blok.getVelkostBloku(), blok.getVelkostBloku());

    }
    public void zobrazSkore(Graphics g){
        // zobraznie skore teda vlastne zobrazenie poctu zjedenych jablk
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Helvetica",Font.BOLD,25));
        FontMetrics metrics= this.getFontMetrics(g.getFont());
        String score= "Score: "+zjedeneJablka;
        g.drawString(score,(plocha.getSirkaPlochy()-metrics.stringWidth(score))/2, g.getFont().getSize());

    }

    public void setHadik(Hadik hadik) {
        this.hadik = hadik;
    }
}
