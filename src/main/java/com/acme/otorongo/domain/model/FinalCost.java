package com.acme.otorongo.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "final_costs")
@Data
public class FinalCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String name;

    @OneToMany(mappedBy = "finalCost")
    private Set<PromissoryNoteFinalCost> promissoryNoteFinalCosts = new HashSet<>();
}
