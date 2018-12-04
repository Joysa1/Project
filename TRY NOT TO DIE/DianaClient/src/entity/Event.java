package entity;

import java.io.Serializable;

public class Event implements Serializable {
    Integer id;
    String name;
    String age;
    String type;
    String numberofpeople;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumberofpeople() {
        return numberofpeople;
    }

    public void setNumberofpeople(String numberofpeople) {
        this.numberofpeople = numberofpeople;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
