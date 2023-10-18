package seedu.address.model.meeting;

import static seedu.address.logic.parser.ParserUtil.FORMAT;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Meetings}'s {@code Title} matches any of the keywords given.
 */
public class MeetingTimeContainsPredicate implements Predicate<Meeting> {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public MeetingTimeContainsPredicate(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
        this.start.format(FORMAT);
        this.end.format(FORMAT);
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

        MeetingTimeContainsPredicate otherMeetingTimeContainsPredicate = (MeetingTimeContainsPredicate) other;
        return this.start.equals(otherMeetingTimeContainsPredicate.start) && this.end.equals(otherMeetingTimeContainsPredicate.end);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("start", start).add("end", end).toString();
    }
}
