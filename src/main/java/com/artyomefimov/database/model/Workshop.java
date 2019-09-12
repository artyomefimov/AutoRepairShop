package com.artyomefimov.database.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "workshop")
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", schema = "public", allocationSize = 1)
    @Column(name = "inn")
    private Long inn;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "open_time")
    private Time openHours;

    @Column(name = "close_time")
    private Time closeHours;

    @Column(name = "owner_name")
    private String ownerName;

    public Workshop() {}

    public Workshop(String name, String address, Time openHours, Time closeHours, String ownerName) {
        this.name = name;
        this.address = address;
        this.openHours = openHours;
        this.closeHours = closeHours;
        this.ownerName = ownerName;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

    public Time getOpenHours() {
        return openHours;
    }

    public void setOpenHours(Time open_hours) {
        this.openHours = open_hours;
    }

    public Time getCloseHours() {
        return closeHours;
    }

    public void setCloseHours(Time close_hours) {
        this.closeHours = close_hours;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String owner_name) {
        this.ownerName = owner_name.trim();
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "inn=" + inn +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", openHours=" + openHours +
                ", closeHours=" + closeHours +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
