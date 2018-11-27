package entity;

import java.io.Serializable;

public class Sdelnaya implements Premii, Serializable {

    private double zarplata;


    private double procentzaplan;


    private double procentzapereplan;


    private double procentpereplan;

    public double getZarplata() {
        return zarplata;
    }

    public void setZarplata(double zarplata) {
        this.zarplata = zarplata;
    }

    public double getProcentzaplan() {
        return procentzaplan;
    }

    public void setProcentzaplan(double procentzaplan) {
        this.procentzaplan = procentzaplan;
    }

    public double getProcentzapereplan() {
        return procentzapereplan;
    }

    public void setProcentzapereplan(double procentzapereplan) {
        this.procentzapereplan = procentzapereplan;
    }

    public double getProcentpereplan() {
        return procentpereplan;
    }

    public void setProcentpereplan(double procentpereplan) {
        this.procentpereplan = procentpereplan;
    }
}
