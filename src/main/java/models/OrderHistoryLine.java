package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderHistoryLine {

    private String orderNumber;
    private String date;
    private double totalPrice;
    private String statusOfPayment;

}
