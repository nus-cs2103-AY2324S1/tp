package seedu.application.model.job;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.util.AppUtil;

/**
 * Represents a Job's role in the application book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {

    public static final String ROLE_FIND_SPECIFIER = "-r";
    public static final String MESSAGE_CONSTRAINTS =
            "Role descriptions should adhere to the following constraints:\n"
                    + "1. Only contain alphanumeric characters and spaces\n"
                    + "2. Should not be blank";

    /*
     * The first character of the role must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String description;

    /**
     * Constructs a {@code Role}.
     *
     * @param description A valid description.
     */
    public Role(String description) {
        requireNonNull(description);
        AppUtil.checkArgument(isValidRole(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Role)) {
            return false;
        }

        Role otherRole = (Role) other;
        return description.equals(otherRole.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

}
