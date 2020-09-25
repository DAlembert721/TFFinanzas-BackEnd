package com.acme.otorongo.domain.service.expenses_service;

import com.acme.otorongo.domain.model.expenses.Quotation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuotationService {
    List<Quotation> getAllQuotations();
    Quotation createQuotation(Quotation quotation);
    Quotation getQuotationById(Long quotationId);
    Quotation updateQuotation(Long quotationId, Quotation quotation);
    ResponseEntity<?> deleteQuotation(Long quotationId);
}
