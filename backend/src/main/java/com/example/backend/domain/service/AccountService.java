package com.example.backend.domain.service;

import com.example.backend.domain.model.Account;

public interface AccountService {

  Account accountWithdrawal(Long accountId, Double amount);

  Account accountDeposit(Long accountId, Double amount);

  String accountHistory(Long accountId);
}
