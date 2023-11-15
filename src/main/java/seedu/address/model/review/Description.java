package seedu.address.model.review;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Review's description in the review list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS =
            "Description should not be blank";

    public static final String VALIDATION_REGEX = "(.|\\s)*\\S(.|\\s)*";

    public final String description;

    /**
     * Constructs a {@code Description}.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && description.equals(((Description) other).description)); // state check
    }
}
