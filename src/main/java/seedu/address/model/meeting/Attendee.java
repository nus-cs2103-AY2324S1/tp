package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.Person;

public class Attendee {

    public static final String MESSAGE_CONSTRAINTS = "Attendee names should have a 1 to 1 correlation with Person names";
    private final String attendeeName;

    /**
     * Constructs a {@code Attendee}.
     *
     * @param attendeeName A valid tag name.
     */
    public Attendee(String attendeeName) {
        requireNonNull(attendeeName);
        checkArgument(isValidAttendee(attendeeName), MESSAGE_CONSTRAINTS);
        this.attendeeName = attendeeName;
    }

    public static boolean isValidAttendee(String test) {
        return true;
    }

    public String getAttendeeName() {
        return attendeeName;
    }

    @Override
    public int hashCode() {
        return attendeeName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return attendeeName + " ";
    }
}
