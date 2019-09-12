package com.artyomefimov.database.model.builders;

import com.artyomefimov.database.model.BreakdownType;

public class BreakdownTypeBuilder implements Builder<BreakdownType> {
    private String name;

    public BreakdownTypeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public BreakdownType build() {
        return new BreakdownType(name);
    }
}
