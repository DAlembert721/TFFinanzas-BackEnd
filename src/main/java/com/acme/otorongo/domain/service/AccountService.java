package com.acme.otorongo.domain.service;

import com.acme.otorongo.domain.model.Account;
import com.acme.otorongo.domain.model.User;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    Account createAccount(Long userId, Account account);

    Account getAccountByIdAndUserId(Long userId, Long accountId);

    Account updateAccount(Long userId, Long accountId, Account accountRequest);

    ResponseEntity<?> deleteAccount(Long userId, Long accountId);
}
