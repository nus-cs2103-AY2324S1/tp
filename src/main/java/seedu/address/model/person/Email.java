package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain at most 50 alphanumeric characters and these special characters, "
            + "excluding the parentheses, (" + SPECIAL_CHARACTERS + "). The local-part may not start or end with any "
            + "special characters.\n"
            + "2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels "
            + "separated by periods.\n"
            + "The domain name must: end with a domain label that is supported: gmail, yahoo, outlook, hotmail, icloud";

    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$"; // At least two chars
    private static final String DOMAIN_REGEX = "(" + DOMAIN_PART_REGEX + "\\.)*" + DOMAIN_LAST_PART_REGEX;
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    // Set of popular email domains
    private static final Set<String> VALID_EMAIL_DOMAINS = new HashSet<>(Set.of(
            "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "icloud.com"
            // Add more domains as needed
    ));

    // Maximum length for the email address
    private static final int MAX_EMAIL_LENGTH = 50; // Set as desired maximum length

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        this.value = email;
    }

    /**
     * Validates if a given string is a valid email.
     *
     * @param test The string to test for validity.
     * @return True if the string matches the expected email format and domain, false otherwise.
     */
    public static boolean isValidEmail(String test) {
        // Extract domain and check if it's in the set of valid domains
        String[] parts = test.split("@");
        if (parts.length != 2) {
            return false;
        }
        if (parts[0].length() > MAX_EMAIL_LENGTH) {
            return false;
        }
        String domain = parts[1];

        return test.matches(VALIDATION_REGEX) && VALID_EMAIL_DOMAINS.contains(domain);
    }

    /**
     * Returns a string representation of this email address.
     *
     * @return A string representation of this email address.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Indicates whether some other object is "equal to" this email address.
     *
     * @param other The reference object with which to compare.
     * @return True if the other object is equal to this email address, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Email)) {
            return false;
        }

        Email otherEmail = (Email) other;
        return value.equals(otherEmail.value);
    }

    /**
     * Returns a hash code value for this email address.
     *
     * @return A hash code value for this email address.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Truncates the email address to a maximum of 15 characters for the local-part,
     * appending an ellipsis (...) for portions exceeding this limit.
     *
     * @return The truncated email address adhering to the 15-character limit for the local-part.
     */
    public String truncatedEmail() {
        String[] parts = value.split("@");
        if (parts[0].length() > 15) {
            return parts[0].substring(0, 12) + "..." + "@" + parts[1];
        }
        return parts[0] + "@" + parts[1];
    }

}
