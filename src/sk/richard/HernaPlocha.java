package sk.richard;

import java.awt.Graphics;

public class HernaPlocha  {
    private final int sirkaPlochy;
    private final int vyskaPlochy;
    private final int vsetkyBloky;
    private final int velkostBloku;

    public HernaPlocha(){
        this.velkostBloku=25;//nastavim velkost vsetkych objektov v hre na 25
        this.sirkaPlochy=600;
        this.vyskaPlochy=600;
        this.vsetkyBloky=(this.sirkaPlochy*this.vyskaPlochy)/this.velkostBloku;
    }

    public int getVsetkyBloky() {
        return vsetkyBloky;
    }

    /*najskor si rozlozim hernu plochu na jednotlive casti, pixely (spravim taku mriezku/raster)
    zobrazenie meriezky na hernu plochu, (sluzi na lespiu vizualizaciu objektov v hre */
    public void zobrazMriezku(Graphics g){
        for (int i = 0; i < (vyskaPlochy / getVelkostBloku()); i++) {
            g.drawLine(i * getVelkostBloku(), 0, i * getVelkostBloku(), vyskaPlochy);
            g.drawLine(0, i * getVelkostBloku(), sirkaPlochy, i * getVelkostBloku());
        }

    }
    public int getSirkaPlochy() {
        return sirkaPlochy;
    }

    public int getVyskaPlochy() {
        return vyskaPlochy;
    }

    public int getVelkostBloku() {
        return velkostBloku;
    }

}
