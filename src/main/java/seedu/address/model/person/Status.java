package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's status
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
*/
public class Status {
    /**
     * Represents the available statuses that can be assigned to a Person.
     */
    public static enum statusList {
        NIL,
        Prospective,
        Active,
        Inactive,
        Claimant,
        Renewal,
    };

    public static final String DEFAULT_STATUS = "NIL";

    public static final String MESSAGE_CONSTRAINTS =
            "Status should be 1 of { Prospective, Active, Inactive, Claimant, Renewal }, and it should not be blank";

    public final String value;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.value = (status == "") ? DEFAULT_STATUS : status;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String input) {
        if (input == "") {
            return true;
        }
        for (statusList enumValue : statusList.class.getEnumConstants()) {
            if (enumValue.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Status)) {
            return false;
        }

        Status otherStatus = (Status) other;
        return value.equals(otherStatus.value);
    }

    @Override
    public String toString() {
        return value;
    }

}
