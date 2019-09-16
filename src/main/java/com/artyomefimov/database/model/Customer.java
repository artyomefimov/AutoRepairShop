package com.artyomefimov.database.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", schema = "public", allocationSize = 1)
    @Column(name = "customer_passport_num")
    private Long customerPassportNum;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_num")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "inn", nullable = false)
    private Workshop workshop;

    @OneToMany(mappedBy = "carNumber", cascade = CascadeType.REMOVE)
    private Set<Car> cars = new HashSet<>();

    public Customer() {
    }

    public Customer(String name, String phone, String address, Date birthDate, Workshop workshop) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
        this.workshop = workshop;
    }

    public Long getCustomerPassportNum() {
        return customerPassportNum;
    }

    public void setCustomerPassportNum(Long customerPassportNum) {
        this.customerPassportNum = customerPassportNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
