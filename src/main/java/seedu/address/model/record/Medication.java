package seedu.address.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a medication that a patient receives. Recorded inside a Record
 * object.
 */
public class Medication {
    public static final String MESSAGE_CONSTRAINTS = "Medications should only contain alphanumeric characters, "
            + "dashes and spaces";

    public static final String VALIDATION_REGEX = "[a-zA-Z0-9- ]+";
    public final String medication;

    /**
     * Constructs a {@code Medication}
     *
     * @param medication A valid medication name.
     */
    public Medication(String medication) {
        requireNonNull(medication);
        checkArgument(isValidMedication(medication), MESSAGE_CONSTRAINTS);
        this.medication = medication;
    }

    public static boolean isValidMedication(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Medication)) {
            return false;
        }

        Medication otherMedication = (Medication) other;
        return this.medication.equals(otherMedication.medication);
    }

    @Override
    public int hashCode() {
        return medication.hashCode();
    }

    @Override
    public String toString() {
        return medication;
    }
}
