package entity;

import java.io.Serializable;

public class Premiya implements Serializable {
    private String personId;
    private int typeOfPremiya;
    private Premii premiya;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public int getTypeOfPremiya() {
        return typeOfPremiya;
    }

    public void setTypeOfPremiya(int typeOfPremiya) {
        this.typeOfPremiya = typeOfPremiya;
    }

    public Premii getPremiya() {
        return premiya;
    }

    public void setPremiya(Premii premiya) {
        this.premiya = premiya;
    }
}
