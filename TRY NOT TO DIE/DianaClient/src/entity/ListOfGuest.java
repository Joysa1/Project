package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfGuest implements Serializable {
    ArrayList<Guest> guests = new ArrayList<>();

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Guest> guests) {
        this.guests = guests;
    }
}
