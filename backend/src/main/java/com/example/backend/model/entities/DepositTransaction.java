package com.example.backend.model.entities;

import com.example.backend.enumerations.Operation;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DiscriminatorValue("Deposit")
public class DepositTransaction extends Transaction {
  @Override
  public void execute() {
    this.getAccount().depositCommand(this.getAmount());
  }

  @Override
  public Map<Double, String> printHistory(Double oldAccountBalance) {
    Map<Double, String> result = new HashMap<>();
    result.put(
        oldAccountBalance + this.getAmount(),
        Operation.DEPOSIT.name()
            + ' '
            + this.getDate()
            + ' '
            + this.getAmount()
            + ' '
            + (oldAccountBalance + this.getAmount()));
    return result;
  }
}
