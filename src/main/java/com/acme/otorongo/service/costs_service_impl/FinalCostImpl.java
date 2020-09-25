package com.acme.otorongo.service.costs_service_impl;

import com.acme.otorongo.domain.model.costs.FinalCost;
import com.acme.otorongo.domain.repository.costs_repository.FinalCostRepository;
import com.acme.otorongo.domain.service.costs_service.FinalCostService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalCostImpl implements FinalCostService {

    private final FinalCostRepository finalCostRepository;

    @Autowired
    public FinalCostImpl(FinalCostRepository finalCostRepository){
        this.finalCostRepository = finalCostRepository;
    }

    @Override
    public List<FinalCost> getAllFinalCosts() {
        return finalCostRepository.findAll();
    }

    @Override
    public FinalCost createFinalCost(FinalCost finalCost) {
        return finalCostRepository.save(finalCost);
    }

    @Override
    public FinalCost getFinalCostById(Long finalCostId) {
        return finalCostRepository.findById(finalCostId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("FinalCost", "Id", finalCostId));
    }

    @Override
    public FinalCost updateFinalCost(Long finalCostId, FinalCost finalCost) {
        FinalCost existed = finalCostRepository.findById(finalCostId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("FinalCost", "Id", finalCostId));
        existed.setName(finalCost.getName());
        return finalCostRepository.save(existed);
    }

    @Override
    public ResponseEntity<?> deleteFinalCost(Long finalCostId) {
        finalCostRepository.deleteById(finalCostId);
        return ResponseEntity.ok().build();
    }
}
