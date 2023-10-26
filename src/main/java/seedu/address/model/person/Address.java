package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it can be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // Updated: Allow empty address, so whitespace is allowed.
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final Address NULL_ADDRESS;

    static {
        try {
            NULL_ADDRESS = new Address("");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        if (test.trim().equals("")) {
            return true;
        }
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
        if (!(other instanceof Address)) {
            return false;
        }

        Address otherAddress = (Address) other;
        return value.equals(otherAddress.value);
    }

    /**
     * Factory method to create an Address object.
     * @param address
     * @return Address object
     * @throws IllegalArgumentException
     */
    public static Address of(String address) throws IllegalArgumentException {
        if (!isValidAddress(address)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        } else if (address.isBlank()) {
            return Address.NULL_ADDRESS;
        } else {
            return new Address(address);
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
