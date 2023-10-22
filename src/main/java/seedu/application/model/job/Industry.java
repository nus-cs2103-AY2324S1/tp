package seedu.application.model.job;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.util.AppUtil;

/**
 * Represents the industry of the Job in the application book.
 */

public class Industry {
    public static final String MESSAGE_CONSTRAINTS =
            "Industry descriptions should adhere to the following constraints:\n"
                    + "1. Only contain alphanumeric characters and spaces\n";

    public static final String TO_ADD_INDUSTRY = "TO_ADD_INDUSTRY";
    public static final Industry DEFAULT_INDUSTRY = new Industry(TO_ADD_INDUSTRY);

    /*
     * The first character of the role must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}].*";

    public final String industry;

    /**
     * Constructs a {@code Status}.
     *
     * @param industry A valid industry.
     */
    public Industry(String industry) {
        requireNonNull(industry);
        AppUtil.checkArgument(isValidIndustry(industry), MESSAGE_CONSTRAINTS);
        this.industry = industry.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid industry.
     */
    public static boolean isValidIndustry(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return industry;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Industry)) {
            return false;
        }

        Industry otherIndustry = (Industry) other;
        return industry.equals(otherIndustry.industry);
    }

    @Override
    public int hashCode() {
        return industry.hashCode();
    }
}
