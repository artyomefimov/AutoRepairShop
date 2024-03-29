package com.artyomefimov.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "master")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", schema = "public", allocationSize = 1)
    @Column(name = "master_id")
    private Long masterId;

    @Column(name = "master_passport_num")
    private Long masterPassportNum;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_num")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @ManyToOne
    @JoinColumn(name = "workshop_id", nullable = false)
    private Workshop workshop;

    @JsonIgnore
    @OneToMany(mappedBy = "carId", cascade = CascadeType.REMOVE)
    private Set<Car> cars = new HashSet<>();

    public Master() {
    }

    public Master(Long masterPassportNum, String name, String phone, Level level, Workshop workshop) {
        this.masterPassportNum = masterPassportNum;
        this.name = name;
        this.phone = phone;
        this.level = level;
        this.workshop = workshop;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getMasterPassportNum() {
        return masterPassportNum;
    }

    public void setMasterPassportNum(Long masterPassportNum) {
        this.masterPassportNum = masterPassportNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
