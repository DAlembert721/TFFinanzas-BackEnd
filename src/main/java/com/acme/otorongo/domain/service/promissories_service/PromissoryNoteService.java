package com.acme.otorongo.domain.service.promissories_service;

import com.acme.otorongo.domain.model.promissories.PromissoryNote;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PromissoryNoteService {
    List<PromissoryNote> getAllPromissoryNotes();
    List<PromissoryNote> getAllPromissoryNotesByRateId(Long rateId);
    PromissoryNote getPromissoryNoteByOperationId(Long operationId);
    PromissoryNote createPromissoryNote(PromissoryNote promissoryNote, Long rateId, Long operationId);
    PromissoryNote getPromissoryNoteId(Long promissoryNoteId);
    PromissoryNote updatePromissoryNote(Long promissoryNoteId, PromissoryNote promissoryNote);
    ResponseEntity<?> deletePromissoryNote(Long promissoryNoteId);
}
