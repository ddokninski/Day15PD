package models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    List<OrderLine> orderLines;

    public Order() {
        this.orderLines = new ArrayList<>();
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void addOrderLine(OrderLine orderLine) {
        boolean productExist = false;
        for (OrderLine line : orderLines) {
            if (line.getProduct().getName().equals(orderLine.getProduct().getName())) {
                productExist = true;
                line.setQuantity(line.getQuantity() + orderLine.getQuantity());
                line.setTotalPrice(line.getTotalPrice() + (line.getProduct().getPrice() * orderLine.getQuantity()));
            }
        }

        if (!productExist) {
            orderLines.add(orderLine);
        }
    }
}
