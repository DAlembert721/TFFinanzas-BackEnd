package com.acme.otorongo.service.expenses_service_impl;

import com.acme.otorongo.domain.model.expenses.Quotation;
import com.acme.otorongo.domain.repository.expenses_repository.QuotationRepository;
import com.acme.otorongo.domain.service.expenses_service.QuotationService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationServiceImpl implements QuotationService {

    private final QuotationRepository quotationRepository;

    @Autowired
    public QuotationServiceImpl(QuotationRepository quotationRepository){
        this.quotationRepository = quotationRepository;
    }

    @Override
    public List<Quotation> getAllQuotations() {
        return quotationRepository.findAll();
    }

    @Override
    public Quotation createQuotation(Quotation quotation) {
        return quotationRepository.save(quotation);
    }

    @Override
    public Quotation getQuotationById(Long quotationId) {
        return quotationRepository.findById(quotationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quotation", "Id", quotationId));
    }

    @Override
    public Quotation updateQuotation(Long quotationId, Quotation quotation) {
        Quotation existed = quotationRepository.findById(quotationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quotation", "Id", quotationId));
        existed.setName(quotation.getName());
        existed.setValue(quotation.getValue());
        return quotationRepository.save(existed);
    }

    @Override
    public ResponseEntity<?> deleteQuotation(Long quotationId) {
        quotationRepository.deleteById(quotationId);
        return ResponseEntity.ok().build();
    }
}
