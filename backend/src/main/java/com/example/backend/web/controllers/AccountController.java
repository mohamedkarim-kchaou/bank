package com.example.backend.web.controllers;

import com.example.backend.persistence.entities.AccountEntity;
import com.example.backend.domain.service.AccountService;
import com.example.backend.web.dto.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
  private final AccountService accountService;

  @PostMapping("/{accountId}/withdrawal")
  @Transactional
  public ResponseEntity<AccountResponse> accountWithdrawal(
          @PathVariable Long accountId, @RequestBody Double amount) {
    return ResponseEntity.ok(AccountResponse.response(accountService.accountWithdrawal(accountId, amount)));
  }

  @PostMapping("/{accountId}/deposit")
  @Transactional
  public ResponseEntity<AccountResponse> accountDeposit(
          @PathVariable Long accountId, @RequestBody Double amount) {
    return ResponseEntity.ok(AccountResponse.response(accountService.accountDeposit(accountId, amount)));
  }

  @GetMapping("/{accountId}/history")
  @Transactional
  public ResponseEntity<String> accountHistory(@PathVariable("accountId") Long accountId) {
    return ResponseEntity.ok(accountService.accountHistory(accountId));
  }
}
