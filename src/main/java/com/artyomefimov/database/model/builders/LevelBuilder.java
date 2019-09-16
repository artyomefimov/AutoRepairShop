package com.artyomefimov.database.model.builders;

import com.artyomefimov.database.model.Level;

public class LevelBuilder implements Builder<Level> {
    private String name;

    public LevelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Level build() {
        return new Level(name);
    }
}
