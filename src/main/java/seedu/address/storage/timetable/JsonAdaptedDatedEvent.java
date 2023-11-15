package seedu.address.storage.timetable;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.TimeBlock;

/**
 * Jackson-friendly version of {@link DatedEvent}.
 */
public class JsonAdaptedDatedEvent {

    private final String name;
    private final String timeBlockString;
    private final String localDateString;
    private final boolean reminder;

    /**
     * Constructs a {@code JsonAdaptedDatedEvent} with the given {@code DatedEventName}.
     */
    @JsonCreator
    public JsonAdaptedDatedEvent(@JsonProperty("name") String name,
            @JsonProperty("timeblock") String timeBlockString,
            @JsonProperty("date") String localDateString,
            @JsonProperty("reminder") boolean reminder) {
        this.name = name;
        this.timeBlockString = timeBlockString;
        this.localDateString = localDateString;
        this.reminder = reminder;
    }

    /**
     * Converts a given {@code DatedEvent} into this class for Jackson use.
     */
    public JsonAdaptedDatedEvent(DatedEvent source) {
        name = source.getName();
        timeBlockString = source.getTimeBlockString();
        LocalDate date = source.getDate();
        localDateString = date.toString();
        reminder = source.hasReminder();
    }


    /**
     * Converts this Jackson-friendly adapted DatedEvent object into the model's {@code DatedEvent} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted datedEvent.
     */
    public DatedEvent toModelType() throws IllegalValueException {
        if (!DatedEvent.isValidDateTimeString(localDateString)) {
            throw new IllegalValueException(DatedEvent.MESSAGE_CONSTRAINTS);
        }

        if (!TimeBlock.isValidTimeBlock(timeBlockString)) {
            throw new IllegalValueException(TimeBlock.MESSAGE_CONSTRAINTS);
        }

        LocalDate date = LocalDate.parse(localDateString);
        return new DatedEvent(name, timeBlockString, date, reminder);
    }

}

