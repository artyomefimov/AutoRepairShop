package com.artyomefimov.database.model;

import javax.persistence.*;

@Entity
@Table(name = "breakdown_type")
public class BreakdownType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", schema = "public", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
