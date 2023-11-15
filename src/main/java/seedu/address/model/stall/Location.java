package seedu.address.model.stall;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Stall's location in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS =
            "Locations should not be blank";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * This regex matches any string containing at least one non-whitespace character
     */
    public static final String VALIDATION_REGEX = "(.|\\s)*\\S(.|\\s)*";

    public final String locationName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Location(String name) {
        requireNonNull(name);
        checkArgument(isValidLocation(name), MESSAGE_CONSTRAINTS);
        locationName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return locationName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Location)) {
            return false;
        }

        Location otherLocation = (Location) other;
        return locationName.toLowerCase().equals(otherLocation.locationName.toLowerCase());
    }

    @Override
    public int hashCode() {
        return locationName.hashCode();
    }

}
