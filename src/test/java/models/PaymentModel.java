package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModel {
    private String id;
    private long amount;
    private String created;
    private String status;
    private String transaction_id;
}