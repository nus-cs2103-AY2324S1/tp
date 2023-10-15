package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Meeting;
import seedu.address.model.person.Name;


/**
 * Json-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    public static final String UNKNOWN_EVENT_TYPE = "Unknown event type!";

    private final String eventType;

    private final String name;
    private final String date;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventType") String eventType, @JsonProperty("name") String name,
                            @JsonProperty("date") String date, @JsonProperty("startTime") String startTime,
                            @JsonProperty("endTime") String endTime) {
        this.eventType = eventType;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Meeting} into this class for Json use.
     */
    public JsonAdaptedEvent(Event source) {
        this.eventType = source.getEventType().toString();
        this.name = source.getName().name;
        this.date = source.getStartDate().toString();
        this.startTime = source.hasStartTime() ? source.getStartTime().toString() : "";
        this.endTime = source.hasEndTime() ? source.getEndTime().toString() : "";
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Event toModelType() throws IllegalValueException {

        if (this.name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final EventName modelName = new EventName(name);

        if (this.date == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, EventDate.class.getSimpleName()));
        }
        if (!EventDate.isValidDate(this.date)) {
            throw new IllegalValueException(EventDate.MESSAGE_CONSTRAINTS);
        }
        final EventDate modelEventDate = new EventDate(this.date);

        if (this.startTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, EventTime.class.getSimpleName()));
        }
        if (!EventTime.isValidTime(this.startTime)) {
            throw new IllegalValueException(EventTime.MESSAGE_CONSTRAINTS);
        }
        final EventTime modelEventStartTime = EventTime.of(this.startTime);

        if (this.endTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, EventTime.class.getSimpleName()));
        }
        if (!EventTime.isValidTime(this.endTime)) {
            throw new IllegalValueException(EventTime.MESSAGE_CONSTRAINTS);
        }
        final EventTime modelEventEndTime = EventTime.of(this.endTime);

        // no other events for now
        return checkEventType(modelName, modelEventDate,
                Optional.of(modelEventStartTime), Optional.of(modelEventEndTime));
    }


    private Event checkEventType(EventName eventName,
                                 EventDate eventDate,
                                 Optional<EventTime> startTime,
                                 Optional<EventTime> endTime) throws IllegalValueException {

        switch (this.eventType) {
        case "meeting":
            return new Meeting(eventName, eventDate, startTime, endTime);
        default:
            throw new IllegalValueException(UNKNOWN_EVENT_TYPE);
        }

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("event_type", this.eventType)
                .add("name", this.name)
                .add("date", this.date)
                .add("start_time", this.startTime == null ? "" : this.startTime)
                .add("end_time", this.endTime == null ? "" : this.endTime)
                .toString();
    }
}
