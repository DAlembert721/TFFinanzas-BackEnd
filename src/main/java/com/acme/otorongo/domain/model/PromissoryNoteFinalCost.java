package com.acme.otorongo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "promissory_note_final_costs")
public class PromissoryNoteFinalCost {
    @EmbeddedId
    private PromissoryNoteFinalCostKey id = new PromissoryNoteFinalCostKey();
    @ManyToOne
    @MapsId("finalCostId")
    private FinalCost finalCost;
    @ManyToOne
    @MapsId("promissoryNoteId")
    private PromissoryNote promissoryNote;
    @Column(name = "cost")
    private float cost;
}
