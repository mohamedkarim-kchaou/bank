package com.example.backend.model.entities;

import java.time.ZonedDateTime;
import java.util.Map;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  private ZonedDateTime date;
  private Double amount;

  @ManyToOne private Long account;

  public abstract void execute();

  public abstract Map<Double, String> printHistory(Double oldAccountBalance);
}
