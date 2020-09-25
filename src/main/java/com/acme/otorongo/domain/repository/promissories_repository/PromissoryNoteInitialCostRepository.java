package com.acme.otorongo.domain.repository.promissories_repository;

import com.acme.otorongo.domain.model.promissories.PromissoryNoteInitialCost;
import com.acme.otorongo.domain.model.promissories.PromissoryNoteInitialCostKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromissoryNoteInitialCostRepository extends JpaRepository<PromissoryNoteInitialCost,
        PromissoryNoteInitialCostKey> {
    List<PromissoryNoteInitialCost> findByPromissoryNoteId(Long promissoryNoteId);
    List<PromissoryNoteInitialCost> findByInitialCostId(Long initialCostId);
    PromissoryNoteInitialCost findByPromissoryNoteIdAndInitialCostId(Long promissoryNoteId, Long initialCostId);
}
