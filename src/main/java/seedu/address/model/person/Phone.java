package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    public static final String MESSAGE_CONSTRAINTS = "Phone numbers should only contain numbers without whitespaces, "
            + "and it should be at least 3 digits long and no longer than 17 digits.";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public static final Phone NULL_PHONE;

    static {
        try {
            NULL_PHONE = new Phone("");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        //to represent the case of optional time.
        if (test.trim().equals("")) {
            return true;
        }

        if (test.length() > 17) {
            return false;
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
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return value.equals(otherPhone.value);
    }

    /**
     * Factory method of Phone class.
     */
    public static Phone of(String phone) throws ParseException {
        if (!isValidPhone(phone)) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
        if (phone.isBlank()) {
            return Phone.NULL_PHONE;
        } else {
            return new Phone(phone);
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
