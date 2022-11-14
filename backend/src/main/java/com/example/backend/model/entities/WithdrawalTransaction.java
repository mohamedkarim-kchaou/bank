package com.example.backend.model.entities;

import com.example.backend.enumerations.Operation;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DiscriminatorValue("Withdrawal")
public class WithdrawalTransaction extends Transaction {

  @Override
  public void execute() {
    this.getAccount().withdrawalCommand(this.getAmount());
  }

  @Override
  public Map<Double, String> printHistory(Double oldAccountBalance) {
    Map<Double, String> result = new HashMap<>();
    result.put(
        oldAccountBalance - this.getAmount(),
        Operation.DEPOSIT.name()
            + ' '
            + this.getDate()
            + ' '
            + this.getAmount()
            + ' '
            + (oldAccountBalance - this.getAmount()));
    return result;
  }
}
