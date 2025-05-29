package com.ps.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sandwich implements Product {
    private String bread;
    private String size;
    private boolean toasted;

    private List<Topping> meats = new ArrayList<>();
    private List<Topping> cheeses = new ArrayList<>();
    private List<Topping> regularToppings = new ArrayList<>();
    private List<Topping> sauces = new ArrayList<>();

    public Sandwich(String bread, String size, boolean toasted) {
        this.bread = bread;
        this.size = size;
        this.toasted = toasted;
    }
    public void addMeat(Topping meat) {meats.add(meat);}
    public void addCheese(Topping cheese) {cheeses.add(cheese);}
    public void addRegularTopping(Topping topping) {regularToppings.add(topping);}
    public void addSauce(Topping sauce) {sauces.add(sauce);}

    @Override
    public BigDecimal calcPrice() {
        BigDecimal basePrice;
        switch(size) {
            case "4":
                basePrice = new BigDecimal("5.50");
                break;
            case "8":
                basePrice = new BigDecimal("7.00");
                break;
            case "12":
                basePrice = new BigDecimal("8.50");
                break;
            default:
                basePrice = BigDecimal.ZERO;
        }
        BigDecimal extras = BigDecimal.ZERO;
        for (Topping t : meats) extras = extras.add(t.getPrice(size));
        for (Topping t : cheeses) extras = extras.add(t.getPrice(size));

        return basePrice.add(extras);

    }
    @Override
    public String getName() {
        return size + "\" "+ bread + " Sandwich";
    }


}
