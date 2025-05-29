package com.ps.core;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products;
    private LocalDateTime timestamp;

    public Order() {
        this.products = new ArrayList<>();
        this.timestamp = LocalDateTime.now();
    }

    public void addProduct(Product product) {
        products.add(0, product);

    }
    public List<Product> getProducts() {
        return products;

    }
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product p : products) {
            total = total.add(p.calcPrice());
        }
        return total;
    }
    public void cancel() {
        products.clear();
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order summary:\n");
        for (Product p : products) {
            sb.append("- ").append(p.getName()).append(": S").append(p.calcPrice()).append("\n");
        }
        sb.append("Total: $").append(getTotal());
        return sb.toString();
    }
}
