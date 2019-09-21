package com.artyomefimov.database.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "masterPassportNum", cascade = CascadeType.REMOVE)
    private Set<Master> masters = new HashSet<>();

    @OneToMany(mappedBy = "customerPassportNum", cascade = CascadeType.REMOVE)
    private Set<Customer> customers = new HashSet<>();

    public Workshop() {
    }

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
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Time getOpenHours() {
        return openHours;
    }

    public void setOpenHours(Time openHours) {
        this.openHours = openHours;
    }

    public Time getCloseHours() {
        return closeHours;
    }

    public void setCloseHours(Time closeHours) {
        this.closeHours = closeHours;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Set<Master> getMasters() {
        return masters;
    }

    public void setMasters(Set<Master> masters) {
        this.masters = masters;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
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
