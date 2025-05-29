package com.ps;

import com.ps.core.*;
import com.ps.core.BagOfChips;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Order currentOrder;
    private FileManager fileManager = new FileManager();

    public void init() {

    }
    public void display() {
        while (true) {
            System.out.println("Welcome to Carreons Deli!");
            System.out.println("1) New Order");
            System.out.println("0) Exit ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    currentOrder = new Order();
                    orderMenu();
                    break;

                case "0":
                    System.out.println("Have a wonderful day!");
                    return;
                default:
                    System.out.println("Invalid, try again!");
            }
        }
    }
    private void orderMenu() {
        while (true) {
            System.out.println("== Order Menu ==");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            String menuChoice = scanner.nextLine();

            switch (menuChoice) {
                case "1":
                    Sandwich sandwich = buildSandwich();
                    currentOrder.addProduct(sandwich);
                    break;
                case "2":
                    Drink drink = buildDrink();
                    currentOrder.addProduct(drink);
                    break;
                case "3":
                    BagOfChips chips = buildChips();
                    currentOrder.addProduct(chips);
                    break;
                case "4":
                    checkout();
                    return;
                case "0":
                    System.out.println("Order cancelled");
                    return;
                default:
                    System.out.println("Invalid, try again.");
            }
        }
    }

    public Sandwich buildSandwich() {
        System.out.println("=== Build Sandwich ===");

        System.out.println("Select bread type:");
        System.out.println("1) White\n2) Wheat\n3) Rye\n4) Wrap");
        String[] breadOptions = {"White", "Wheat", "Rye", "Wrap"};
        String bread = breadOptions[scanner.nextInt() - 1];

        System.out.println("Select your sandwich size:");
        System.out.println("1) 4\"\n2) 8\"\n3) 12\"");
        String[] sizeOptions= {"4", "8", "12"};
        String size =sizeOptions[scanner.nextInt() - 1];

        System.out.println("Would you like it toasted? (y/n)");
        boolean toasted = scanner.next().equalsIgnoreCase("y");

        Sandwich sandwich = new Sandwich(bread, size, toasted);

        scanner.nextLine();

        String[] meats = {"Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"};
        for (String meat : meats) {
            System.out.printf("Add %s? (n, y, or x for extra): ", meat);
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("y")) {
                sandwich.addMeat(new Topping(meat, false, "Meat"));

            } else if (choice.equals("x")) {
                sandwich.addMeat(new Topping(meat, true, "Meat"));
            }
        }
        String[] cheeses = {"American", "Provolone", "Cheddar", "Swiss"};
        for (String cheese : cheeses) {
            System.out.printf("Add %s? (n, y, or x for extra): ", cheese);
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("y")) {
                sandwich.addCheese(new Topping(cheese, false, "Cheese"));
            } else if (choice.equals("x")) {
                sandwich.addCheese(new Topping(cheese, true, "Cheese"));
            }
        }
        String[] regulars = {"Lettuce", "Peppers", "Onions", "Tomatoes", "Cucumbers", "Guacamole", "Mushrooms"};
        for (String topping : regulars) {
            System.out.printf("Add %s? (y/n): ", topping);
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                sandwich.addRegularTopping(new Topping(topping, false, "Regular"));
            }
        }
        String[] sauces = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"};
        for (String sauce : sauces) {
            System.out.printf("Add %s? (y/n): ", sauce);
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                sandwich.addSauce(new Topping(sauce, false, "Sauce"));
            }
        }
        System.out.println("Your sandwich is ready: " + sandwich.getName());
        System.out.println("Total: $" + sandwich.calcPrice());
        return sandwich;

    }

    private Drink buildDrink() {
        System.out.println("=== Select Drink ===");
        System.out.println("Choose a size:");
        System.out.println("1) Small\n2) Medium\n3) Large");

        String sizeInput = scanner.nextLine();

        Drink.Size size = null;

        switch (sizeInput) {
            case "1":
                size = Drink.Size.Small;
                break;
            case "2":
                size = Drink.Size.Medium;
                break;
            case "3":
                size = Drink.Size.Large;
                break;
            default:
                System.out.println("Invalid, default size is small");
                size = Drink.Size.Small;
                break;

        }
        System.out.println("Choose Flavor:");
        String flavor = scanner.nextLine();

        return new Drink(size, flavor);

    }

    private BagOfChips buildChips() {
        System.out.println("=== Choose chips ===");
        String[] chipOptions = {
                "Classic", "BBQ", "Salt & Vinegar", "Sour cream", "Jalapeno"
        };
        for (int i = 0; i < chipOptions.length; i++) {
            System.out.printf("%d) %s%n", i + 1, chipOptions[i]);
        }
        String input = scanner.nextLine();
        int choice = 1;
        try {
            choice = Integer.parseInt(input);
            if (choice < 1 || choice > chipOptions.length) {
                System.out.println("Invalid selection, default selection Classic");
                choice = -1;
            }
        }catch (NumberFormatException e) {
            System.out.println("Invalid selection, default to Classic");
        }
        String chipType = chipOptions[choice - 1];
        return new BagOfChips(chipType);
    }

    private void checkout() {
        System.out.println("\n=== Checkout ===");

        if (currentOrder.getProducts().isEmpty()) {
            System.out.println("No items selected.");
            return;
        }
        System.out.println("Your order:");
        for (Product product : currentOrder.getProducts()) {
            System.out.println("- " + product.getName() + "...$" + product.calcPrice());
        }
        System.out.println("Total: $" + currentOrder.getTotal());
        System.out.println("1) Confirm and Save");
        System.out.println("2) Cancel and Return Home");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                fileManager.saveReceipt(currentOrder);
                System.out.println("Order confirmed, receipt saved! Returning home...");
                break;
            case "0":
                System.out.println("Order cancelled.");
                break;
            default:
                System.out.println("Invalid selection, returning home.");
        }
    }


}
