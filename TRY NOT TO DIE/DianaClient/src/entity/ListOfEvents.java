package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfEvents implements Serializable {
    ArrayList<Event> events = new ArrayList<>();

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
