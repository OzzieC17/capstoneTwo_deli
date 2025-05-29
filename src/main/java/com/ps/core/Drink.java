package com.ps.core;

import java.math.BigDecimal;

public class Drink implements Product {
    public enum Size { Small, Medium, Large}

    private Size size;
    private String flavor;

    public Drink(Size size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public Size getSize() {
        return size;
    }


    public String getFlavor() {
        return flavor;
    }


    @Override
    public BigDecimal calcPrice() {

        switch (size) {
            case Small:
                return new BigDecimal("2.00");
            case Medium:
                return new BigDecimal("2.50");
            case Large:
                return new BigDecimal("3.00");
            default:
                return BigDecimal.ZERO;
        }
    }

    @Override
    public String getName() {
        return size + " " + flavor;
    }
}




