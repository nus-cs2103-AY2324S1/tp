package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's medical history in the address book.
 */
public class MedicalHistory {
    public static final String MESSAGE_CONSTRAINTS =
            "Medical History should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the Medical History must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param medicalHistoryInput A valid medical history.
     */
    public MedicalHistory(String medicalHistoryInput) {
        requireNonNull(medicalHistoryInput);
        checkArgument(isValidMedicalHistory(medicalHistoryInput), MESSAGE_CONSTRAINTS);
        this.value = medicalHistoryInput;
    }

    /**
     * Returns true if a given string is a valid medical history.
     */
    public static boolean isValidMedicalHistory(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof MedicalHistory)) {
            return false;
        }

        MedicalHistory otherMedicalHistory = (MedicalHistory) other;
        return value.equals(otherMedicalHistory.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public MedicalHistory getCopy() {
        return new MedicalHistory(this.value);
    }
}
