package sk.richard;

import java.awt.*;
import java.util.Random;

public class Hadik {

    private int dlzkaHada;
    //pole x y sluzi na ukladanie pozicii hada
    private int [] x;
    private int [] y;
    private String smer;
    private HernaPlocha hernaPlocha;
    private AbstraktneCastiHry castiHry;
    private Random random;


    public Hadik(){
        this.hernaPlocha=new HernaPlocha();
        this.castiHry=new AbstraktneCastiHry();
        this.random=new Random();
        this.dlzkaHada=5;//pociatocna dlzka hadika
        this.smer="doprava";//pociatocny smer ktorym pojde hadik
        x=new int[hernaPlocha.getVsetkyBloky()];
        y=new int[hernaPlocha.getVsetkyBloky()];
    }
    public void pohybHada(){
        for (int i = dlzkaHada;i > 0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (smer){
            case "hore":
                y[0]= y[0] - hernaPlocha.getVelkostBloku();
                break;
            case "dole":
                y[0]= y[0] + hernaPlocha.getVelkostBloku();
                break;
            case "dolava":
                x[0]= x[0] - hernaPlocha.getVelkostBloku();
                break;
            case "doprava":
                x[0]= x[0] + hernaPlocha.getVelkostBloku();
                break;
        }
    }
    public void skontrolujKoliziu(){
        for (int i = dlzkaHada;i > 0;i--){
            //ak hlava hada narazi do tela hada
            if ((x[0]==x[i])&&(y[0]==y[i])) {
                this.castiHry.setHraBezi(false);
            }

        }
        // kontroluje ci hlava hada narazila do laveho okraja hernej plochy
        if (x[0] < 0){
            this.castiHry.setHraBezi(false);
        }
        //kontroluje ci hlava hada narazila do praveho okraja hernej plochy
        if (x[0] > hernaPlocha.getSirkaPlochy()){
            this.castiHry.setHraBezi(false);
        }
        // kotrola ci hlava hada narazila do horneho okraja
        if (y[0] < 0){
            this.castiHry.setHraBezi(false);
        }
        //kontrola ci hlava hada narazila do spodneho okraja
        if (y[0] > hernaPlocha.getVyskaPlochy()){
            this.castiHry.setHraBezi(false);
        }
    }
    public void zobrazHadika(Graphics g){
        //zobrazenie hadika na plochu
        for (int i = 0; i < dlzkaHada; i++) {
            //hlava hada ked je i rovne 0 b //hlava sa lisi od tela je vzdy zelena
            if (i == 0) {
                g.setColor(Color.green);
            } else {
                //blikanie tela hadika(nahodne meni farby)
                g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            }
            g.fillRect(x[i], y[i], hernaPlocha.getVelkostBloku(), hernaPlocha.getVelkostBloku());

        }

    }

    public int getX(int index) {
        return x[index];
    }

    public int getY(int index) {
        return y[index];
    }

    public int getDlzkaHada() {
        return dlzkaHada;
    }

    public void setDlzkaHada(int dlzkaHada) {
        this.dlzkaHada = dlzkaHada;
    }

    public String getSmer() {
        return smer;
    }

    public void setSmer(String smer) {
        this.smer = smer;
    }

    public AbstraktneCastiHry getCastiHry() {
        return castiHry;
    }
}
