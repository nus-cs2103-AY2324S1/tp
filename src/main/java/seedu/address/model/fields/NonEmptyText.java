package seedu.address.model.fields;

import static java.util.Objects.requireNonNull;

/**
 * Represents any non empty text
 */
public class NonEmptyText {
    public static final String MESSAGE_CONSTRAINTS = "Empty string not allowed";

    // Matches everything except empty string
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    private final String text;

    /**
     * Constructs a {@code Name}.
     *
     * @param text A valid text.
     */
    public NonEmptyText(String text) {
        requireNonNull(text);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    /**
     * @param taskName be verified
     * @return true when not an empty string, false otherwise
     */
    public static boolean isValidText(String taskName) {
        return taskName.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NonEmptyText)) {
            return false;
        }

        NonEmptyText otherName = (NonEmptyText) other;
        return text.equals(otherName.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
