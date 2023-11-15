package seedu.cc.model.medicalhistory;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.AppUtil.checkArgument;

/**
 * Represents a patient's treatment.
 */
public class Treatment {
    public static final String MESSAGE_CONSTRAINTS =
            "Treatment should only contain alphanumeric characters and spaces, and it should not be blank";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String value;

    /**
     * Constructs a {@code Treatment}.
     *
     * @param treatment A valid treatment.
     */
    public Treatment(String treatment) {
        requireNonNull(treatment);
        checkArgument(isValidTreatment(treatment), MESSAGE_CONSTRAINTS);
        this.value = treatment;
    }

    public static boolean isValidTreatment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getTreatment() {
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
        if (!(other instanceof Treatment)) {
            return false;
        }

        Treatment otherTreatment = (Treatment) other;
        return value.equals(otherTreatment.value);
    }
}
