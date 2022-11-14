package com.example.backend.service;

import com.example.backend.model.entities.Long;
import com.example.backend.model.entities.Transaction;
import java.util.List;

public interface TransactionService {
  void execute(Transaction transaction);

  Transaction save(Transaction transaction);

  List<Transaction> findByAccount(Long account);
}
