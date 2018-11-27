package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfPremiya implements Serializable {
    ArrayList<PremiyaForTable> listOfPremiya;
    public ListOfPremiya()
    {
        listOfPremiya = new ArrayList<>();
    }

    public ArrayList<PremiyaForTable> getListOfPremiya() {
        return listOfPremiya;
    }

    public void setListOfPremiya(ArrayList<PremiyaForTable> listOfPremiya) {
        this.listOfPremiya = listOfPremiya;
    }
}
