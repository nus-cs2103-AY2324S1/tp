package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Schedule's tutor index in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTutorIndex(String)}
 */
public class TutorIndex {
    public static final String MESSAGE_CONSTRAINTS =
            "TutorIndex should only contain a positive integer, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[1-9]\\d*$";
    private final Integer index;

    /**
     * Constructs a {@code TutorIndex}.
     *
     * @param index A valid index.
     */
    public TutorIndex(Integer index) {
        requireNonNull(index);
        checkArgument(isPositive(index), MESSAGE_CONSTRAINTS);
        this.index = index;
    }

    public static boolean isValidTutorIndex(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private static boolean isPositive(Integer i) {
        return i > 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorIndex)) {
            return false;
        }

        TutorIndex otherIndex = (TutorIndex) other;
        return index.equals(otherIndex.index);
    }

    @Override
    public int hashCode() {
        return index.hashCode();
    }
}
