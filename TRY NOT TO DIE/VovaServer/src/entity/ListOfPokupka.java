package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfPokupka implements Serializable {
    ArrayList<Pokupka> pokupkas = new ArrayList<>();

    public ArrayList<Pokupka> getPokupkas() {
        return pokupkas;
    }

    public void setPokupkas(ArrayList<Pokupka> pokupkas) {
        this.pokupkas = pokupkas;
    }
}
