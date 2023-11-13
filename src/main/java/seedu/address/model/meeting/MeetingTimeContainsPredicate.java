package seedu.address.model.meeting;

import static seedu.address.commons.util.DateTimeUtil.format;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Meeting}'s {@code MeetingTime} duration within the given start and end.
 */
public class MeetingTimeContainsPredicate implements Predicate<Meeting> {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a predicate with the given start and end times.
     * @param start start of the duration to be checked.
     * @param end end of the duration to be checked.
     */
    public MeetingTimeContainsPredicate(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
        format(this.start);
        format(this.end);
        MeetingTime.isValidMeetingTime(start, end);
    }

    @Override
    public boolean test(Meeting meeting) {
        return meeting.withinSpecifiedTime(start, end);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingTimeContainsPredicate)) {
            return false;
        }

        MeetingTimeContainsPredicate otherMeetingTimeContainsPredicate =
                (MeetingTimeContainsPredicate) other;
        return this.start.equals(otherMeetingTimeContainsPredicate.start)
                && this.end.equals(otherMeetingTimeContainsPredicate.end);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("start", start).add("end", end).toString();
    }
}
