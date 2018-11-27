package entity;


import java.io.Serializable;

public class Person implements Serializable {

    private String surname;

    private String name;

    private String otchestvo;
    private String idperson;
    private String numotdel;

    public String getSurname() {
        return surname;
    }

    public String getIdperson() {
        return idperson;
    }

    public void setIdperson(String idperson) {
        this.idperson = idperson;
    }

    public void setNumotdel(String numotdel) {
        this.numotdel = numotdel;
    }
    public String getNumotdel()
    {
        return numotdel;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }


}
