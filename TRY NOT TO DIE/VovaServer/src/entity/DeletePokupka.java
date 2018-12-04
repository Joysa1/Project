package entity;

import java.io.Serializable;

public class DeletePokupka implements Serializable {
    Pokupka pokupka;

    public Pokupka getPokupka() {
        return pokupka;
    }

    public void setPokupka(Pokupka pokupka) {
        this.pokupka = pokupka;
    }
}
