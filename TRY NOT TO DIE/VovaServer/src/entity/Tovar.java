package entity;

import java.io.Serializable;

public class Tovar implements Serializable {
    Integer id;
    String name;
    String kind;
    String cena;
    String kolvo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getKolvo() {
        return kolvo;
    }

    public void setKolvo(String kolvo) {
        this.kolvo = kolvo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
