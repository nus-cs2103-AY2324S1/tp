package seedu.address.model.person;

/**
 * Represents a Person's details in the address book.
 */
public class Details {

    public static final String MESSAGE_CONSTRAINTS = "Details should only contain alphanumeric characters "
            + "and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
    * Constructs a {@code Details}.
    *
    * @param details A valid details.
    */
    public Details(String details) {
        value = details;
    }

    /**
    * Returns true if a given string is a valid details.
    */
    public static boolean isValidDetails(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }
}
