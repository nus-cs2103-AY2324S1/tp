package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

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
        requireNonNull(profession);
        checkArgument(isValidProfession(profession), MESSAGE_CONSTRAINTS);
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

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof Profession
            && value.equals(((Profession) other).value));
    }
}
