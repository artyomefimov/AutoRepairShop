package com.artyomefimov.database.model;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "mark")
    private String mark;

    @Column(name = "model")
    private String model;

    @Column(name = "crash_type")
    private String crashType;

    @Column(name = "mileage")
    private int mileage;

    @ManyToOne
    @JoinColumn(name = "customerPassportNum")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "masterPassportNum")
    private Master master;

    public Car() {
    }

    public Car(String carNumber, String mark, String model, String crashType, int mileage, Customer customer, Master master) {
        this.carNumber = carNumber;
        this.mark = mark;
        this.model = model;
        this.crashType = crashType;
        this.mileage = mileage;
        this.customer = customer;
        this.master = master;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCrashType() {
        return crashType;
    }

    public void setCrashType(String crashType) {
        this.crashType = crashType;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }
}
