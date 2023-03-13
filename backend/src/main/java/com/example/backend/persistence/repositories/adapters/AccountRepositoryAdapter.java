package com.example.backend.persistence.repositories.adapters;

import com.example.backend.domain.model.Account;
import com.example.backend.domain.repositoryPort.AccountRepositoryPort;
import com.example.backend.persistence.entities.AccountEntity;
import com.example.backend.persistence.repositories.jpaRepositories.AccountJpaRepository;
import com.example.backend.persistence.repositories.jpaRepositories.TransactionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepositoryPort {
    private final AccountJpaRepository accountJpaRepository;
    @Override
    public Optional<Account> findById(Long accountId) {
        Optional<Account> account = Optional.empty();
        Optional<AccountEntity> accountEntity = accountJpaRepository.findById(accountId);
        if (accountEntity.isPresent()){
            account = Optional.of(accountEntity.get().toDomainModel());
        }
        return account;
    }

    @Override
    public Account save(Account account) {
        return accountJpaRepository.save(AccountEntity.fromDomainModel(account)).toDomainModel();
    }
}
