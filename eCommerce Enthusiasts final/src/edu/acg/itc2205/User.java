package edu.acg.itc2205;

/**
 * The User class represents a user with associated information.
 */
public class User {
    private String username;
    private String password;
    private String userClass;
    private String name;
    private String surname;
    private String contactInfo;
    private String email;

    /**
     * Constructs a new User with the given properties.
     *
     * @param username    The unique username of the user.
     * @param password    The password associated with the user.
     * @param userClass   The user class indicating the role of the user.
     * @param name        The name of the user.
     * @param surname     The surname of the user.
     * @param contactInfo The contact information of the user.
     * @param email       The email address of the user.
     */
    public User(String username, String password, String userClass, String name, String surname, String contactInfo, String email) {
        this.username = username;
        this.password = password;
        this.userClass = userClass;
        this.name = name;
        this.surname = surname;
        this.contactInfo = contactInfo;
        this.email = email;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the user class indicating the role of the user.
     *
     * @return The user class.
     */
    public String getUserClass() {
        return userClass;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The new name of the user.
     */
    public void setName(String name) {
        if (isValidString(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name. Please provide a valid name.");
        }
    }

    /**
     * Retrieves the surname of the user.
     *
     * @return The user's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the user.
     *
     * @param surname The new surname of the user.
     */
    public void setSurname(String surname) {
        if (isValidString(surname)) {
            this.surname = surname;
        } else {
            throw new IllegalArgumentException("Invalid surname. Please provide a valid surname.");
        }
    }

    /**
     * Retrieves the contact information of the user.
     *
     * @return The user's contact information.
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the contact information of the user.
     *
     * @param contactInfo The new contact information of the user.
     */
    public void setContactInfo(String contactInfo) {
        if (isValidString(contactInfo)) {
            this.contactInfo = contactInfo;
        } else {
            throw new IllegalArgumentException("Invalid contact information. Please provide valid contact information.");
        }
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The new email address of the user.
     */
    public void setEmail(String email) {
        if (isValidString(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email. Please provide a valid email address.");
        }
    }

    /**
     * Checks if a string is valid (not null or empty).
     *
     * @param value The string to validate.
     * @return True if the string is valid, false otherwise.
     */
    private boolean isValidString(String value) {
        return value != null && !value.isEmpty();
    }
}
