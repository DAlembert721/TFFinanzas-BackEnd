package com.acme.otorongo.domain.service.operations_service;

import com.acme.otorongo.domain.model.operations.Annuity;

import java.util.List;

public interface AnnuityService {
    List<Annuity> getAllAnnuities();
    List<Annuity> getAllAnnuitiesByRateId(Long rateId);
    List<Annuity> getAllAnnuitiesByQuotationId(Long quotationId);
    Annuity save(Annuity annuity, Long clientId,
                 Long currencyId, Long quotationId, Long rateId);
    Annuity update(Long annuityId, Annuity annuity);
}
