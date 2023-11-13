package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Meeting;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_MEETING_NAME = "TP MEETING TEST";
    public static final String DEFAULT_DATE = "2023-10-18";

    private EventName eventName;
    private EventDate date;
    private EventTime startTime;
    private EventTime endTime;
    private Set<Name> names;

    private Set<Group> groups = new HashSet<>();

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() throws ParseException {
        eventName = new EventName(DEFAULT_MEETING_NAME);
        date = new EventDate(DEFAULT_DATE);
        startTime = EventTime.NULL_EVENT_TIME;
        endTime = EventTime.NULL_EVENT_TIME;
        names = new HashSet<>();
        groups = new HashSet<>();
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        eventName = meetingToCopy.getName();
        date = meetingToCopy.getStartDate();
        startTime = meetingToCopy.getStartTime();
        endTime = meetingToCopy.getEndTime();
        names = new HashSet<>(meetingToCopy.getNames());
    }

    /**
     * Sets the {@code Name} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withEventName(String name) throws ParseException {
        this.eventName = ParserUtil.parseEventName(name);
        return this;
    }

    /**
     * Parses the {@code names} into a {@code Set<Name>} and set it to the {@code Meeting} that we are building.
     * @param names names of the persons
     * @return this instance of MeetingBuilder
     * @throws ParseException if there were any parsing errors.
     */
    public MeetingBuilder withPerson(String... names) throws ParseException {
        for (String name : names) {
            this.names.add(new Name(name));
        }
        return this;
    }

    /**
     * Sets the {@code names} of the {@code Meeting} that we are building.
     * @param names names of the persons
     * @return this instance of MeetingBuilder
     */
    public MeetingBuilder withPerson(Set<Name> names) {
        this.names = names;
        return this;
    }

    /**
     * Parses the {@code groups} into a {@code Set<Group>} and set it to the {@code Meeting} that we are building.
     * @param groups names of the groups to add
     * @return this instance of MeetingBuilder
     * @throws ParseException if there were any parsing errors.
     */
    public MeetingBuilder withGroups(String... groups) throws ParseException {
        for (String group : groups) {
            this.groups.add(new Group(group));
        }
        return this;
    }

    /**
     * Sets the {@code groups} of the {@code Meeting} that we are building.
     * @param groups the groups to add
     * @return this instance of MeetingBuilder
     */
    public MeetingBuilder withGroups(Set<Group> groups) {
        this.groups = groups;
        return this;
    }

    /**
     * Sets the {@code EventDate} of the {@code Meeting} that we are building.
     * @param date the date of the meeting
     * @return this
     * @throws ParseException if there were any parsing errors.
     */
    public MeetingBuilder withEventDate(String date) throws ParseException {
        this.date = ParserUtil.parseEventDate(date);
        return this;
    }

    /**
     * Sets the {@code EventTime} of the {@code Meeting} that we are building.
     * @param startTime the start time of the meeting
     * @return this
     * @throws ParseException if there were any parsing errors.
     */
    public MeetingBuilder withEventStartTime(String startTime) throws ParseException {
        this.startTime = ParserUtil.parseEventTime(startTime);
        return this;
    }

    /**
     * Sets the {@code EventTime} of the {@code Meeting} that we are building.
     * @param endTime the end time of the meeting
     * @return this
     * @throws ParseException if there were any parsing errors.
     */
    public MeetingBuilder withEventEndTime(String endTime) throws ParseException {
        this.endTime = ParserUtil.parseEventTime(endTime);
        return this;
    }



    /**
     * Builds a meeting object.
     * @return a meeting object.
     */
    public Meeting build() {
        return new Meeting(eventName, date, Optional.of(startTime), Optional.of(endTime), names, groups);
    }
}
