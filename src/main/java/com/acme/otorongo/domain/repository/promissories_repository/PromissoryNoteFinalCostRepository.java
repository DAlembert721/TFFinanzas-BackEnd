package com.acme.otorongo.domain.repository.promissories_repository;

import com.acme.otorongo.domain.model.promissories.PromissoryNoteFinalCost;
import com.acme.otorongo.domain.model.promissories.PromissoryNoteFinalCostKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromissoryNoteFinalCostRepository extends JpaRepository<PromissoryNoteFinalCost,
        PromissoryNoteFinalCostKey> {
    List<PromissoryNoteFinalCost> findByPromissoryNoteId(Long promissoryNoteId);
    List<PromissoryNoteFinalCost> findByFinalCostId(Long finalCostId);
    PromissoryNoteFinalCost findByPromissoryNoteIdAndFinalCostId(Long promissoryNoteId, Long finalCostId);
}
