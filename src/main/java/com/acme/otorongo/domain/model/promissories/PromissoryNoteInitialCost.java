package com.acme.otorongo.domain.model.promissories;

import com.acme.otorongo.domain.model.costs.InitialCost;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "promissory_note_initial_costs")
public class PromissoryNoteInitialCost {
    @EmbeddedId
    private PromissoryNoteInitialCostKey id = new PromissoryNoteInitialCostKey();
    @ManyToOne
    @MapsId("initialCostId")
    private InitialCost initialCost;
    @ManyToOne
    @MapsId("promissoryNoteId")
    private PromissoryNote promissoryNote;
    @Column(name = "cost")
    private float cost;
}
