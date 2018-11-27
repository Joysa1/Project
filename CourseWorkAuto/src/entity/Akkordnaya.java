package entity;

import java.io.Serializable;

public class Akkordnaya implements Premii, Serializable {


    public double getPrirabotok() {
        return prirabotok;
    }

    public void setPrirabotok(double prirabotok) {
        this.prirabotok = prirabotok;
    }

    public double getKaefuch() {
        return kaefuch;
    }

    public void setKaefuch(double kaefuch) {
        this.kaefuch = kaefuch;
    }

    public double getTarifstavka() {
        return tarifstavka;
    }

    public void setTarifstavka(double tarifstavka) {
        this.tarifstavka = tarifstavka;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    private double prirabotok;


    private double kaefuch;


    private double tarifstavka;


    private int time;

}
