package seedu.address.model.partnercourse;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.messages.ConstraintMessage;

/**
 * Represents a Partner Course's partner description in SEPlendid.
 * Guarantees: immutable; is valid as declared in {@link #isValidPartnerDescription(String)}
 */
public class PartnerDescription {
    /*
     * This matches a string that starts with a non-whitespace character.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String value;

    /**
     * Constructs a {@code PartnerDescription}.
     * partnerDescription is trimmed before checkArgument, as a standardization.
     *
     * @param partnerDescription A valid partner description.
     */
    public PartnerDescription(String partnerDescription) {
        partnerDescription = partnerDescription.trim();
        requireNonNull(partnerDescription);
        checkArgument(isValidPartnerDescription(partnerDescription),
                      ConstraintMessage.PARTNERCOURSE_DESCRIPTION.toString());
        value = partnerDescription;
    }

    public static boolean isValidPartnerDescription(String test) {
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
        if (!(other instanceof PartnerDescription)) {
            return false;
        }

        PartnerDescription otherPartnerDescription = (PartnerDescription) other;
        // performs semantic check
        return value.equals(otherPartnerDescription.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
