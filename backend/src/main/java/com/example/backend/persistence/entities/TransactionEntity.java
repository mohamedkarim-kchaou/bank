package com.example.backend.persistence.entities;

import com.example.backend.domain.model.Transaction;
import com.example.backend.persistence.enumerations.OperationEntity;
import java.time.ZonedDateTime;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TransactionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  private ZonedDateTime date;
  private Double amount;
  private OperationEntity operationEntity;
  private Double oldBalance;
  private Double newBalance;
  @ManyToOne private AccountEntity accountEntity;

  public Transaction toDomainModel() {
    return Transaction.builder()
        .id(id)
        .amount(amount)
        .newBalance(newBalance)
        .oldBalance(oldBalance)
        .operation(OperationEntity.toDomainOperation(operationEntity))
        .date(date)
        .build();
  }

  public static TransactionEntity fromDomainModel(Transaction transaction) {
    return TransactionEntity.builder()
        .id(transaction.getId())
        .amount(transaction.getAmount())
        .newBalance(transaction.getNewBalance())
        .oldBalance(transaction.getOldBalance())
        .operationEntity(OperationEntity.fromDomainOperation(transaction.getOperation()))
        .date(transaction.getDate())
        .build();
  }
}
