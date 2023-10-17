package seedu.address.testutil;

import static seedu.address.model.meeting.MeetingTimeTest.FORMAT;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Title;
import seedu.address.model.tag.Tag;
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
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public MeetingBuilder() {
        title = new Title(DEFAULT_TITLE);
        location = new Location(DEFAULT_LOCATION);
        start = DEFAULT_START;
        end = DEFAULT_END;
        attendees = new LinkedHashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        title = meetingToCopy.getTitle();
        location = meetingToCopy.getLocation();
        start = meetingToCopy.getStart();
        end = meetingToCopy.getEnd();
        attendees = new LinkedHashSet<>(meetingToCopy.getAttendees());
        tags = new HashSet<>(meetingToCopy.getTags());
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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
        return new Meeting(title, location, start, end, attendees, tags);
    }
}
