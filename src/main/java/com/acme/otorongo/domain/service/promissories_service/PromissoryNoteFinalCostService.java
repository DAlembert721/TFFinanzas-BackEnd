package com.acme.otorongo.domain.service.promissories_service;

import com.acme.otorongo.domain.model.promissories.PromissoryNoteFinalCost;
import java.util.List;

public interface PromissoryNoteFinalCostService {
    List<PromissoryNoteFinalCost> getAllPromissoryNoteFinalsByPromissoryNoteId(Long promissoryNoteId);
    List<PromissoryNoteFinalCost> getAllPromissoryNoteFinalsByFinalCostId(Long finalCostId);
    PromissoryNoteFinalCost save(PromissoryNoteFinalCost promissoryNoteFinalCost,
                                   Long promissoryNoteId, Long finalCostId);
    PromissoryNoteFinalCost update(Long promissoryNoteId, Long finalCostId,
                                     PromissoryNoteFinalCost promissoryNoteFinalCost);
    void delete(Long promissoryNoteId, Long finalCostId);
}
