package hse.finance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    private Long id;
    private String type;
    private BankAccount bankAccount;
    private Double amount;
    private Date date;
    private String description;
    private Category category;
}
