package com.example.backend.persistence.repositories.adapters;

import com.example.backend.domain.model.Account;
import com.example.backend.domain.model.Transaction;
import com.example.backend.domain.repositoryPort.AccountRepositoryPort;
import com.example.backend.domain.repositoryPort.TransactionRepositoryPort;
import com.example.backend.persistence.entities.AccountEntity;
import com.example.backend.persistence.entities.TransactionEntity;
import com.example.backend.persistence.repositories.jpaRepositories.AccountJpaRepository;
import com.example.backend.persistence.repositories.jpaRepositories.TransactionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class transactionRepositoryAdapter implements TransactionRepositoryPort {
    private final TransactionJpaRepository transactionJpaRepository;

    @Override
    public List<Transaction> findAllByAccountId(Long accountId) {
        return transactionJpaRepository.findAllByAccountEntityId(accountId).stream().map(TransactionEntity::toDomainModel).collect(Collectors.toList());
    }
}
