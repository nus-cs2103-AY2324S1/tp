package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person in the address book that will attend the meeting.
 * Guarantees: immutable; is valid as declared in {@link #isValidAttendee(String)}
 */
public class Attendee {

    public static final String MESSAGE_CONSTRAINTS =
            "Attendees should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

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
        return test.matches(VALIDATION_REGEX);
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
