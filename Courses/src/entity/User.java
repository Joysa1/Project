package entity;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
   private String username;
    private  String password;
    private  boolean admin;
    private  String name;
    private  String surname;
    private  String lastname;
    private  int age;
    private String number;

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return admin == user.admin &&
                age == user.age &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(number, user.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, admin, name, surname, lastname, age, number);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", admin=" + admin +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", number='" + number + '\'' +
                '}';
    }

    public void setNumber(String number) {
        String regex = "(80|\\+375)[\\s|-]?(29|33|44|25)[\\s|-]?(\\d{7}|\\d{3})";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(number);

        if(!m.find()) {
            throw new IllegalArgumentException();
       }
        this.number = m.group();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }
}
