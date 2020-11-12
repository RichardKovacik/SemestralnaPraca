package sk.richard;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Jablko extends JPanel {
    private int jablkoX;
    private int jablkoY;
    private int zjedeneJablka;
    private Random random;
    private HernaPlocha plocha;
    private Hadik hadik;

    public Jablko(){
        this.random=new Random();
        this.plocha=new HernaPlocha();
        this.hadik=new Hadik();
    }
    // vygeneruje jablko na nahodnu poziciu na hernu plochu
    public void generujNoveJablko(){
        jablkoX=random.nextInt((plocha.getSirkaPlochy()/plocha.getVelkostBloku()))*plocha.getVelkostBloku();
        jablkoY=random.nextInt((plocha.getVyskaPlochy()/plocha.getVelkostBloku()))*plocha.getVelkostBloku();
    }
    public boolean skotrolujJablko(){
        //ak sa pozicia jablka bude zhodovat s poziciou hlavy hadika, teda ked hadik zje jablko
        if ((jablkoX==hadik.getX(0))&&(jablkoY==hadik.getY(0))){
            hadik.setDlzkaHada(hadik.getDlzkaHada()+1);
            zjedeneJablka++;
            generujNoveJablko();
            return true;
        }
        return false;

    }
    //zobrazenie jablka na hernu plochu
    public void zobrazJablko(Graphics g){
        g.setColor(Color.red);
        g.fillOval(jablkoX, jablkoY, plocha.getVelkostBloku(), plocha.getVelkostBloku());

    }
    // zobraznie skore teda vlastne zobrazenie poctu zjedenych jablk
    public void zobrazSkore(Graphics g){
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Helvetica",Font.BOLD,35));
        FontMetrics metrics= this.getFontMetrics(g.getFont());
        String score= "Score: "+zjedeneJablka;
        g.drawString(score,(plocha.getSirkaPlochy()-metrics.stringWidth(score))/2, g.getFont().getSize());

    }
    public Hadik getHadik() {
        return hadik;
    }
}
