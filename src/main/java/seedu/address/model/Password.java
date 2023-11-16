package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Represents a User's password. The password must meet certain constraints to be considered valid.
 * Guarantees: immutable; meets specified constraints.
 */
public class Password {
    public static final String DEFAULT_PASSWORD = "Password123!";
    public static final String MESSAGE_CONSTRAINTS =
            "Password must be at least 8 characters long and contain at least one digit, one lowercase letter,"
                    + " one uppercase letter, and one special character.\n"
                    + "Default password: " + DEFAULT_PASSWORD;
    public static final String VALIDATION_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String FILENAME = "pword.txt";
    private static final String SALT = "YourFixedSaltHere";
    public final String password;

    /**
     * Constructs a `Password` with a valid password.
     *
     * @param password A valid password that meets the specified constraints.
     */
    public Password(String password) {
        requireNonNull(password);
        checkArgument(isValidPassword(password), MESSAGE_CONSTRAINTS);
        this.password = password;
    }

    /**
     * Returns true if a given string is a valid password.
     *
     * @param test The string to be tested for password validity.
     * @return True if the string is a valid password; false otherwise.
     */
    public static boolean isValidPassword(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Hashes a password.
     *
     * @param password The password to be hashed.
     * @return The hashed password.
     */
    public static String hashPassword(String password) {
        return password.hashCode() + "";
    }

    /**
     * Saves a hashed password to a file.
     *
     * @param hashedPassword The hashed password to be saved.
     * @param filename The name of the file where the password will be saved.
     */
    public static void savePasswordToFile(String hashedPassword, String filename) {
        try {
            Files.write(Path.of(filename), hashedPassword.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a hashed password from a file.
     *
     * @param filename The name of the file from which to read the password.
     * @return The hashed password read from the file.
     */
    public static String readPasswordFromFile(String filename) {
        try {
            byte[] bytes = Files.readAllBytes(Path.of(filename));
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Verifies a password entered by the user.
     *
     * @param enteredPassword The entered password to be verified.
     * @return True if the entered password matches the stored hashed password; false otherwise.
     */
    public static boolean verifyPassword(String enteredPassword) {
        System.out.println(enteredPassword);
        System.out.println(enteredPassword.equals(DEFAULT_PASSWORD));
        String storedHashedPassword = readPasswordFromFile(FILENAME);
        System.out.println(storedHashedPassword);
        if (storedHashedPassword == null) {
            storedHashedPassword = hashPassword(DEFAULT_PASSWORD);
            savePasswordToFile(storedHashedPassword, FILENAME);
        }

        String enteredHashedPassword = hashPassword(enteredPassword);
        System.out.println(enteredHashedPassword);
        System.out.println(storedHashedPassword.equals(enteredHashedPassword));
        return storedHashedPassword.equals(enteredHashedPassword);
    }

    /**
     * Changes the user's password.
     *
     * @param currentPassword The current password.
     * @param newPassword The new password to set.
     * @return A message indicating the result of the password change operation.
     */
    public static String changePassword(String currentPassword, String newPassword) {
        if (verifyPassword(currentPassword)) {
            // Verify that the new password meets the constraints
            if (isValidPassword(newPassword)) {
                if (currentPassword.equals(newPassword)) {
                    return "New password cannot be the same as the current password.";
                }
                // Hash the new password
                String newHashedPassword = hashPassword(newPassword);
                // Save the new hashed password to the file
                savePasswordToFile(newHashedPassword, FILENAME);
                return "Password changed successfully.";
            } else {
                return "New password doesn't meet the constraints.\n" + MESSAGE_CONSTRAINTS;
            }
        } else {
            return "Current password is incorrect.\n" + MESSAGE_CONSTRAINTS;
        }
    }

    /**
     * Converts an array of bytes to its hexadecimal representation.
     *
     * @param bytes The array of bytes to be converted.
     * @return The hexadecimal representation of the byte array.
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Returns the password as a string.
     *
     * @return The password as a string.
     */
    @Override
    public String toString() {
        return password;
    }

    /**
     * Checks if this `Password` is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal; false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Password)) {
            return false;
        }

        Password otherPassword = (Password) other;
        return password.equals(otherPassword.password);
    }

    /**
     * Returns the hash code of this `Password`.
     *
     * @return The hash code of this `Password`.
     */
    @Override
    public int hashCode() {
        return password.hashCode();
    }
}
