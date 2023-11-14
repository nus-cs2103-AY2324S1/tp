package seedu.address.model.partnercourse;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.messages.ConstraintMessage;

/**
 * Represents a Partner Course's partner code in SEPlendid.
 * Guarantees: immutable; is valid as declared in {@link #isValidPartnerCode(String)}
 */
public class PartnerCode {
    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z0-9]{0,9}";

    private final String value;

    /**
     * Constructs an {@code partnerCode}.
     *
     * @param partnerCode A valid localCode.
     */
    public PartnerCode(String partnerCode) {
        partnerCode = partnerCode.trim();
        requireNonNull(partnerCode);
        checkArgument(isValidPartnerCode(partnerCode), ConstraintMessage.PARTNERCOURSE_CODE.toString());
        value = partnerCode;
    }

    public static boolean isValidPartnerCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return value;
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

        if (!(other instanceof PartnerCode)) {
            return false;
        }

        PartnerCode otherPartnerCode = (PartnerCode) other;
        return value.equalsIgnoreCase(otherPartnerCode.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
