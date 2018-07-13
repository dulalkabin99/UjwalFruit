package com.example.ujwalfruit;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private  String name;

    @OneToMany(mappedBy = "fruit",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    public Set<Weekday> weekdays;

    private String imageurl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Set<Weekday> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(Set<Weekday> weekdays) {
        this.weekdays = weekdays;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
