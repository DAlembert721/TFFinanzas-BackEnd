package com.acme.otorongo.domain.model.promissories;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class PromissoryNoteFinalCostKey  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "final_cost_id")
    Long finalCostId;
    @Column(name = "promissory_note_id")
    Long promissoryNoteId;

    public PromissoryNoteFinalCostKey(){}

    public PromissoryNoteFinalCostKey(Long finalCostId, Long promissoryNoteId){
        super();
        this.finalCostId = finalCostId;
        this.promissoryNoteId = promissoryNoteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromissoryNoteFinalCostKey that = (PromissoryNoteFinalCostKey) o;
        return Objects.equals(getFinalCostId(), that.getFinalCostId()) &&
                Objects.equals(getPromissoryNoteId(), that.getPromissoryNoteId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result)
                + ((finalCostId == null) ? 0 : finalCostId.hashCode());
        result = prime * result
                + ((promissoryNoteId == null) ? 0 : promissoryNoteId.hashCode());
        return result;
    }
}
