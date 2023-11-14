package seedu.address.model.partnercourse;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.messages.ConstraintMessage;

/**
 * Represents a Partner Course's partner name in SEPlendid.
 * Guarantees: immutable; is valid as declared in {@link #isValidPartnerName(String)}
 */
public class PartnerName {
    public static final String VALIDATION_REGEX = "[^\\s].{0,75}";

    private final String value;

    /**
     * Constructs an {@code PartnerName}.
     * partnerName is trimmed before checkArgument, as a standardisation.
     *
     * @param partnerName A valid partnername.
     */
    public PartnerName(String partnerName) {
        partnerName = partnerName.trim();
        requireNonNull(partnerName);
        checkArgument(isValidPartnerName(partnerName), ConstraintMessage.PARTNERCOURSE_NAME.toString());
        value = partnerName;
    }

    public static boolean isValidPartnerName(String test) {
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

        if (!(other instanceof PartnerName)) {
            return false;
        }

        PartnerName otherPartnerName = (PartnerName) other;

        return value.equals(otherPartnerName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
