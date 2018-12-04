package entity;

import java.io.Serializable;

public class Pokupka implements Serializable {
    Integer id;
    String idtovara;
    String iduser;
    String kolvo;
    String summa;

    public String getIdtovara() {
        return idtovara;
    }

    public void setIdtovara(String idtovara) {
        this.idtovara = idtovara;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getKolvo() {
        return kolvo;
    }

    public void setKolvo(String kolvo) {
        this.kolvo = kolvo;
    }

    public String getSumma() {
        return summa;
    }

    public void setSumma(String summa) {
        this.summa = summa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
