package com.acme.otorongo.service.promissories_service_impl;

import com.acme.otorongo.domain.model.promissories.PromissoryNote;
import com.acme.otorongo.domain.repository.expenses_repository.*;
import com.acme.otorongo.domain.repository.operations_repository.OperationRepository;
import com.acme.otorongo.domain.repository.promissories_repository.PromissoryNoteRepository;
import com.acme.otorongo.domain.service.promissories_service.PromissoryNoteService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromissoryNoteServiceImpl implements PromissoryNoteService {

    private final PromissoryNoteRepository promissoryNoteRepository;
    private final OperationRepository operationRepository;
    private final RateRepository rateRepository;

    @Autowired
    public PromissoryNoteServiceImpl(PromissoryNoteRepository promissoryNoteRepository,
                                     OperationRepository operationRepository,
                                     RateRepository rateRepository){
        this.promissoryNoteRepository = promissoryNoteRepository;
        this.operationRepository = operationRepository;
        this.rateRepository = rateRepository;
    }

    @Override
    public List<PromissoryNote> getAllPromissoryNotes() {
        return promissoryNoteRepository.findAll();
    }

    @Override
    public List<PromissoryNote> getAllPromissoryNotesByRateId(Long rateId) {
        return promissoryNoteRepository.findByRateId(rateId);
    }

    @Override
    public PromissoryNote getPromissoryNoteByOperationId(Long operationId) {
        return promissoryNoteRepository.findByOperationId(operationId);
    }

    @Override
    public PromissoryNote createPromissoryNote(PromissoryNote promissoryNote, Long rateId, Long operationId) {
        promissoryNote.setRate(rateRepository.findById(rateId).orElse(null));
        promissoryNote.setOperation(operationRepository.findById(operationId).orElse(null));
        return promissoryNoteRepository.save(promissoryNote);
    }

    @Override
    public PromissoryNote getPromissoryNoteId(Long promissoryNoteId) {
        return promissoryNoteRepository.findById(promissoryNoteId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PromissoryNote", "Id", promissoryNoteId));
    }

    @Override
    public PromissoryNote updatePromissoryNote(Long promissoryNoteId, PromissoryNote promissoryNote) {
        PromissoryNote existed = promissoryNoteRepository.findById(promissoryNoteId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PromissoryNote", "Id", promissoryNoteId));
        existed.setInitialWithholding(promissoryNote.getInitialWithholding());
        existed.setPaymentDate(promissoryNote.getPaymentDate());
        existed.setFinalWithholding(promissoryNote.getFinalWithholding());
        existed.setSignDate(promissoryNote.getSignDate());
        existed.setTcea(promissoryNote.getTcea());
        return promissoryNoteRepository.save(existed);
    }

    @Override
    public ResponseEntity<?> deletePromissoryNote(Long promissoryNoteId) {
        promissoryNoteRepository.deleteById(promissoryNoteId);
        return ResponseEntity.ok().build();
    }
}
