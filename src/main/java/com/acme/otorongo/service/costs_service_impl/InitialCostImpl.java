package com.acme.otorongo.service.costs_service_impl;

import com.acme.otorongo.domain.model.costs.InitialCost;
import com.acme.otorongo.domain.repository.costs_repository.InitialCostRepository;
import com.acme.otorongo.domain.service.costs_service.InitialCostService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitialCostImpl implements InitialCostService {

    private final InitialCostRepository initialCostRepository;

    @Autowired
    public InitialCostImpl(InitialCostRepository initialCostRepository){
        this.initialCostRepository = initialCostRepository;
    }

    @Override
    public List<InitialCost> getAllInitialCosts() {
        return initialCostRepository.findAll();
    }

    @Override
    public InitialCost createInitialCost(InitialCost initialCost) {
        return initialCostRepository.save(initialCost);
    }

    @Override
    public InitialCost getInitialCostById(Long initialCostId) {
        return initialCostRepository.findById(initialCostId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("InitialCost", "Id", initialCostId));
    }

    @Override
    public InitialCost updateInitialCost(Long initialCostId, InitialCost initialCost) {
        InitialCost existed = initialCostRepository.findById(initialCostId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("InitialCost", "Id", initialCostId));
        existed.setName(initialCost.getName());
        return initialCostRepository.save(existed);
    }

    @Override
    public ResponseEntity<?> deleteInitialCost(Long initialCostId) {
        initialCostRepository.deleteById(initialCostId);
        return ResponseEntity.ok().build();
    }
}
