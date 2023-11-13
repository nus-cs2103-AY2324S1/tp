package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a scheduled time for a meeting in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class MeetingTime {

    public static final String MESSAGE_CONSTRAINTS = "Meeting Time should have the format [dd.mm.yyyy HHmm] and Start"
            + " cannot be after End\neg. 18.09.2023 1500 represents 18 September 2023, 3PM";

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs the meeting time for a meeting.
     */
    public MeetingTime(LocalDateTime start, LocalDateTime end) {
        requireNonNull(start);
        requireNonNull(end);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public static boolean isValidMeetingTime(LocalDateTime start, LocalDateTime end) {
        return !start.isAfter(end) && start.isAfter(LocalDateTime.MIN) && end.isBefore(LocalDateTime.MAX);
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
        return start.equals(otherMeetingTime.start)
                && end.equals(otherMeetingTime.end);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("start", start)
                .add("end", end)
                .toString();
    }
}
