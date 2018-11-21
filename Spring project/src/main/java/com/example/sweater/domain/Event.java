package com.example.sweater.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_event;
    @NotBlank(message = "Поле не должно быть пустым")
    private String place;
    @NotBlank(message = "Поле не должно быть пустым")
    private String date;
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;

    private String filename;
    @NotNull(message = "Поле не должно быть пустым")
    private int requiredage;
    @NotNull(message = "Поле не должно быть пустым")
    private int numberofguests;

    public int getRequiredage() {
        return requiredage;
    }

    public void setRequiredage(int requiredage) {
        this.requiredage = requiredage;
    }

    public int getNumberofguests() {
        return numberofguests;
    }

    public void setNumberofguests(int numberofguests) {
        this.numberofguests = numberofguests;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId_event() {
        return id_event;
    }

    public void setId_event(Long id_event) {
        this.id_event = id_event;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
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
