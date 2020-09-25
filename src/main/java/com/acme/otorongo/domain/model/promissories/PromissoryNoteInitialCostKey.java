package com.acme.otorongo.domain.model.promissories;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class PromissoryNoteInitialCostKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "initial_cost_id")
    Long initialCostId;
    @Column(name = "promissory_note_id")
    Long promissoryNoteId;

    public PromissoryNoteInitialCostKey(){}

    public PromissoryNoteInitialCostKey(Long initialCostId, Long promissoryNoteId){
        super();
        this.initialCostId = initialCostId;
        this.promissoryNoteId = promissoryNoteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromissoryNoteInitialCostKey that = (PromissoryNoteInitialCostKey) o;
        return Objects.equals(getInitialCostId(), that.getInitialCostId()) &&
                Objects.equals(getPromissoryNoteId(), that.getPromissoryNoteId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result)
                + ((initialCostId == null) ? 0 : initialCostId.hashCode());
        result = prime * result
                + ((promissoryNoteId == null) ? 0 : promissoryNoteId.hashCode());
        return result;
    }
}
