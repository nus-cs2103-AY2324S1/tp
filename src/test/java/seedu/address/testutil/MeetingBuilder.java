package seedu.address.testutil;

import static seedu.address.model.meeting.MeetingTimeTest.FORMAT;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.Title;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_TITLE = "Test Meeting 1";
    public static final String DEFAULT_LOCATION = "Room 1";
    public static final LocalDateTime DEFAULT_START = LocalDateTime.parse("02.10.2023 1000", FORMAT);
    public static final LocalDateTime DEFAULT_END = LocalDateTime.parse("03.10.2023 1000", FORMAT);
    private Title title;
    private Location location;
    private LocalDateTime start;
    private LocalDateTime end;
    private Set<Attendee> attendees;
    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public MeetingBuilder() {
        title = new Title(DEFAULT_TITLE);
        this.location = new Location(DEFAULT_LOCATION);
        this.start = DEFAULT_START;
        this.end = DEFAULT_END;
        attendees = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public MeetingBuilder(Meeting personToCopy) {
        title = personToCopy.getTitle();
        location = personToCopy.getLocation();
        start = personToCopy.getStart();
        end = personToCopy.getEnd();
        attendees = new HashSet<>(personToCopy.getAttendees());
    }

    /**
     * Sets the {@code Title} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTitle(String name) {
        this.title = new Title(name);
        return this;
    }

    /**
     * Parses the {@code attendees} into a {@code Set<Attendee>} and set it to the {@code Meeting} that we are building.
     */
    public MeetingBuilder withAttendees(String ... attendees) {
        this.attendees = SampleDataUtil.getAttendeeSet(attendees);
        return this;
    }

    /**
     * Sets the {@code start} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withStart(String start) {
        this.start = LocalDateTime.parse(start, FORMAT);
        return this;
    }

    /**
     * Sets the {@code start} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withEnd(String end) {
        this.end = LocalDateTime.parse(end, FORMAT);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public MeetingBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    public Meeting build() {
            return new Meeting(title, location, start, end, attendees);
        }
}
