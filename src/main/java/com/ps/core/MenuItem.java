package com.ps.core;

import java.math.BigDecimal;

public abstract class MenuItem implements Product {
    protected String name;

    @Override
    public String getName() {
        return name;
    }
    public abstract BigDecimal calcPrice();
}
