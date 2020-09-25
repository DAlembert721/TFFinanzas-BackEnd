package com.acme.otorongo.domain.service.promissories_service;

import com.acme.otorongo.domain.model.promissories.PromissoryNoteInitialCost;

import java.util.List;

public interface PromissoryNoteInitialCostService {
    List<PromissoryNoteInitialCost> getAllPromissoryNoteInitialsByPromissoryNoteId(Long promissoryNoteId);
    List<PromissoryNoteInitialCost> getAllPromissoryNoteInitialsByInitialCostId(Long initialCostId);
    PromissoryNoteInitialCost save(PromissoryNoteInitialCost promissoryNoteInitialCost,
                                   Long promissoryNoteId, Long initialCostId);
    PromissoryNoteInitialCost update(Long promissoryNoteId, Long initialCostId,
                                     PromissoryNoteInitialCost promissoryNoteInitialCost);
    void delete(Long promissoryNoteId, Long initialCostId);
}
