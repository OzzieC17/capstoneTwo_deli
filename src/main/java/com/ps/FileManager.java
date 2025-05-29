package com.ps;

import com.ps.core.Order;
import com.ps.core.Product;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FileManager {
    public void saveReceipt(Order currentOrder) {
        try {
            String folderName = "receipts";
            File folder = new File(folderName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String timestamp = currentOrder.getTimestamp().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            File receiptFile = new File(folder, timestamp + ".txt");

            try (FileWriter writer = new FileWriter(receiptFile)) {
                writer.write("=== Carreons Deli Receipt ===\n\n");
                for (Product product : currentOrder.getProducts()) {
                    writer.write(product.getName() + " -$" + product.calcPrice() + "\n");

                }
                writer.write("\nTotal: $" + currentOrder.getTotal() + "\n");
                writer.write("Thank you for shopping!");
            }
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }

    }
}
