package com.acme.otorongo.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "initial_costs")
@Data
public class InitialCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String name;

    @OneToMany(mappedBy = "initialCost")
    private Set<PromissoryNoteInitialCost> promissoryNoteInitialCosts = new HashSet<>();
}
