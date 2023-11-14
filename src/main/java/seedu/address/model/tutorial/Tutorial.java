package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's tutorial in StudentConnect.
 * Guarantees: immutable; is valid as declared in {@link #isValidTutorial(String)}
 */
public class Tutorial {

    public static final String MESSAGE_CONSTRAINTS = "Tutorials should be 2-digit numbers between 01 and 22.";

    public static final String VALIDATION_REGEX = "^(0[1-9]|1\\d|2[0-2])$";

    public final String value;

    /**
     * Constructs a {@code Tutorial} with the specified tutorial value.
     *
     * @param tutorial The tutorial value. Must not be null.
     */
    public Tutorial(String tutorial) {
        requireNonNull(tutorial);
        checkArgument(isValidTutorial(tutorial), MESSAGE_CONSTRAINTS);
        value = tutorial;
    }

    /**
     * Returns true if a given string is a valid tutorial number between 01 and 22.
     */
    public static boolean isValidTutorial(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "T" + value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Tutorial // instanceof handles nulls
            && value.equals(((Tutorial) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
