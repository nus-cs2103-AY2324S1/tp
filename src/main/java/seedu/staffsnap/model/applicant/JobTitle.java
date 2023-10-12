package seedu.staffsnap.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.commons.util.AppUtil.checkArgument;

/**
 * Represents a Applicant's jobTitle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobTitle(String)}
 */
public class JobTitle {

    public static final String MESSAGE_CONSTRAINTS = "JobTitles can take any values, and it should not be blank";

    /*
     * The first character of the jobTitle must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code JobTitle}.
     *
     * @param jobTitle A valid jobTitle.
     */
    public JobTitle(String jobTitle) {
        requireNonNull(jobTitle);
        checkArgument(isValidJobTitle(jobTitle), MESSAGE_CONSTRAINTS);
        value = jobTitle;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidJobTitle(String test) {
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
        if (!(other instanceof JobTitle)) {
            return false;
        }

        JobTitle otherJobTitle = (JobTitle) other;
        return value.equals(otherJobTitle.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
