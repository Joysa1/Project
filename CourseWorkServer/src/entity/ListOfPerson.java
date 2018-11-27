package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfPerson implements Serializable {
    ArrayList<Person> listOfPerson;
    public ListOfPerson()
    {
        listOfPerson = new ArrayList<>();
    }

    public ArrayList<Person> getListOfPerson() {
        return listOfPerson;
    }

    public void setListOfPerson(ArrayList<Person> listOfPerson) {
        this.listOfPerson = listOfPerson;
    }
}
