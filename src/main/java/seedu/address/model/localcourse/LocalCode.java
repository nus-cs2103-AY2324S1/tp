package seedu.address.model.localcourse;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.messages.ConstraintMessage;

/**
 * Represents a Local Course's local code in SEPlendid.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocalCode(String)}
 */
public class LocalCode {
    /*
     * This matches alphanumeric string of length 1-10, starting with an alphabet.
     */
    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z0-9]{0,9}";

    private final String value;

    /**
     * Constructs an {@code LocalCode}.
     *
     * @param localCode A valid localCode.
     */
    public LocalCode(String localCode) {
        localCode = localCode.trim();
        requireNonNull(localCode);
        checkArgument(isValidLocalCode(localCode), ConstraintMessage.LOCALCOURSE_CODE.toString());
        value = localCode;
    }

    public static boolean isValidLocalCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return this.value;
    }

    // Note: be careful not to modify this, as it is used in auto-invocation of toString
    // in places where string is expected
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
        if (!(other instanceof LocalCode)) {
            return false;
        }

        LocalCode otherLocalCode = (LocalCode) other;
        // performs semantic check
        return value.equalsIgnoreCase(otherLocalCode.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
