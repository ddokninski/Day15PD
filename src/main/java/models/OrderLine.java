package models;

import lombok.Data;

@Data
public class OrderLine {
    private Product product;
    private int quantity;
    private double totalPrice;

    public OrderLine(Product product, int quantity, double totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
