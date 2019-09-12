package com.artyomefimov.database.model.builders;

import com.artyomefimov.database.model.Workshop;

import java.sql.Time;

public class WorkshopBuilder implements Builder<Workshop> {
    private String name;
    private String address;
    private Time openHours;
    private Time closeHours;
    private String ownerName;

    public WorkshopBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public WorkshopBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public WorkshopBuilder setOpenHours(Time openHours) {
        this.openHours = openHours;
        return this;
    }

    public WorkshopBuilder setCloseHours(Time closeHours) {
        this.closeHours = closeHours;
        return this;
    }

    public WorkshopBuilder setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    @Override
    public Workshop build() {
        return new Workshop(name, address, openHours, closeHours, ownerName);
    }
}
