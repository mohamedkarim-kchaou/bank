package com.example.backend.exceptions;

public class AccountFoundException extends RuntimeException {

  public AccountFoundException() {
    super("Account Found Exception");
  }
}
