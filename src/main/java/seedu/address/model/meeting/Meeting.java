package seedu.address.model.meeting;

import static seedu.address.commons.util.DateTimeUtil.verbose;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Meeting in the address book. Guarantees: details are present and
 * not null, field values are validated, immutable.
 */
public class Meeting {
    private final Title title;
    private final Location location;
    private final MeetingTime meetingTime;
    private final Set<Attendee> attendees;
    private final Set<Tag> tags;
    private final MeetingStatus status;

    /**
     * Every field must be present and not null.
     */
    public Meeting(Title title, Location location, LocalDateTime start, LocalDateTime end, Set<Attendee> attendees,
            Set<Tag> tags, MeetingStatus status) {
        this.title = title;
        this.location = location;
        this.meetingTime = new MeetingTime(start, end);
        this.attendees = new LinkedHashSet<>(attendees);
        this.tags = new HashSet<>(tags);
        this.status = status;
    }

    public Title getTitle() {
        return title;
    }

    public Location getLocation() {
        return location;
    }

    public LocalDateTime getStart() {
        return meetingTime.getStart();
    }

    public LocalDateTime getEnd() {
        return meetingTime.getEnd();
    }

    public MeetingTime getMeetingTime() {
        return meetingTime;
    }

    public MeetingStatus getStatus() {
        return status;
    }

    public boolean withinSpecifiedTime(LocalDateTime start, LocalDateTime end) {
        return !meetingTime.getStart().isBefore(start) && !meetingTime.getEnd().isAfter(end);
    }

    /**
     * Returns an immutable attendee set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Attendee> getAttendees() {
        return Collections.unmodifiableSet(attendees);
    }

    /**
     * Returns an attendee at the given {@code index}
     */
    public Attendee getAttendee(Index index) {
        return new ArrayList<>(attendees).get(index.getZeroBased());
    }

    /**
     * Updates the given attendee {@code targetAttendee} with {@code editedAttendee} for all meetings.
     * @param targetAttendee The Attendee name to be replaced from all meetings.
     * @param editedAttendee The Attendee name to replace the {@code targetAttendee}.
     */
    public void updateAttendee(String targetAttendee, String editedAttendee) {
        if (attendees.contains(new Attendee(targetAttendee))) {
            attendees.remove(new Attendee(targetAttendee));
            attendees.add(new Attendee(editedAttendee));
        }
    }

    /**
     * Deletes the given attendee {@code targetAttendee} from all meetings.
     * @param targetAttendee The attendee name to be deleted.
     */
    public void deleteAttendee(String targetAttendee) {
        attendees.remove(new Attendee(targetAttendee));
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if {@code otherMeeting} has the same identity as this meeting.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null && otherMeeting.getTitle().equals(getTitle())
                && otherMeeting.getLocation().equals(getLocation())
                && otherMeeting.getMeetingTime().equals(getMeetingTime()) && otherMeeting.getTags().equals(getTags());
    }

    /**
     * Returns true if both meetings have the same title and data fields. This
     * defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return title.equals(otherMeeting.title) && location.equals(otherMeeting.location)
                && meetingTime.equals(otherMeeting.meetingTime) && attendees.equals(otherMeeting.attendees)
                && tags.equals(otherMeeting.tags) && status.equals(otherMeeting.status);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, location, meetingTime, attendees);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("title", title).add("location", location)
                .add("start", meetingTime.getStart()).add("end", meetingTime.getEnd()).add("attendees", attendees)
                .add("tags", tags).add("status", status).toString();
    }

    /**
     * Returns detailed information of Meeting for viewm command.
     */
    public String toDisplayString() {
        AtomicInteger counter = new AtomicInteger(1);
        String indexedAttendees = attendees.stream()
                .map(x -> "\n" + counter.getAndIncrement() + ". " + x.getAttendeeName())
                .reduce("", (result, next) -> result + next);
        return String.format("Title: %s\nLocation: %s\nStart: %s\nEnd: %s\nAttendees: %s\n", title, location,
                verbose(meetingTime.getStart()), verbose(meetingTime.getEnd()),
                indexedAttendees);

    }
}
