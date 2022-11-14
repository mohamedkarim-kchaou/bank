package com.example.backend.service;

import com.example.backend.model.entities.Long;

public interface AccountService {
  Long createAccount(String name);

  Long accountWithdrawal(Long accountId, Double amount);

  Long accountDeposit(Long accountId, Double amount);

  String accountHistory(Long accountId);
}
