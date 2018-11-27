package entity;

import java.io.Serializable;

public class Povremenaya implements Premii, Serializable {

    private double zarplata;

    private double procpremii;

    public double getZarplata() {
        return zarplata;
    }

    public void setZarplata(double zarplata) {
        this.zarplata = zarplata;
    }

    public double getProcpremii() {
        return procpremii;
    }

    public void setProcpremii(double procpremii) {
        this.procpremii = procpremii;
    }
}
