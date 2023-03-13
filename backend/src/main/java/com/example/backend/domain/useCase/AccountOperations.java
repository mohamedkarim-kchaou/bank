package com.example.backend.domain.useCase;

import com.example.backend.domain.enumerations.Operation;
import com.example.backend.domain.model.Account;
import com.example.backend.domain.model.Transaction;
import com.example.backend.exceptions.InsufficientBalanceException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountOperations {
  public static Account withdrawal(Account account, Double amount) {
    if (Objects.isNull(account.getBalance())) {
      account.setBalance((double) 0);
    }
    if (account.getBalance()<amount){
      throw new InsufficientBalanceException();
    }
    Transaction transaction =
        Transaction.builder()
            .date(ZonedDateTime.now())
            .oldBalance(account.getBalance())
            .operation(Operation.WITHDRAWAL)
            .amount(amount)
            .newBalance(account.getBalance() - amount)
            .build();
    account.setBalance(account.getBalance() - amount);
    if (account.getTransactions().isEmpty()) {
      account.setTransactions(new ArrayList<>(List.of(transaction)));
    } else {
      account.getTransactions().add(transaction);
    }
    return account;
  }

  public static Account deposit(Account account, Double amount) {
    if (Objects.isNull(account.getBalance())) {
      account.setBalance((double) 0);
    }
    Transaction transaction =
        Transaction.builder()
            .date(ZonedDateTime.now())
            .oldBalance(account.getBalance())
            .operation(Operation.DEPOSIT)
            .amount(amount)
            .newBalance(account.getBalance() + amount)
            .build();
    account.setBalance(account.getBalance() + amount);
    if (account.getTransactions().isEmpty()) {
      account.setTransactions(new ArrayList<>(List.of(transaction)));
    } else {
      account.getTransactions().add(transaction);
    }
    return account;
  }
}
