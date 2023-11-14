package seedu.address.model.localcourse;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.messages.ConstraintMessage;

/**
 * Represents a Local Course's local name in SEPlendid.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocalName(String)}
 */
public class LocalName {
    /*
     * This matches a string that starts with a non-whitespace character.
     */
    public static final String VALIDATION_REGEX = "[^\\s].{0,75}";

    private final String value;

    /**
     * Constructs an {@code LocalName}.
     * localName is trimmed before checkArgument, as a standardisation.
     *
     * @param localName A valid localname.
     */
    public LocalName(String localName) {
        localName = localName.trim();
        requireNonNull(localName);
        checkArgument(isValidLocalName(localName), ConstraintMessage.LOCALCOURSE_NAME.toString());
        value = localName;
    }

    public static boolean isValidLocalName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return this.value;
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
        if (!(other instanceof LocalName)) {
            return false;
        }

        LocalName otherLocalName = (LocalName) other;
        // performs semantic check
        return value.equals(otherLocalName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
