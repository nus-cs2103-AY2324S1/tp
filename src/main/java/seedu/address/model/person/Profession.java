package seedu.address.model.person;

/**
 * Represents a Person's profession in the address book.
 */
public class Profession {

    public static final String MESSAGE_CONSTRAINTS = "Professions should only contain alphanumeric characters "
            + "and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
    * Constructs a {@code Profession}.
    *
    * @param profession A valid profession.
    */
    public Profession(String profession) {
        value = profession;
    }

    /**
    * Returns true if a given string is a valid profession.
    */
    public static boolean isValidProfession(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }
}
