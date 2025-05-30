package com.ps.core;

import java.math.BigDecimal;

public class BagOfChips extends MenuItem{
    private String type;

    public BagOfChips(String type) {
        this.type = type;
    }

    @Override
    public BigDecimal calcPrice() {
        return new BigDecimal("1.50");
    }

    @Override
    public String getName() {
        return "Chips (" + type + ")";
    }
}
