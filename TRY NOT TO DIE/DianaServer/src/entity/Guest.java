package entity;

import java.io.Serializable;

public class Guest implements Serializable {
    Integer id;
    String name;
    String email;
    String iduser;
    String idevent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdevent() {
        return idevent;
    }

    public void setIdevent(String idevent) {
        this.idevent = idevent;
    }
}
