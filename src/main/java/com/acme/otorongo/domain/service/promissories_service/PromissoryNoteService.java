package com.acme.otorongo.domain.service.promissories_service;

import com.acme.otorongo.domain.model.promissories.PromissoryNote;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PromissoryNoteService {
    List<PromissoryNote> getAllPromissoryNotes();
    List<PromissoryNote> getAllPromissoryNotesByClientId(Long clientId);
    List<PromissoryNote> getAllPromissoryNotesByRateId(Long rateId);
    List<PromissoryNote> getAllPromissoryNotesByQuotationId(Long quotationId);
    List<PromissoryNote> getAllPromissoryNotesByCurrencyId(Long currencyId);
    PromissoryNote createPromissoryNote(PromissoryNote promissoryNote, Long clientId,
                                        Long rateId, Long quotationId, Long currencyId);
    PromissoryNote getPromissoryNoteId(Long promissoryNoteId);
    PromissoryNote updatePromissoryNote(Long promissoryNoteId, PromissoryNote promissoryNote);
    ResponseEntity<?> deletePromissoryNote(Long promissoryNoteId);
}
