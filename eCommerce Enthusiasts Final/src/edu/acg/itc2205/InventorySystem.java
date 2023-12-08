package edu.acg.itc2205;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The InventorySystem class manages the inventory of products.
 */
public class InventorySystem {
    private List<Product> products;
    private static final String PRODUCT_FILE_PATH = "Products.txt";

    /**
     * Constructs an InventorySystem and reads products from a file.
     */
    public InventorySystem() {
        products = new ArrayList<>();
        readProductsFromFile();  // Automatically read products when the system is created
    }

    /**
     * Retrieves a product based on its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The product with the specified ID, or null if not found.
     */
    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Updates the quantity of a product in the inventory.
     *
     * @param productId   The ID of the product to update.
     * @param newQuantity The new quantity of the product.
     * @return True if the quantity is successfully updated, false otherwise.
     */
    public boolean updateProductQuantity(int productId, int newQuantity) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                product.setQuantity(newQuantity);
                return true;  // Successfully updated quantity
            }
        }
        return false;  // Product not found
    }

    /**
     * Reads products from a text file and adds them to the inventory.
     */
    private void readProductsFromFile() {
        String fileName = "Products.txt";
        createFileIfNotExists();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                int quantity = Integer.parseInt(parts[2].trim());
                double price = Double.parseDouble(parts[3].trim());

                products.add(new Product(id, name, quantity, price));
            }
        } catch (IOException e) {
            System.err.println("Error reading products from file: " + e.getMessage());
        }
    }

    /**
     * Writes the current inventory of products to a text file.
     */
    public void writeProductsToFile() {
        String fileName = "Products.txt";
        createFileIfNotExists();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products) {
                bw.write(product.getProductId() + ", " + product.getProductName() + ", "
                        + product.getQuantity() + ", " + product.getPrice());
                bw.newLine();
            }
            System.out.println("Products written to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing products to file: " + e.getMessage());
        }
    }

    /**
     * Creates the product file if it does not exist.
     */
    private static void createFileIfNotExists() {
        String fileName = "Products.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                System.out.println("File created: " + fileName + " - " + (created ? "success" : "failed"));
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Displays all products in the inventory.
     */
    public void displayProducts() {
        System.out.println("----- Available Products -----");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * Adds a new product to the inventory.
     *
     * @param product The product to add.
     */
    public void addProduct(Product product) {
        if (!productExists(product.getProductId())) {
            products.add(product);
        } else {
            System.out.println("Product with ID " + product.getProductId() + " already exists.");
        }
    }

    private boolean productExists(int productId) {
        return products.stream().anyMatch(p -> p.getProductId() == productId);
    }

    /**
     * Subtracts a specified quantity from the available stock of a product.
     *
     * @param productId         The ID of the product.
     * @param quantityToSubtract The quantity to subtract.
     * @return True if the quantity is successfully subtracted, false otherwise.
     */
    public boolean subtractQuantity(int productId, int quantityToSubtract) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                int currentQuantity = product.getQuantity();
                if (currentQuantity >= quantityToSubtract) {
                    product.setQuantity(currentQuantity - quantityToSubtract);
                    return true; // Successfully subtracted quantity
                } else {
                    // Insufficient quantity to subtract
                    System.out.println("Insufficient quantity. Please enter a valid quantity.");
                    return false;
                }
            }
        }
        // Product not found
        System.out.println("Product not found. Please enter a valid product ID.");
        return false;
    }
}
