package com.example.backend.domain.service;

import com.example.backend.persistence.entities.AccountEntity;
import com.example.backend.persistence.entities.TransactionEntity;
import java.util.List;

public interface TransactionService {
  void execute(TransactionEntity transactionEntity);

  TransactionEntity save(TransactionEntity transactionEntity);

  List<TransactionEntity> findByAccount(AccountEntity accountEntity);
}
