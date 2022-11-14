package com.example.backend.controllers;

import com.example.backend.model.entities.Long;
import com.example.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
  private final AccountService accountService;

  @PostMapping("/create")
  @Transactional
  public ResponseEntity<Long> createAccount(@RequestBody String name) {
    return ResponseEntity.ok(accountService.createAccount(name));
  }

  @PostMapping("/{accountId}/withdrawal")
  @Transactional
  public ResponseEntity<Long> accountWithdrawal(
      @PathVariable("accountId") Long accountId, @RequestBody Double amount) {
    return ResponseEntity.ok(accountService.accountWithdrawal(accountId, amount));
  }

  @PostMapping("/{accountId}/deposit")
  @Transactional
  public ResponseEntity<Long> accountDeposit(
      @PathVariable("accountId") Long accountId, @RequestBody Double amount) {
    return ResponseEntity.ok(accountService.accountDeposit(accountId, amount));
  }

  @GetMapping("/{accountId}/history")
  @Transactional
  public ResponseEntity<String> accountDeposit(@PathVariable("accountId") Long accountId) {
    return ResponseEntity.ok(accountService.accountHistory(accountId));
  }
}
