package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfTovar implements Serializable {
    ArrayList<Tovar> tovars = new ArrayList<>();

    public ArrayList<Tovar> getTovars() {
        return tovars;
    }

    public void setTovars(ArrayList<Tovar> tovars) {
        this.tovars = tovars;
    }
}
