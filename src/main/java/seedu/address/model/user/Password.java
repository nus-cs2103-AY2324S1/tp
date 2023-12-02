package seedu.address.model.user;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.storage.StorageManager;


/**
 * Represents a User's password in the address book.
 */
public class Password {

    public static final String MESSAGE_CONSTRAINTS =
            "Password should only contain alphanumeric characters, at least 8 characters, and it should not be blank";

    /*
     * The first character of the password must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}]{8,}$";
    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);

    public final String hashedPassword;

    /**
     * Constructs a {@code Password}.
     *
     * @param passwordString A valid password.
     */

    public Password(String passwordString) {
        this.hashedPassword = hashAndValidatePassword(passwordString, true);
    }

    /*
     * Overloaded constructor for creating a new user with a hashed password
     */
    public Password(String passwordString, boolean isHashed) {
        this.hashedPassword = isHashed ? passwordString : hashAndValidatePassword(passwordString, false);
    }

    /**
     * Hashes and validates a password.
     *
     * @param passwordString A valid password.
     * @param shouldValidate Whether the password should be validated.
     */
    private String hashAndValidatePassword(String passwordString, boolean shouldValidate) {
        requireNonNull(passwordString);
        if (shouldValidate) {
            checkArgument(isValidPassword(passwordString), MESSAGE_CONSTRAINTS);
        }
        return hashPassword(passwordString);
    }

    private String hashPassword(String passwordString) {
        // hash password using SHA-256
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            logger.fine("SHA-256 algorithm not found");
            return "";
        }
        byte[] hash = digest.digest(passwordString.getBytes(StandardCharsets.UTF_8));
        // convert byte array to hex string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        // standardise hex string to only capital letters
        hexString = new StringBuilder(hexString.toString().toUpperCase());
        return hexString.toString();
    }

    /**
     * Returns true if a given string is a valid password.
     */
    public static boolean isValidPassword(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return hashedPassword;
    }

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
        return hashedPassword.equals(otherPassword.hashedPassword);
    }

    @Override
    public int hashCode() {
        return hashedPassword.hashCode();
    }
}
