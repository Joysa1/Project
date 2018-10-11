package entity;

import java.util.Objects;

public abstract class Event {
    private String place;
    private  String date;
    private  String name;
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(place, event.place) &&
                Objects.equals(date, event.date) &&
                Objects.equals(name, event.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, date, name);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


