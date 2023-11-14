package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ListEntryField;
/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone extends ListEntryField {
    public static final Phone DEFAULT_PHONE = new Phone();
    public static final String DEFAULT_PHONE_MESSAGE = "To be added.";


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) throws ParseException {
        requireNonNull(phone);
        if (!Phone.isValidPhone(phone)) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
        value = phone.trim();
    }

    Phone() {
        value = DEFAULT_PHONE_MESSAGE;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    public static Boolean isValid(String input) {
        return isValidPhone(input);
    }
    public static Phone of(String input) throws ParseException {
        return new Phone(input);
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
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns a clone of this phone that is equal to this phone.
     */
    public Phone clone() {
        if (this == DEFAULT_PHONE) {
            return this;
        } else {
            try {
                return new Phone(value);
            } catch (ParseException e) {
                throw new AssertionError("This should not happen.");
            }
        }
    }

}
