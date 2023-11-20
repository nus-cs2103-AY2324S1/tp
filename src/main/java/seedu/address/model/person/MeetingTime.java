package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.MeetingTimeFormatter.DATE_TIME_FORMAT;

import java.time.LocalDateTime;

/**
 * Represents a Person's meeting time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMeetingTime(String)}
 */
public class MeetingTime {

    // Replace uuuu in format to yyyy to not confuse users
    public static final String MESSAGE_CONSTRAINTS = "Meeting time should be in the format of "
            + DATE_TIME_FORMAT.replace("u", "y")
            + " and have a valid date and time";

    public final LocalDateTime value;

    /**
     * Constructs a {@code MeetingTime}.
     *
     * @param meetingTime A valid meeting time.
     */
    public MeetingTime(String meetingTime) {
        checkArgument(isValidMeetingTime(meetingTime), MESSAGE_CONSTRAINTS);
        this.value = MeetingTimeFormatter.parse(meetingTime);
    }

    public static boolean isValidMeetingTime(String test) {
        return MeetingTimeFormatter.isValid(test);
    }

    @Override
    public String toString() {
        return MeetingTimeFormatter.format(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingTime)) {
            return false;
        }

        MeetingTime otherMeetingTime = (MeetingTime) other;
        return value.equals(otherMeetingTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
