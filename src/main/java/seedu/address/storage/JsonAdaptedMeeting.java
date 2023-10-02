package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.Title;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String title;
    private final String location;
    private final String start;
    private final String end;
    private final List<JsonAdaptedAttendee> attendees = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("title") String title, @JsonProperty("location") String location,
                             @JsonProperty("start") String start, @JsonProperty("end") String end,
                             @JsonProperty("attendees") List<JsonAdaptedAttendee> attendees) {
        this.title = title;
        this.location = location;
        this.start = start;
        this.end = end;
        if (attendees != null) {
            this.attendees.addAll(attendees);
        }
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        title = source.getTitle().meetingTitle;
        location = source.getLocation().location;
        start = source.getStart().toString();
        end = source.getEnd().toString();
        attendees.addAll(source.getAttendees().stream()
                .map(JsonAdaptedAttendee::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        final List<Attendee> meetingAttendees = new ArrayList<>();
        for (JsonAdaptedAttendee person : attendees) {
            meetingAttendees.add(person.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (start == null || end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        if (!MeetingTime.isValidMeetingTime(LocalDateTime.parse(start), LocalDateTime.parse(end))) {
            throw new IllegalValueException(MeetingTime.MESSAGE_CONSTRAINTS);
        }
        final LocalDateTime modelStart = LocalDateTime.parse(start);
        final LocalDateTime modelEnd = LocalDateTime.parse(end);

        final Set<Attendee> modelAttendees = new HashSet<>(meetingAttendees);
        return new Meeting(modelTitle, modelLocation, modelStart, modelEnd, modelAttendees);
    }

}
