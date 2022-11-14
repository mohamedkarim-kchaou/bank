package com.example.backend.repositories;

import com.example.backend.model.entities.Long;
import com.example.backend.model.entities.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  @Query("select t from Transaction t where t.account.id=(:acccountId)")
  List<Transaction> findByAccount(Long accountId);
}
