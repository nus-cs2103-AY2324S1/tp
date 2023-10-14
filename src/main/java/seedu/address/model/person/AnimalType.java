package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class AnimalType {
    public final String value;
    public final String availability; // New field for availability

    public static final String MESSAGE_CONSTRAINTS = "If fosterer is available, animal type should be 'able.Dog' / 'able.Cat'.\n" +
            "If fosterer is NOT available, animal type should be 'current.Dog' / 'current.Cat'.\n" +
            "If animal type information is not available, it should be inputted as 'nil'.";

    public static final String VALIDATION_REGEX_AVAILABLE = "^(able\\.Dog|able\\.Cat|nil)$";
    public static final String VALIDATION_REGEX_NOT_AVAILABLE = "^(current\\.Dog|current\\.Cat|nil)$";

    public AnimalType(String value, String availability) {
        requireNonNull(value);
        requireNonNull(availability);

        // Validate the availability
        if (availability.equals("Available")) {
            checkArgument(isValidAnimalType(value, VALIDATION_REGEX_AVAILABLE), MESSAGE_CONSTRAINTS);
        } else if (availability.equals("NotAvailable")) {
            checkArgument(isValidAnimalType(value, VALIDATION_REGEX_NOT_AVAILABLE), MESSAGE_CONSTRAINTS);
        } else {
            // Handle other availability values or raise an exception if needed
        }

        this.value = value;
        this.availability = availability;
    }

    public static boolean isValidAnimalType(String test, String validationRegex) {
        return test.matches(validationRegex);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        AnimalType otherAnimalType = (AnimalType) other;
        return value.equals(otherAnimalType.value) && availability.equals(otherAnimalType.availability);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}