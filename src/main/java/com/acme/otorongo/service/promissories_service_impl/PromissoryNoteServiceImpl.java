package com.acme.otorongo.service.promissories_service_impl;

import com.acme.otorongo.domain.model.promissories.PromissoryNote;
import com.acme.otorongo.domain.repository.expenses_repository.*;
import com.acme.otorongo.domain.repository.promissories_repository.PromissoryNoteRepository;
import com.acme.otorongo.domain.repository.users_repository.ClientRepository;
import com.acme.otorongo.domain.service.promissories_service.PromissoryNoteService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromissoryNoteServiceImpl implements PromissoryNoteService {

    private final PromissoryNoteRepository promissoryNoteRepository;
    private final ClientRepository clientRepository;
    private final RateRepository rateRepository;
    private final QuotationRepository quotationRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public PromissoryNoteServiceImpl(PromissoryNoteRepository promissoryNoteRepository,
                                     ClientRepository clientRepository, RateRepository rateRepository,
                                     QuotationRepository quotationRepository, CurrencyRepository currencyRepository){
        this.promissoryNoteRepository = promissoryNoteRepository;
        this.clientRepository = clientRepository;
        this.rateRepository = rateRepository;
        this.quotationRepository = quotationRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<PromissoryNote> getAllPromissoryNotes() {
        return promissoryNoteRepository.findAll();
    }

    @Override
    public List<PromissoryNote> getAllPromissoryNotesByClientId(Long clientId) {
        return promissoryNoteRepository.findByClientId(clientId);
    }

    @Override
    public List<PromissoryNote> getAllPromissoryNotesByRateId(Long rateId) {
        return promissoryNoteRepository.findByRateId(rateId);
    }

    @Override
    public List<PromissoryNote> getAllPromissoryNotesByQuotationId(Long quotationId) {
        return promissoryNoteRepository.findByQuotationId(quotationId);
    }

    @Override
    public List<PromissoryNote> getAllPromissoryNotesByCurrencyId(Long currencyId) {
        return promissoryNoteRepository.findByCurrencyId(currencyId);
    }

    @Override
    public PromissoryNote createPromissoryNote(PromissoryNote promissoryNote, Long clientId,
                                               Long rateId, Long quotationId, Long currencyId) {
        promissoryNote.setClient(clientRepository.findById(clientId).orElse(null));
        promissoryNote.setRate(rateRepository.findById(rateId).orElse(null));
        promissoryNote.setQuotation(quotationRepository.findById(quotationId).orElse(null));
        promissoryNote.setCurrency(currencyRepository.findById(currencyId).orElse(null));
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
        existed.setNominalValue(promissoryNote.getNominalValue());
        existed.setPaymentDate(promissoryNote.getPaymentDate());
        existed.setDiscountDate(promissoryNote.getDiscountDate());
        existed.setExpireDate(promissoryNote.getExpireDate());
        existed.setFinalWithholding(promissoryNote.getFinalWithholding());
        existed.setRateValue(promissoryNote.getRateValue());
        existed.setSignDate(promissoryNote.getSignDate());
        existed.setTcea(promissoryNote.getTcea());
        existed.setTime(promissoryNote.getTime());
        return promissoryNoteRepository.save(existed);
    }

    @Override
    public ResponseEntity<?> deletePromissoryNote(Long promissoryNoteId) {
        promissoryNoteRepository.deleteById(promissoryNoteId);
        return ResponseEntity.ok().build();
    }
}
