package com.acme.otorongo.service.promissories_service_impl;

import com.acme.otorongo.domain.model.promissories.PromissoryNoteFinalCost;
import com.acme.otorongo.domain.repository.costs_repository.FinalCostRepository;
import com.acme.otorongo.domain.repository.promissories_repository.PromissoryNoteFinalCostRepository;
import com.acme.otorongo.domain.repository.promissories_repository.PromissoryNoteRepository;
import com.acme.otorongo.domain.service.promissories_service.PromissoryNoteFinalCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromissoryNoteFinalCostServiceImpl implements PromissoryNoteFinalCostService {
    private final PromissoryNoteFinalCostRepository promissoryNoteFinalCostRepository;
    private final PromissoryNoteRepository promissoryNoteRepository;
    private final FinalCostRepository finalCostRepository;

    @Autowired
    public PromissoryNoteFinalCostServiceImpl(PromissoryNoteFinalCostRepository promissoryNoteFinalCostRepository,
                                              PromissoryNoteRepository promissoryNoteRepository,
                                              FinalCostRepository finalCostRepository){
        this.promissoryNoteFinalCostRepository = promissoryNoteFinalCostRepository;
        this.promissoryNoteRepository = promissoryNoteRepository;
        this.finalCostRepository = finalCostRepository;
    }

    @Override
    public List<PromissoryNoteFinalCost> getAllPromissoryNoteFinalsByPromissoryNoteId(Long promissoryNoteId) {
        return promissoryNoteFinalCostRepository.findByPromissoryNoteId(promissoryNoteId);
    }

    @Override
    public List<PromissoryNoteFinalCost> getAllPromissoryNoteFinalsByFinalCostId(Long finalCostId) {
        return promissoryNoteFinalCostRepository.findByFinalCostId(finalCostId);
    }

    @Override
    public PromissoryNoteFinalCost save(PromissoryNoteFinalCost promissoryNoteFinalCost, Long promissoryNoteId,
                                        Long finalCostId) {
        promissoryNoteFinalCost.setPromissoryNote(promissoryNoteRepository.findById(promissoryNoteId)
                .orElse(null));
        promissoryNoteFinalCost.setFinalCost(finalCostRepository.findById(finalCostId)
                .orElse(null));
        return promissoryNoteFinalCostRepository.save(promissoryNoteFinalCost);
    }

    @Override
    public PromissoryNoteFinalCost update(Long promissoryNoteId, Long finalCostId, PromissoryNoteFinalCost promissoryNoteFinalCost) {
        PromissoryNoteFinalCost existed = promissoryNoteFinalCostRepository
                .findByPromissoryNoteIdAndFinalCostId(promissoryNoteId, finalCostId);
        existed.setCost(promissoryNoteFinalCost.getCost());
        return promissoryNoteFinalCostRepository.save(existed);
    }

    @Override
    public void delete(Long promissoryNoteId, Long finalCostId) {
        PromissoryNoteFinalCost existed = promissoryNoteFinalCostRepository
                .findByPromissoryNoteIdAndFinalCostId(promissoryNoteId, finalCostId);
        promissoryNoteFinalCostRepository.delete(existed);
    }
}
