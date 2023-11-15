package seedu.cc.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.AppUtil.checkArgument;

/**
 * Represents a patient's prescription.
 */
public class Prescription {

    public static final String MESSAGE_CONSTRAINTS = "Prescriptions should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public final String value;

    /**
     * Constructs a {@code Prescription}.
     *
     * @param prescription A valid tag name.
     */
    public Prescription(String prescription) {
        requireNonNull(prescription);
        checkArgument(isValidPrescription(prescription), MESSAGE_CONSTRAINTS);
        this.value = prescription;
    }

    /**
     * Returns true if a given string is a valid prescription name.
     */
    public static boolean isValidPrescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public String getPrescription() {
        return value;
    }

    @Override
    public String toString() {
        return '[' + value + ']';
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Prescription)) {
            return false;
        }

        Prescription otherPrescription = (Prescription) other;
        return value.equals(otherPrescription.value);
    }
}
