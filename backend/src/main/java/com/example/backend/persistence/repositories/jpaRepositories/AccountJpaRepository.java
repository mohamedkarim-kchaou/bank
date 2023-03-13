package com.example.backend.persistence.repositories.jpaRepositories;

import com.example.backend.persistence.entities.AccountEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
  Optional<AccountEntity> findById(Long accountId);
}
