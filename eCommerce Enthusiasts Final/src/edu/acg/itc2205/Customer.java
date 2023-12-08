package edu.acg.itc2205;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Customer class represents a customer user with functionality
 * to view products, add to the cart, view the cart, remove from the cart,
 * and update their profile information.
 */
public class Customer {
    private User user; // The customer user
    private List<Product> cart; // The customer's shopping cart
    private InventorySystem inventorySystem; // The inventory system containing available products

    /**
     * Constructs a Customer object with the specified user and inventory system.
     *
     * @param user             The customer user.
     * @param inventorySystem The inventory system.
     */
    public Customer(User user, InventorySystem inventorySystem) {
        this.user = user;
        this.cart = new ArrayList<>();
        this.inventorySystem = inventorySystem;
    }

    /**
     * Runs the customer functionality menu, allowing the customer to interact
     * with available products, manage their shopping cart, and update their profile.
     */
    public void runCustomerFunctionality() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n----- Customer Menu -----");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. Update Profile");
            System.out.println("6. Logout");
            System.out.print("Enter your choice (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addToCart(scanner);
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    removeFromCart(scanner);
                    break;
                case 5:
                    updateProfile(scanner);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    /**
     * Displays the available products in the inventory system.
     */
    private void viewProducts() {
        inventorySystem.displayProducts();
    }

    /**
     * Adds a selected product to the customer's shopping cart.
     *
     * @param scanner The scanner for user input.
     */
    private void addToCart(Scanner scanner) {
        System.out.print("Enter product ID to add to cart: ");

        // Validate if the input is an integer
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid product ID. Please enter a valid integer.");
            scanner.nextLine();  // Consume the invalid input
            return;
        }

        int productId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        Product product = inventorySystem.getProductById(productId);
        if (product != null) {
            System.out.print("Enter quantity: ");

            // Validate if the input is an integer
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid quantity. Please enter a valid integer.");
                scanner.nextLine();  // Consume the invalid input
                return;
            }

            int quantity = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            if (quantity > 0 && quantity <= product.getQuantity()) {
                Product cartProduct = new Product(product.getProductId(), product.getProductName(), quantity, product.getPrice());
                cart.add(cartProduct);
                System.out.println("Product added to cart: " + cartProduct);
                // Update the inventory system to subtract the quantity from the available stock
                inventorySystem.subtractQuantity(productId, quantity);
            } else {
                System.out.println("Invalid quantity. Please enter a valid quantity.");
            }
        } else {
            System.out.println("Product not found. Please enter a valid product ID.");
        }
    }

    /**
     * Displays the contents of the customer's shopping cart.
     */
    private void viewCart() {
        System.out.println("\n----- Your Cart -----");
        for (Product product : cart) {
            System.out.println(product);
        }
    }

    /**
     * Removes a selected product from the customer's shopping cart.
     *
     * @param scanner The scanner for user input.
     */
    private void removeFromCart(Scanner scanner) {
        System.out.print("Enter product ID to remove from cart: ");

        // Validate if the input is an integer
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid product ID. Please enter a valid integer.");
            scanner.nextLine();  // Consume the invalid input
            return;
        }

        int productId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        Product cartProduct = null;
        for (Product product : cart) {
            if (product.getProductId() == productId) {
                cartProduct = product;
                break;
            }
        }

        if (cartProduct != null) {
            cart.remove(cartProduct);
            System.out.println("Product removed from cart: " + cartProduct);
            // Update the inventory system to add the quantity back to the available stock
            inventorySystem.updateProductQuantity(productId, cartProduct.getQuantity() + inventorySystem.getProductById(productId).getQuantity());
        } else {
            System.out.println("Product not found in cart. Please enter a valid product ID.");
        }
    }

    /**
     * Updates the customer's profile information.
     *
     * @param scanner The scanner for user input.
     */
    private void updateProfile(Scanner scanner) {
        System.out.println("----- Update Profile -----");
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new surname: ");
        String newSurname = scanner.nextLine();
        System.out.print("Enter new contact info: ");
        String newContactInfo = scanner.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();

        // Update the user's profile information
        if (updateUser(newName, newSurname, newContactInfo, newEmail)) {
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Failed to update profile. Please try again.");
        }
    }

    /**
     * Updates the user's profile information.
     *
     * @param newName         The new name.
     * @param newSurname      The new surname.
     * @param newContactInfo  The new contact information.
     * @param newEmail        The new email address.
     * @return True if the update is successful, false otherwise.
     */
    private boolean updateUser(String newName, String newSurname, String newContactInfo, String newEmail) {
        // Validate the new profile information (Add additional validation if needed)
        if (newName.isEmpty() || newSurname.isEmpty() || newContactInfo.isEmpty() || !newEmail.contains("@")) {
            System.out.println("Invalid input. Please provide all required information.");
            return false;
        }

        // Update the user's profile information
        user.setName(newName);
        user.setSurname(newSurname);
        user.setContactInfo(newContactInfo);
        user.setEmail(newEmail);

        return true;
    }
}
