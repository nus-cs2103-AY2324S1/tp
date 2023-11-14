package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class PropAddress {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values except slashes,"
            + "and it should not be blank.";

    /*
     * The first character of the property name must not be a whitespace or slash,
     * otherwise " " (a blank string) or "/" becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s/][^/]*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public PropAddress(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.property.PropAddress)) {
            return false;
        }

        seedu.address.model.property.PropAddress otherAddress = (seedu.address.model.property.PropAddress) other;
        return value.equals(otherAddress.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
