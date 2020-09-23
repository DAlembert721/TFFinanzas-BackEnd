package com.acme.otorongo.service;

import com.acme.otorongo.domain.model.Account;
import com.acme.otorongo.domain.model.User;
import com.acme.otorongo.domain.repository.AccountRepository;
import com.acme.otorongo.domain.repository.UserRepository;
import com.acme.otorongo.domain.service.AccountService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Account createAccount(Long userId, Account account) {
        return userRepository.findById(userId)
                .map(user -> {
                    account.setUser(user);
                    return accountRepository.save(account);
                }).orElseThrow(() ->
                        new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Account getAccountByIdAndUserId(Long userId, Long accountId) {
        return accountRepository.findByIdAndUserId(accountId,userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account not found with Id" + accountId
                        + " and userId" + userId));
    }

    @Override
    public Account updateAccount(Long userId, Long accountId, Account accountRequest) {
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return accountRepository.findById(accountId)
                .map(account -> {
                    account.setFirstName(accountRequest.getFirstName());
                    account.setLastName(accountRequest.getLastName());
                    account.setDni(accountRequest.getDni());
                    account.setPhone(accountRequest.getPhone());
                    return accountRepository.save(account);
                }).orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));
    }

    @Override
    public ResponseEntity<?> deleteAccount(Long userId, Long accountId) {
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return accountRepository.findById(accountId)
                .map(account -> {
                    accountRepository.delete(account);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));
    }
}
