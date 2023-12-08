package edu.acg.itc2205;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserAuthentication implements AutoCloseable {
    private List<User> users;
    private static final String USER_FILE = "users.txt";

    public UserAuthentication() {
        users = new ArrayList<>();
        loadUsersFromFile();
    }


    @Override
    public void close() throws IOException {
        // Save the users to file before closing
        saveUsersToFile();

        // Close any other resources, such as file handles
        System.out.println("Closing resources...");
    }

    public void registerUser(String username, String password, String userClass, String name, String surname, String contactInfo, String email) {
        // Check if the username already exists
        if (getUserByUsername(username) != null) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        // Create a new user and add it to the list
        User newUser = new User(username, password, userClass, name, surname, contactInfo, email);
        users.add(newUser);
        saveUsersToFile();  // Save the updated user list to the file
        System.out.println("User registered successfully. Welcome, " + name + "!");
    }

    public boolean loginUser(String username, String password) {
        // Check if the username exists
        User user = getUserByUsername(username);
        if (user == null) {
            System.out.println("Username not found. Please register first.");
            return false;
        }

        // Check if the password matches
        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password. Please try again.");
            return false;
        }

        // Retrieve the user_class_indicator
        String userClass = user.getUserClass();
        System.out.println("Login successful! Welcome, " + user.getName() + "! Your user class is: " + userClass);

        // Redirect to the appropriate user class functionality based on userClass value
        redirectUser(userClass, user);

        return true;
    }

    private void redirectUser(String userClass, User user) {
        switch (userClass) {
            case "Customer":
                InventorySystem inventorySystem = new InventorySystem();
                Customer customer = new Customer(user, inventorySystem);
                customer.runCustomerFunctionality();
                break;
            case "WarehouseManager":
                WarehouseManager warehouseManager = new WarehouseManager(new InventorySystem());
                warehouseManager.runWarehouseManagerFunctionality();
                break;
            default:
                System.out.println("Invalid user class.");
        }
    }



    private User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private void saveUsersToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                writer.println(user.getUsername() + "," + user.getPassword() + "," + user.getUserClass() +
                        "," + user.getName() + "," + user.getSurname() + "," + user.getContactInfo() + "," + user.getEmail());
            }
        } catch (IOException e) {
            System.err.println("Error saving user data to file: " + e.getMessage());
        }
    }

    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length == 7) {
                    String username = userInfo[0];
                    String password = userInfo[1];
                    String userClass = userInfo[2];
                    String name = userInfo[3];
                    String surname = userInfo[4];
                    String contactInfo = userInfo[5];
                    String email = userInfo[6];

                    User user = new User(username, password, userClass, name, surname, contactInfo, email);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading user data from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (UserAuthentication userAuth = new UserAuthentication(); Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                // Check if there's an int available
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character

                    switch (choice) {
                        case 1:
                            // Registration process
                            System.out.print("Enter username: ");
                            String username = scanner.nextLine();
                            System.out.print("Enter password: ");
                            String password = scanner.nextLine();
                            System.out.print("Enter user class: ");
                            String userClass = scanner.nextLine();
                            System.out.print("Enter name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter surname: ");
                            String surname = scanner.nextLine();
                            System.out.print("Enter contact info: ");
                            String contactInfo = scanner.nextLine();
                            System.out.print("Enter email: ");
                            String email = scanner.nextLine();

                            userAuth.registerUser(username, password, userClass, name, surname, contactInfo, email);
                            break;
                        case 2:
                            // Login process
                            System.out.print("Enter username: ");
                            String loginUsername = scanner.nextLine();
                            System.out.print("Enter password: ");
                            String loginPassword = scanner.nextLine();
                            userAuth.loginUser(loginUsername, loginPassword);
                            break;
                        case 3:
                            System.out.println("Goodbye! Thank you for choosing our shop.");
                            // scanner.close();
                            System.exit(0);  // Terminate the program
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();  // Consume the invalid input
                }
            }
        } catch (IOException e) {
            System.out.println("Error in user authentication: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
