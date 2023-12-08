package edu.acg.itc2205;

import java.io.Serializable;

/**
 * The Product class represents a product with its properties.
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private int productId;
    private String productName;
    private int quantity;
    private double price;

    /**
     * Constructs a new Product with the given properties.
     *
     * @param productId   The unique identifier of the product.
     * @param productName The name of the product.
     * @param quantity    The available quantity of the product.
     * @param price       The price of the product.
     */
    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Retrieves the unique identifier of the product.
     *
     * @return The product's ID.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the unique identifier of the product.
     *
     * @param productId The new product ID.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The product's name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the name of the product.
     *
     * @param productName The new product name.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Retrieves the available quantity of the product.
     *
     * @return The product's quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the available quantity of the product.
     *
     * @param quantity The new quantity of the product.
     */
    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Invalid quantity. Please provide a non-negative value.");
        }
    }


    /**
     * Retrieves the price of the product.
     *
     * @return The product's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The new price of the product.
     */
    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("Invalid price. Please provide a positive value.");
        }
    }

    /**
     * Calculates the total cost of the product based on its quantity and price.
     *
     * @return The total cost of the product.
     */
    public double totalCost() {
        return quantity * price;
    }

    /**
     * Returns a string representation of the product.
     *
     * @return A string containing the product's details.
     */
    @Override
    public String toString() {
        return String.format("Product [ID: %d, Name: %s, Quantity: %d, Price: %.2f]", productId, productName, quantity, price);
    }
}
