package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventPeriod;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";
    public static final String INVALID_FIELD_MESSAGE_FORMAT = EventPeriod.MESSAGE_CONSTRAINTS;

    private final String description;
    private final String eventPeriod;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("description") String description,
                            @JsonProperty("eventPeriod") String eventPeriod) {
        this.description = description;
        this.eventPeriod = eventPeriod;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        description = source.getDescription().getDescription();
        eventPeriod = source.getEventPeriod().getFormattedPeriod();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            EventDescription.class.getSimpleName()));
        }
        if (!EventDescription.isValid(description)) {
            throw new IllegalValueException(EventDescription.MESSAGE_CONSTRAINTS);
        }
        final EventDescription modelDescription = new EventDescription(description);

        // Event Periods are expected to be saved in this format yyyy-MM-dd HH:mm - yyyy-MM-dd HH:mm
        String start;
        String end;
        if (eventPeriod == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            EventPeriod.class.getSimpleName()));
        }
        String[] parts = eventPeriod.split(" - ");
        if (parts.length == 2) {
            start = parts[0];
            end = parts[1];
        } else {
            throw new IllegalValueException(String.format(INVALID_FIELD_MESSAGE_FORMAT,
                    EventPeriod.class.getSimpleName()));
        }
        if (!EventPeriod.isValidPeriod(start, end)) {
            throw new IllegalValueException(EventPeriod.MESSAGE_CONSTRAINTS);
        }
        final EventPeriod modelEventPeriod = new EventPeriod(start, end);

        return new Event(modelDescription, modelEventPeriod);
    }

}
