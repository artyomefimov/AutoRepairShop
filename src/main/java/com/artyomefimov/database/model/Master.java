package com.artyomefimov.database.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "master")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", schema = "public", allocationSize = 1)
    @Column(name = "master_passport_num")
    private Long masterPassportNum;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_num")
    private String phone;

    @Column(name = "level")
    private int level;

    @ManyToOne
    @JoinColumn(name = "inn", nullable = false)
    private Workshop workshop;

    @OneToMany(mappedBy = "carNumber", cascade = CascadeType.ALL) // todo rethink
    private Set<Car> cars = new HashSet<>();

    public Master() {
    }

    public Master(Long masterPassportNum, String name, int level, String phone, Workshop workshop) {
        this.masterPassportNum = masterPassportNum;
        this.name = name;
        this.level = level;
        this.phone = phone;
        this.workshop = workshop;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
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