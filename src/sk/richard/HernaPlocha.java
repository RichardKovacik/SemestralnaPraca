package sk.richard;

import java.awt.*;

public class HernaPlocha  {
    private final int sirkaPlochy;
    private final int vyskaPlochy;
    private final int vsetkyBloky;
    Blok blok;

    public HernaPlocha(){
        this.blok=new Blok();
        this.sirkaPlochy=600;
        this.vyskaPlochy=600;
        this.vsetkyBloky=(this.sirkaPlochy*this.vyskaPlochy)/this.blok.getVelkostBloku();
    }

    public int getVsetkyBloky() {
        return vsetkyBloky;
    }
    public void zobrazMriezku(Graphics g){
        //najskor si rozlozim hernu plochu na jednotlive casti, pixely (spravim taku mriezku/raster)
        //zobrazenie meriezky na hernu plochu, (sluzi na lespiu vizualizaciu objektov v hre)
        for (int i = 0; i < (vyskaPlochy / blok.getVelkostBloku()); i++) {
            g.drawLine(i * blok.getVelkostBloku(), 0, i * blok.getVelkostBloku(), vyskaPlochy);
            g.drawLine(0, i * blok.getVelkostBloku(), sirkaPlochy, i * blok.getVelkostBloku());
        }

    }
    public int getSirkaPlochy() {
        return sirkaPlochy;
    }

    public int getVyskaPlochy() {
        return vyskaPlochy;
    }

}
