package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's identity code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCode(String)}
 */
public class IdentityCode {

    public static final String MESSAGE_CONSTRAINTS =
            "Identity codes should only contain numeric characters, and it should not be blank";

    public static final String VALIDATION_REGEX = "\\d+";

    private final String code;
    /**
     * Constructs a valid IdentityCode.
     *
     * @param code The identity code string to be validated and set.
     */
    public IdentityCode(String code) {
        requireNonNull(code);
        checkArgument(isValidCode(code), MESSAGE_CONSTRAINTS);
        this.code = code;
    }
    /**
     * Returns the numeric value of the identity code.
     *
     * @return An integer representation of the identity code.
     */
    public int getValue() {
        return Integer.parseInt(code);
    }
    /**
     * Validates the given string representation of an identity code.
     *
     * @param test The string to test for validity.
     * @return True if the given string is a valid identity code, false otherwise.
     */
    public static boolean isValidCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof IdentityCode)) {
            return false;
        }

        IdentityCode otherCode = (IdentityCode) other;
        return code.equals(otherCode.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
