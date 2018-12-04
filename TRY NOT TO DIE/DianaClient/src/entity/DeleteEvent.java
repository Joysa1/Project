package entity;

import java.io.Serializable;

public class DeleteEvent implements Serializable {
    Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
