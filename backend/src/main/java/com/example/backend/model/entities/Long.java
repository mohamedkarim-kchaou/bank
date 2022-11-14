package com.example.backend.model.entities;

import com.example.backend.exceptions.InsufficientBalanceException;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Long {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Builder.Default private Double balance = 0.;

  @OneToOne private User user;

  public void withdrawalCommand(Double amount) {
    if (amount > balance) {
      throw new InsufficientBalanceException();
    } else {
      balance -= amount;
    }
  }

  public void depositCommand(Double amount) {
    balance += amount;
  }
}
