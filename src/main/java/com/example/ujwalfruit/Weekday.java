package com.example.ujwalfruit;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Weekday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String day;

    private String hours;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fruit_id")
    private Fruit fruit;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }
}


