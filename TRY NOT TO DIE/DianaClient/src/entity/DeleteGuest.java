package entity;

import java.io.Serializable;

public class DeleteGuest implements Serializable {
    Guest guest;

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
