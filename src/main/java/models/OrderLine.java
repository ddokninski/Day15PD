package models;

import lombok.Data;

import java.math.BigDecimal;

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

//    public OrderLine() {
//        // na potrzeby kompilacji kodu
//    }

//    public Product getProduct() {
//        return product;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }

//    public OrderLine getOrderLineDetails() {
//        //do konstruktora wsadzić nazwę cenę cenę łączną z tego co widzi na stronie
//        return new OrderLine();
//    }
}
