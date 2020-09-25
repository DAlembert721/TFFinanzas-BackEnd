package com.acme.otorongo.service.expenses_service_impl;

import com.acme.otorongo.domain.model.expenses.CreditLine;
import com.acme.otorongo.domain.repository.expenses_repository.CreditLineRepository;
import com.acme.otorongo.domain.repository.users_repository.ClientRepository;
import com.acme.otorongo.domain.service.expenses_service.CreditLineService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditLineServiceImpl implements CreditLineService {

    private final CreditLineRepository creditLineRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public CreditLineServiceImpl(CreditLineRepository creditLineRepository, ClientRepository clientRepository){
        this.creditLineRepository = creditLineRepository;
        this.clientRepository = clientRepository;
    }


    @Override
    public List<CreditLine> getAllCreditLines() {
        return creditLineRepository.findAll();
    }

    @Override
    public List<CreditLine> getAllCreditLinesByClientId(Long clientId) {
        return creditLineRepository.findByClientId(clientId);
    }

    @Override
    public CreditLine createCreditLine(CreditLine creditLine, Long clientId) {
        creditLine.setClient(clientRepository.findById(clientId).orElse(null));
        return creditLineRepository.save(creditLine);
    }

    @Override
    public CreditLine getCreditLineById(Long creditLineId) {
        return creditLineRepository.findById(creditLineId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("CreditLine", "Id", creditLineId));
    }

    @Override
    public CreditLine updateCreditLine(Long creditLineId, CreditLine creditLine) {
        CreditLine existed = creditLineRepository.findById(creditLineId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("CreditLine", "Id", creditLineId));
        existed.setTotal(creditLine.getTotal());
        existed.setBalance(creditLine.getBalance());
        return creditLineRepository.save(existed);
    }

    @Override
    public ResponseEntity<?> deleteCreditLine(Long creditLineId) {
        creditLineRepository.deleteById(creditLineId);
        return ResponseEntity.ok().build();
    }
}
