package com.acme.otorongo.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "promissory_note_initial_costs")
public class PromissoryNoteInitialCost {


    @ManyToOne
    @MapsId("initial")
    @JoinColumn(name = "initial_cost_id")
    InitialCost initialCost;

    @NotNull
    private Float cost;


}
