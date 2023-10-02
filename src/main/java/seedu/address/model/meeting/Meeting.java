package seedu.address.model.meeting;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class Meeting {
    private final Title title;
    private final Location location;
    private final MeetingTime meetingTime;
    private final Set<Attendee> attendees = new HashSet<>();

    public Meeting(Title title, Location location, LocalDateTime start, LocalDateTime end, Set<Attendee> attendees) {
        this.title = title;
        this.location = location;
        this.meetingTime = new MeetingTime(start, end);
        this.attendees.addAll(attendees);
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

    public Set<Attendee> getAttendees() {
        return Collections.unmodifiableSet(attendees);
    }

    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getTitle().equals(getTitle())
                && otherMeeting.getLocation().equals(getLocation())
                && otherMeeting.getMeetingTime().equals(getMeetingTime());
    }

    /**
     * Returns true if both meetings have the same title and data fields.
     * This defines a stronger notion of equality between two meetings.
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
        return title.equals(otherMeeting.title)
                && location.equals(otherMeeting.location)
                && meetingTime.equals(otherMeeting.meetingTime)
                && attendees.equals(otherMeeting.attendees);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, location, meetingTime, attendees);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("title", title)
                .add("location", location)
                .add("meeting time", meetingTime)
                .add("attendees", attendees)
                .toString();
    }
}
