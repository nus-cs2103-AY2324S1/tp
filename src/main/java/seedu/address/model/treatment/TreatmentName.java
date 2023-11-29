package seedu.address.model.treatment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a treatment's name in the address book. Guarantees: immutable; is valid as declared in
 * {@link #isValidName(String)}
 */
public class TreatmentName {

    public static final String MESSAGE_CONSTRAINTS =
        "Treatment Names should only contain alphabets and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?=.*[A-Za-z])[A-Za-z ]+$";

    public final String value;

    /**
     * Constructs a {@code TreatmentName}.
     *
     * @param name A valid name.
     */
    public TreatmentName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getTreatmentName() {
        return value;
    }

    /**
     * Returns true if both treatments have the same name. This defines a weaker notion of equality
     * between two treatments.
     */
    public boolean isSameTreatmentName(TreatmentName otherTreatment) {
        return otherTreatment != null
                && otherTreatment.getTreatmentName().equals(getTreatmentName());
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
        if (!(other instanceof TreatmentName)) {
            return false;
        }

        TreatmentName otherName = (TreatmentName) other;
        return value.equalsIgnoreCase(otherName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
