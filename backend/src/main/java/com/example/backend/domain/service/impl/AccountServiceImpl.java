package com.example.backend.domain.service.impl;

import com.example.backend.domain.model.Account;
import com.example.backend.domain.model.Transaction;
import com.example.backend.domain.repositoryPort.AccountRepositoryPort;
import com.example.backend.domain.repositoryPort.TransactionRepositoryPort;
import com.example.backend.domain.service.AccountService;
import com.example.backend.domain.useCase.AccountOperations;
import com.example.backend.domain.useCase.TransactionsHistory;
import com.example.backend.exceptions.AccountNotFoundException;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
  private final AccountRepositoryPort accountRepository;
  private final TransactionRepositoryPort transactionRepositoryPort;

  @Override
  public Account accountWithdrawal(Long accountId, Double amount) {
    Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
    return accountRepository.save(AccountOperations.withdrawal(account, amount));
  }

  @Override
  public Account accountDeposit(Long accountId, Double amount) {
    Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
    return accountRepository.save(AccountOperations.deposit(account, amount));
  }

  @Override
  public String accountHistory(Long accountId) {
    List<Transaction> transactions = transactionRepositoryPort.findAllByAccountId(accountId);
    return TransactionsHistory.showHistory(transactions);
  }
}
