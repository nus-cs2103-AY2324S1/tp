package seedu.address.model.university;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.messages.ConstraintMessage;

/**
 * Represents a University's local name in SEPlendid.
 * Guarantees: immutable; is valid as declared in {@link #isValidUniversityName(String)}
 */
public class UniversityName {
    public static final String VALIDATION_REGEX = "[^\\s].{0,50}";

    private final String value;

    /**
     * Constructs an {@code UniversityName}.
     * localName is trimmed before checkArgument, as a standardisation.
     *
     * @param universityname A valid universityname.
     */
    public UniversityName(String universityname) {
        universityname = universityname.trim();
        requireNonNull(universityname);
        checkArgument(isValidUniversityName(universityname), ConstraintMessage.UNIVERSITY_NAME.toString());
        value = universityname;
    }

    public String getValue() {
        return value;
    }

    public static boolean isValidUniversityName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getName() {
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

        if (!(other instanceof UniversityName)) {
            return false;
        }

        UniversityName otherUniversityName = (UniversityName) other;
        return value.equalsIgnoreCase(otherUniversityName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
