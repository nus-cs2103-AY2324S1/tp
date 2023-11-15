package seedu.cc.model.medicalhistory;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.AppUtil.checkArgument;

/**
 * Represents a patient's medical condition.
 */
public class MedicalCondition {
    public static final String MESSAGE_CONSTRAINTS =
            "Medical condition should only contain alphanumeric characters and spaces, and it should not be blank";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String value;

    /**
     * Constructs a {@code MedicalCondition}.
     *
     * @param medicalCondition A valid medical condition.
     */
    public MedicalCondition(String medicalCondition) {
        requireNonNull(medicalCondition);
        checkArgument(isValidMedicalCondition(medicalCondition), MESSAGE_CONSTRAINTS);
        this.value = medicalCondition;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidMedicalCondition(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getMedicalCondition() {
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

        // instanceof handles nulls
        if (!(other instanceof MedicalCondition)) {
            return false;
        }

        MedicalCondition otherMedicalCondition = (MedicalCondition) other;
        return value.equals(otherMedicalCondition.value);
    }
}
