package com.acme.otorongo.domain.service.promissories_service;

import com.acme.otorongo.domain.model.promissories.PromissoryNote;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PromissoryNoteService {
    List<PromissoryNote> getAllPromissoryNotes();
    List<PromissoryNote> getAllCreditLinesByClientId(Long clientId);
    List<PromissoryNote> getAllCreditLinesByRateId(Long rateId);
    List<PromissoryNote> getAllCreditLinesByQuotationId(Long quotationId);
    List<PromissoryNote> getAllCreditLinesByCurrencyId(Long currencyId);
    PromissoryNote createPromissoryNote(PromissoryNote promissoryNote);
    PromissoryNote getPromissoryNoteId(Long promissoryNoteId);
    PromissoryNote updatePromissoryNote(Long promissoryNoteId, PromissoryNote promissoryNote);
    ResponseEntity<?> deletePromissoryNote(Long promissoryNoteId);
}
