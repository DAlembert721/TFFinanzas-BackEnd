package com.acme.otorongo.service.promissories_service_impl;

import com.acme.otorongo.domain.model.promissories.PromissoryNoteInitialCost;
import com.acme.otorongo.domain.repository.costs_repository.InitialCostRepository;
import com.acme.otorongo.domain.repository.promissories_repository.PromissoryNoteInitialCostRepository;
import com.acme.otorongo.domain.repository.promissories_repository.PromissoryNoteRepository;
import com.acme.otorongo.domain.service.promissories_service.PromissoryNoteInitialCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromissoryNoteInitialCostServiceImpl implements PromissoryNoteInitialCostService {

    private final PromissoryNoteInitialCostRepository promissoryNoteInitialCostRepository;
    private final PromissoryNoteRepository promissoryNoteRepository;
    private final InitialCostRepository initialCostRepository;

    @Autowired
    public PromissoryNoteInitialCostServiceImpl(PromissoryNoteInitialCostRepository promissoryNoteInitialCostRepository,
                                                PromissoryNoteRepository promissoryNoteRepository,
                                                InitialCostRepository initialCostRepository){
        this.promissoryNoteInitialCostRepository = promissoryNoteInitialCostRepository;
        this.promissoryNoteRepository = promissoryNoteRepository;
        this.initialCostRepository = initialCostRepository;
    }

    @Override
    public List<PromissoryNoteInitialCost> getAllPromissoryNoteInitialsByPromissoryNoteId(Long promissoryNoteId) {
        return promissoryNoteInitialCostRepository.findByPromissoryNoteId(promissoryNoteId);
    }

    @Override
    public List<PromissoryNoteInitialCost> getAllPromissoryNoteInitialsByInitialCostId(Long initialCostId) {
        return promissoryNoteInitialCostRepository.findByInitialCostId(initialCostId);
    }

    @Override
    public PromissoryNoteInitialCost save(PromissoryNoteInitialCost promissoryNoteInitialCost,
                                          Long promissoryNoteId, Long initialCostId) {
        promissoryNoteInitialCost.setPromissoryNote(promissoryNoteRepository.findById(promissoryNoteId)
                .orElse(null));
        promissoryNoteInitialCost.setInitialCost(initialCostRepository.findById(initialCostId)
                .orElse(null));
        return promissoryNoteInitialCostRepository.save(promissoryNoteInitialCost);
    }

    @Override
    public PromissoryNoteInitialCost update(Long promissoryNoteId, Long initialCostId,
                                            PromissoryNoteInitialCost promissoryNoteInitialCost) {
        PromissoryNoteInitialCost existed = promissoryNoteInitialCostRepository
                .findByPromissoryNoteIdAndInitialCostId(promissoryNoteId, initialCostId);
        existed.setCost(promissoryNoteInitialCost.getCost());
        return promissoryNoteInitialCostRepository.save(existed);
    }

    @Override
    public void delete(Long promissoryNoteId, Long initialCostId) {
        PromissoryNoteInitialCost existed = promissoryNoteInitialCostRepository
                .findByPromissoryNoteIdAndInitialCostId(promissoryNoteId, initialCostId);
        promissoryNoteInitialCostRepository.delete(existed);
    }
}
