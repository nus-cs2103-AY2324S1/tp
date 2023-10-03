package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Employee's id in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS =
            "Employee IDs should start with the prefix EID followed by"
            + "4 digits separated by a hyphen followed by 4 more digits,"
            + "\n and it should not be blank";

    /*
     * Should follow the format EIDXXXX-XXXX
     */
    public static final String VALIDATION_REGEX = "^EID\\d{4}-\\d{4}$";

    public final String id;

    /**
     * Constructs an employee {@code id}.
     *
     * @param id A valid id.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Id)) {
            return false;
        }

        Id otherId = (Id) other;
        return id.equals(otherId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
