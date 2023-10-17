package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String title;
    private final String location;
    private final String start;
    private final String end;
    private final List<JsonAdaptedAttendee> attendees = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("title") String title, @JsonProperty("location") String location,
                             @JsonProperty("start") String start, @JsonProperty("end") String end,
                             @JsonProperty("attendees") List<JsonAdaptedAttendee> attendees,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {

        this.title = title;
        this.location = location;
        this.start = start;
        this.end = end;
        if (attendees != null) {
            this.attendees.addAll(attendees);
        }
        if (tags != null) {
            this.tags.addAll(tags);
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
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
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

        final List<Tag> meetingTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            meetingTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (start == null || end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        try {
            if (!MeetingTime.isValidMeetingTime(LocalDateTime.parse(start), LocalDateTime.parse(end))) {
                throw new IllegalValueException(MeetingTime.MESSAGE_CONSTRAINTS);
            }
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(MeetingTime.MESSAGE_CONSTRAINTS);
        }
        final LocalDateTime modelStart = LocalDateTime.parse(start);
        final LocalDateTime modelEnd = LocalDateTime.parse(end);

        final Set<Attendee> modelAttendees = new HashSet<>(meetingAttendees);

        final Set<Tag> modelTags = new HashSet<>(meetingTags);
        return new Meeting(modelTitle, modelLocation, modelStart, modelEnd, modelAttendees, modelTags);
    }

}
