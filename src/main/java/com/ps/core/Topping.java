package com.ps.core;

import java.math.BigDecimal;

public class Topping {
    private String name;
    private boolean isExtra;
    private String category;

    public Topping(String name, boolean isExtra, String category) {
        this.name = name;
        this.isExtra = isExtra;
        this.category = category;
    }

    public BigDecimal getPrice(String size) {
        if (category.equals("Regular") || category.equals("Sauce")) {
            return BigDecimal.ZERO;
        }

        switch (category) {
            case "Meat":
                return isExtra ? getExtraMeatPrice(size) : getMeatPrice(size);
            case "Cheese":
                return isExtra ? getExtraCheese(size) : getCheesePrice(size);
            default:
                return BigDecimal.ZERO;
        }
    }


    private BigDecimal getMeatPrice(String size) {
        switch (size) {
            case "4": return new BigDecimal("1.00");
            case "8": return new BigDecimal("2.00");
            case "12": return new BigDecimal("3.00");
            default: return BigDecimal.ZERO;
        }
    }
    private BigDecimal getExtraMeatPrice(String size) {
        switch (size) {
            case "4": return new BigDecimal(".50");
            case "8": return new BigDecimal("1.00");
            case "12": return new BigDecimal("1.50");
            default: return BigDecimal.ZERO;
        }
    }
    private BigDecimal getCheesePrice(String size) {
        switch (size) {
            case "4": return new BigDecimal(".75");
            case "8": return new BigDecimal("1.50");
            case "12": return new BigDecimal("2.25");
            default: return BigDecimal.ZERO;
        }
    }
    private BigDecimal getExtraCheese(String size) {
        switch (size) {
            case "4": return new BigDecimal(".30");
            case "8": return new BigDecimal(".60");
            case "12": return new BigDecimal(".90");
            default: return BigDecimal.ZERO;
        }
    }
    public String getName() {
        return name + (isExtra ? " (extra)" : "");
    }
}
