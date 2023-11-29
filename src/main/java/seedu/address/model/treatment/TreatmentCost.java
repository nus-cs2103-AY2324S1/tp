package seedu.address.model.treatment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Class which represents the cost of a Treatment in sgd
 */
public class TreatmentCost {


    public static final String MESSAGE_CONSTRAINTS =
        "The cost of the treatment should be between 1 and 7 digits";
    public static final String VALIDATION_REGEX = "^\\d{1,7}$";
    public final String value;

    /**
     * Constructs a {@code TreatmentCost}.
     *
     * @param cost A valid cost number.
     */
    public TreatmentCost(String cost) {
        requireNonNull(cost);
        checkArgument(isValidCost(cost), MESSAGE_CONSTRAINTS);
        value = cost;
    }

    /**
     * Returns true if a given string is a valid Treatment Cost.
     */
    public static boolean isValidCost(String test) {
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
        if (!(other instanceof TreatmentCost)) {
            return false;
        }

        TreatmentCost otherTreatment = (TreatmentCost) other;
        return value.equals(otherTreatment.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
