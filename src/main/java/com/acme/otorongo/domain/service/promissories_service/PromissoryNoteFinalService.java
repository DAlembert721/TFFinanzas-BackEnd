package com.acme.otorongo.domain.service.promissories_service;

import com.acme.otorongo.domain.model.promissories.PromissoryNoteFinalCost;
import com.acme.otorongo.domain.model.promissories.PromissoryNoteInitialCost;

import java.util.List;

public interface PromissoryNoteFinalService {
    List<PromissoryNoteFinalCost> getAllPromissoryNoteFinalsByPromissoryNoteId(Long promissoryNoteId);
    List<PromissoryNoteFinalCost> getAllPromissoryNoteFinalsByFinalCostId(Long finalCostId);
    PromissoryNoteFinalCost save(PromissoryNoteFinalCost promissoryNoteFinalCost,
                                   Long promissoryNoteId, Long initialCostId);
    PromissoryNoteFinalCost update(Long promissoryNoteId, Long finalCostId,
                                     PromissoryNoteFinalCost promissoryNoteFinalCost);
    void delete(Long promissoryNoteId, Long finalCostId);
}
