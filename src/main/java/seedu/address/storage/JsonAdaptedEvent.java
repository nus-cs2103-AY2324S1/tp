package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventInformation;
import seedu.address.model.event.EventLocation;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String name;
    private final String start;
    private final String end;

    private final String location;

    private final String information;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name,
            @JsonProperty("start") String start, @JsonProperty("end") String end,
            @JsonProperty("location") String location,
            @JsonProperty("information") String information) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.location = location;
        this.information = information;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getName();
        start = source.getStartString();
        end = source.getEndString();
        location = source.getLocationStr();
        information = source.getInformationStr();
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's
     * {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted event.
     */
    public Event toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }

        if (start == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "start"));
        }

        if (end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "end"));
        }

        if (location == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, EventLocation.class.getSimpleName()));
        }

        if (information == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, EventInformation.class.getSimpleName()));
        }

        return new Event(name, start, end, location, information);
    }
}
