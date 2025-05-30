package com.ps;

public class Banner {

    public static String create(String message) {
        String border = "*".repeat(message.length() + 4); // creates a line of * longer than the message
        String banner = border + "\n" +
                "* " + message + " *\n" +
                border;
        return banner;
    }

}
