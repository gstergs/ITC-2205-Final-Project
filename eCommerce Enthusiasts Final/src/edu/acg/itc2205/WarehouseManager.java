package edu.acg.itc2205;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The WarehouseManager class provides functionality for managing the warehouse inventory.
 */
public class WarehouseManager {
    private InventorySystem inventorySystem;

    /**
     * Constructs a new WarehouseManager with the given inventory system.
     *
     * @param inventorySystem The inventory system to manage.
     */
    public WarehouseManager(InventorySystem inventorySystem) {
        this.inventorySystem = inventorySystem;
    }

    /**
     * Runs the warehouse manager functionality, allowing the user to interact with the warehouse inventory.
     */
    public void runWarehouseManagerFunctionality() {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                System.out.println("\n----- Warehouse Manager Menu -----");
                System.out.println("1. Display Products");
                System.out.println("2. Add Product");
                System.out.println("3. Subtract Quantity");
                System.out.println("4. Save and Exit");
                System.out.print("Enter your choice (1-4): ");

                String input = scanner.nextLine();

                try {
                    choice = Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            inventorySystem.displayProducts();
                            break;
                        case 2:
                            addProduct(scanner);
                            break;
                        case 3:
                            subtractQuantity(scanner);
                            break;
                        case 4:
                            inventorySystem.writeProductsToFile();
                            System.out.println("Exiting Warehouse Manager. Products saved to file.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    choice = 0; // Set to an invalid choice
                }

            } while (choice != 4);

            // Check for more input before attempting to read
            if (scanner.hasNextLine()) {
                scanner.nextLine();  // Consume the newline character
            }
        }
    }

    /**
     * Adds a new product to the warehouse inventory based on user input.
     *
     * @param scanner The Scanner used to gather user input.
     */
    private void addProduct(Scanner scanner) {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        // Add product
        inventorySystem.addProduct(new Product(productId, productName, quantity, price));

        System.out.println("Product added: " + productName);
    }

    /**
     * Subtracts a specified quantity from a product in the warehouse inventory based on user input.
     *
     * @param scanner The Scanner used to gather user input.
     */
    private void subtractQuantity(Scanner scanner) {
        try {
            System.out.print("Enter product ID: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid product ID. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
                return;
            }
            int productId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.print("Enter quantity to subtract: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid quantity. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
                return;
            }
            int quantityToSubtract = scanner.nextInt();

            // Subtract quantity
            boolean success = inventorySystem.subtractQuantity(productId, quantityToSubtract);

            if (success) {
                System.out.println("Quantity subtracted for product ID: " + productId);
            } else {
                System.out.println("Failed to subtract quantity. Product not found or insufficient quantity.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume the invalid input
        }
    }
}
