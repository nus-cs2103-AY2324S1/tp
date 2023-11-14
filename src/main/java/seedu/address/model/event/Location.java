package seedu.address.model.event;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;


/**
 * Represents a Event's location in the events book.
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS = "Location can take any values up to 256 characters";

    public static final String VALIDATION_REGEX = "^(?!\\s)[\\s\\S]{0,256}$";
    public final String value;

    /**
     * Constructs a {@code Location}.
     *
     * @param locationName A valid location.
     */
    public Location(String locationName) {
        checkArgument(isValidLocation(locationName), MESSAGE_CONSTRAINTS);
        this.value = locationName;
    }

    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Location location = (Location) obj;
        return Objects.equals(value, location.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
