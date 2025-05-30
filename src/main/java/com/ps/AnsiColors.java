package com.ps;

import java.util.Random;

public class AnsiColors {
    public static final String RESET = "\033[0m";
    private static final String[] COLORS = {
            "\u001B[31m",
            "\u001B[32m",
            "\u001B[33m",
            "\u001B[34m",
            "\u001B[35m",
            "\u001B[36m",
            "\u001B[91m",
            "\u001B[92m",
            "\u001B[93m",
            "\u001B[94m",
            "\u001B[95m",
            "\u001B[96m"

    };
    private static final Random random = new Random();
    public static final String BRIGHT = "\u001B[1m";
    public static final String DIM = "\u001B[2m";

    public static String colorizeRandom(String text) {
        return COLORS[random.nextInt(COLORS.length)] + text + RESET;
    }
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static void pulsate(String message, int pulses, int delayMillis) {
        String[] colors = {RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN}; // your ANSI color codes

        try {
            for (int i = 0; i < pulses; i++) {
                String color = colors[i % colors.length]; // cycle through colors

                // Dim pulse with color
                System.out.print(DIM + color + message + RESET + "\r");
                Thread.sleep(delayMillis);

                // Bright pulse with same color
                System.out.print(BRIGHT + color + message + RESET + "\r");
                Thread.sleep(delayMillis);
            }
            // Leave message bright in last color
            String lastColor = colors[(pulses - 1) % colors.length];
            System.out.println(BRIGHT + lastColor + message + RESET);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
