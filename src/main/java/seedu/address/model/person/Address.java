package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ListEntryField;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address extends ListEntryField {
    public static final Address DEFAULT_ADDRESS = new Address();
    public static final String DEFAULT_ADDRESS_MESSAGE = "To be added.";

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) throws ParseException {
        requireNonNull(address);
        if (!Address.isValidAddress(address)) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
        value = address;
    }

    Address() {
        value = DEFAULT_ADDRESS_MESSAGE;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    public static Boolean isValid(String input) {
        return isValidAddress(input);
    }
    public static Address of(String input) throws ParseException {
        return new Address(input);
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

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns a clone of this address that is equal to this address.
     */
    public Address clone() {
        if (this == DEFAULT_ADDRESS) {
            return this;
        } else {
            try {
                return new Address(value);
            } catch (ParseException e) {
                throw new AssertionError("Clone of valid name failed.");
            }
        }
    }

}
