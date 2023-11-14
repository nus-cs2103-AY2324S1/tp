package seedu.address.storage.events;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.EventsBook;
import seedu.address.model.ReadOnlyEventsBook;
import seedu.address.model.event.Event;

/**
 * An Immutable Events that is serializable to JSON format.
 */
public class JsonSerializableEventsBook {
    public static final String MESSAGE_DUPLICATE_EVENT = "Event list contains duplicate event(s).";

    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEventsBook} with the given events.
     */
    @JsonCreator
    public JsonSerializableEventsBook(@JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.events.addAll(events);
    }

    /**
     * Converts a given {@code ReadOnlyEventsBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEventsBook}.
     */
    public JsonSerializableEventsBook(ReadOnlyEventsBook source) {
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this events book into the model's {@code EventsBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EventsBook toModelType() throws IllegalValueException {
        EventsBook eventsBook = new EventsBook();
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (eventsBook.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            eventsBook.addEvent(event);
        }
        return eventsBook;
    }
}
