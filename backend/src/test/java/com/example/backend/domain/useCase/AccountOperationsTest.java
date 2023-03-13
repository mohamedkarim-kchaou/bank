package com.example.backend.domain.useCase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.backend.domain.enumerations.Operation;
import com.example.backend.domain.model.Account;
import com.example.backend.domain.model.Transaction;
import com.example.backend.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountOperationsTest {
    private ZonedDateTime dateTime;

    @BeforeEach
    void setUp() {
        ZonedDateTime dateTime = ZonedDateTime.now();
    }

    @Test
    void withdrawalWithNewEmptyAccount() {
        // Given
        Account account = Account.builder().build();
        // When
        // Then
        assertThrows(InsufficientBalanceException.class, ()->AccountOperations.withdrawal(account, 10.0));
    }
    @Test
    void withdrawalWithNewAccountContainingSufficientBalance() {
        // Given
        Account account = Account.builder().balance(20.).build();
        // When
        try (MockedStatic<ZonedDateTime> mockedLocalDateTime = Mockito.mockStatic(ZonedDateTime.class)) {
            mockedLocalDateTime.when(ZonedDateTime::now).thenReturn(dateTime);
            Account result = AccountOperations.withdrawal(account, 10.);
            // Then
            Account expected = Account.builder().balance(10.).transactions(List.of(Transaction.builder().operation(Operation.WITHDRAWAL).oldBalance(20.).newBalance(10.).date(dateTime).amount(10.).build())).build();
            assertThat(result).isEqualTo(expected);
        }
    }

    @Test
    void deposit() {
        // Given
        Account account = Account.builder().balance(20.).build();
        // When
        try (MockedStatic<ZonedDateTime> mockedLocalDateTime = Mockito.mockStatic(ZonedDateTime.class)) {
            mockedLocalDateTime.when(ZonedDateTime::now).thenReturn(dateTime);
            Account result = AccountOperations.deposit(account, 10.);
            // Then
            Account expected = Account.builder().balance(30.).transactions(List.of(Transaction.builder().operation(Operation.DEPOSIT).oldBalance(20.).newBalance(30.).date(dateTime).amount(10.).build())).build();
            assertThat(result).isEqualTo(expected);
        }
    }
}