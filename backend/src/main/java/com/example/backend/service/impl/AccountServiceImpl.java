package com.example.backend.service.impl;

import com.example.backend.exceptions.AccountFoundException;
import com.example.backend.exceptions.AccountNotFoundException;
import com.example.backend.model.entities.*;
import com.example.backend.model.entities.Long;
import com.example.backend.repositories.AccountRepository;
import com.example.backend.service.AccountService;
import com.example.backend.service.TransactionService;
import com.example.backend.service.UserService;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
  private final AccountRepository accountRepository;
  private final UserService userService;

  private final TransactionService transactionService;

  @Override
  public Long createAccount(String name) {
    Optional<User> foundUser = userService.findUserByName(name);
    User user = foundUser.orElseGet(() -> userService.saveUser(User.builder().name(name).build()));
    Optional<Long> account = accountRepository.findByUser(user);
    if (account.isPresent()) {
      throw new AccountFoundException();
    } else {
      return accountRepository.save(Long.builder().user(user).build());
    }
  }

  @Override
  public Long accountWithdrawal(Long accountId, Double amount) {
    Long account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
    Transaction transaction = new WithdrawalTransaction();
    transaction.setAccount(account);
    transaction.setDate(ZonedDateTime.now());
    transaction.setAmount(amount);
    transactionService.execute(transaction);
    transactionService.save(transaction);
    return accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
  }

  @Override
  public Long accountDeposit(Long accountId, Double amount) {
    Long account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
    Transaction transaction = new DepositTransaction();
    transaction.setAccount(account);
    transaction.setDate(ZonedDateTime.now());
    transaction.setAmount(amount);
    transactionService.execute(transaction);
    transactionService.save(transaction);
    return accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
  }

  @Override
  public String accountHistory(Long accountId) {
    List<Transaction> transactions =
        transactionService.findByAccount(
            accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new));
    StringBuilder result = new StringBuilder();
    Double initialBalance = 0.;
    for (Transaction transaction : transactions) {
      Map<Double, String> element = transaction.printHistory(initialBalance);
      initialBalance = element.keySet().stream().findFirst().get();
      result.append(element.get(initialBalance));
      result.append('\n');
    }
    return result.toString();
  }
}
