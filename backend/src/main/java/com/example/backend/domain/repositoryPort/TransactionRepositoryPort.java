package com.example.backend.domain.repositoryPort;

import com.example.backend.domain.model.Account;
import com.example.backend.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepositoryPort {
  List<Transaction> findAllByAccountId(Long accountId);
}
