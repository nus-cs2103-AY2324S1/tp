package seedu.address.storage.event;

import static seedu.address.logic.Messages.MESSAGE_INCORRECT_DATE_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";
    private final String name;
    private final String description;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name, @JsonProperty("description") String description,
                             @JsonProperty("startTime") String startTime, @JsonProperty("endTime") String endTime) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        name = source.getPerson().getName().fullName;
        description = source.getDescription();
        startTime = source.getStart_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        endTime = source.getEnd_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }

        LocalDateTime modelStartTime;
        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "startTime"));
        } else {
            try {
                modelStartTime = LocalDateTime.parse(startTime,
                        DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT));
            } catch (DateTimeException e) {
                String s = e.getMessage();
                if (s.contains("Invalid")) {
                    throw new ParseException(MESSAGE_INVALID_DATE);
                } else {
                    throw new ParseException(MESSAGE_INCORRECT_DATE_FORMAT);
                }
            }
        }

        LocalDateTime modelEndTime;
        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "endTime"));
        } else {
            try {
                modelEndTime = LocalDateTime.parse(endTime,
                        DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT));
            } catch (DateTimeException e) {
                String s = e.getMessage();
                if (s.contains("Invalid")) {
                    throw new ParseException(MESSAGE_INVALID_DATE);
                } else {
                    throw new ParseException(MESSAGE_INCORRECT_DATE_FORMAT);
                }
            }
        }

        Person person = new Person(new Name(name), new Phone("00000"), new Email("filler@email.com"),
                new Address("36 College Avenue East"), new Remark(""), new HashSet<>());
        Event e = new Event(person, description, modelStartTime, modelEndTime);
        return e;
    }
}
