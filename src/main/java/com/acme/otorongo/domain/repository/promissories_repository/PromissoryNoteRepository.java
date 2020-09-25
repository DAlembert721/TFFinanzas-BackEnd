package com.acme.otorongo.domain.repository.promissories_repository;

import com.acme.otorongo.domain.model.locations.District;
import com.acme.otorongo.domain.model.promissories.PromissoryNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromissoryNoteRepository extends JpaRepository<PromissoryNote, Long> {
    List<PromissoryNote> findByClientId(Long clientId);
    List<PromissoryNote> findByRateId(Long rateId);
    List<PromissoryNote> findByQuotationId(Long quotationId);
    List<PromissoryNote> findByCurrencyId(Long currencyId);
    void deleteById(Long id);
}
