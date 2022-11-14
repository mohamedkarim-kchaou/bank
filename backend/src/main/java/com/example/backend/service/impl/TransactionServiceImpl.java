package com.example.backend.service.impl;

import com.example.backend.model.entities.Long;
import com.example.backend.model.entities.Transaction;
import com.example.backend.repositories.TransactionRepository;
import com.example.backend.service.TransactionService;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Setter
@Service
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository transactionRepository;

  @Override
  public void execute(Transaction transaction) {
    transaction.execute();
  }

  @Override
  public Transaction save(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  @Override
  public List<Transaction> findByAccount(Long accountId) {
    return transactionRepository.findByAccount(accountId);
  }
}
