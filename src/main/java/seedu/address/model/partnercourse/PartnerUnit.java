package seedu.address.model.partnercourse;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.messages.ConstraintMessage;

/**
 * Represents a Partner Course's unit in SEPlendid.
 * Guarantees: immutable; is valid as declared in {@link #isValidPartnerUnit(Object)}
 */
public class PartnerUnit {
    private final Double value;

    /**
     * Constructs an {@code PartnerUnit}.
     *
     * @param partnerUnit A valid partner unit.
     */
    public PartnerUnit(String partnerUnit) {
        requireNonNull(partnerUnit);
        checkArgument(isValidPartnerUnit(partnerUnit), ConstraintMessage.PARTNERCOURSE_UNIT.toString());
        value = Double.parseDouble(partnerUnit);
    }

    /**
     * Constructs an {@code PartnerUnit}.
     *
     * @param partnerUnit A valid partner unit.
     */
    public PartnerUnit(Double partnerUnit) {
        requireNonNull(partnerUnit);
        checkArgument(isValidPartnerUnit(partnerUnit), ConstraintMessage.PARTNERCOURSE_UNIT.toString());
        value = partnerUnit;
    }


    /**
     * Checks if the provided object is a valid partner unit.
     * A valid partner unit is a positive number.
     *
     * @param test The object to be validated.
     * @return true if the object is a valid partner unit, false otherwise.
     */
    public static boolean isValidPartnerUnit(Object test) {
        if (test == null) {
            // Handle null input gracefully
            return false;
        }

        if (test instanceof String) {
            // If the input is a String, attempt to parse it as a double
            try {
                double parsedValue = Double.parseDouble((String) test);

                // Check if the parsed value is greater than 0
                return parsedValue >= 0 && parsedValue <= 10000;
            } catch (NumberFormatException e) {
                // If parsing fails, return false
                return false;
            }
        }

        if (test instanceof Number) {
            // If the input is a Number, check if its double value is greater than 0
            return ((Number) test).doubleValue() >= 0;
        }

        // If the input is neither a String nor a Number, return false
        return false;
    }

    public Double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PartnerUnit)) {
            return false;
        }

        PartnerUnit otherPartnerName = (PartnerUnit) other;
        // performs semantic check
        return value.equals(otherPartnerName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
