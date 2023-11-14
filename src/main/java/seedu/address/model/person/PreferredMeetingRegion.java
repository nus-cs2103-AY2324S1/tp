package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's preferred meeting region in the address book.
 */
public class PreferredMeetingRegion {

    public static final String MESSAGE_CONSTRAINTS =
            "Preferred meeting region can take either north, south, east, west or central. It cannot be null";

    public final String value;

    /**
     * Constructs a {@code Preferred Meeting Region}.
     *
     * @param preferredMeetingRegion of client
     */
    public PreferredMeetingRegion(String preferredMeetingRegion) {
        requireNonNull(preferredMeetingRegion);
        checkArgument(isValidPreferredMeetingRegion(preferredMeetingRegion), MESSAGE_CONSTRAINTS);
        value = preferredMeetingRegion;
    }

    /**
     * Returns true if a given string is a valid preferred meeting region.
     */
    public static boolean isValidPreferredMeetingRegion(String test) {
        boolean result = test.equals("north") || test.equals("south") || test.equals("east")
                || test.equals("west") || test.equals("central");
        return result;
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
        if (!(other instanceof PreferredMeetingRegion)) {
            return false;
        }

        PreferredMeetingRegion otherPreferredMeetingRegion = (PreferredMeetingRegion) other;
        return value.equals(otherPreferredMeetingRegion.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
