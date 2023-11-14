package seedu.address.model.mapping;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Mapping's miscellaneous information in SEPlendid.
 * Guarantees: immutable; is valid as declared in {@link #isValidMappingMiscInformation(String)}
 */
public class MappingMiscInformation {

    // Used in AppUtil#checkArgument and ParserUtil, JsonAdaptedXXX exceptions
    public static final String MESSAGE_CONSTRAINTS = "Information must start with an alphabet.";

    /*
     * This matches a non-empty string.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String value;

    /**
     * Constructs an {@code MappingMiscInformation}.
     *
     * @param miscInformation A valid miscellaneous information input.
     */
    public MappingMiscInformation(String miscInformation) {
        miscInformation = miscInformation.trim();
        requireNonNull(miscInformation);
        checkArgument(isValidMappingMiscInformation(miscInformation), MESSAGE_CONSTRAINTS);
        value = miscInformation;
    }

    public static boolean isValidMappingMiscInformation(String test) {
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
        if (!(other instanceof MappingMiscInformation)) {
            return false;
        }

        MappingMiscInformation otherMappingMiscInformation = (MappingMiscInformation) other;
        // performs semantic check
        return value.equals(otherMappingMiscInformation.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
