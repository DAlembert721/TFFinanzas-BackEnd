package com.acme.otorongo.domain.service.expenses_service;

import com.acme.otorongo.domain.model.expenses.CreditLine;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CreditLineService {
    List<CreditLine> getAllCreditLines();
    List<CreditLine> getAllCreditLinesByClientId(Long clientId);
    CreditLine createCreditLine(CreditLine creditLine, Long clientId);
    CreditLine getCreditLineById(Long creditLineId);
    CreditLine updateCreditLine(Long creditLineId, CreditLine creditLine);
    ResponseEntity<?> deleteCreditLine(Long creditLineId);
}
