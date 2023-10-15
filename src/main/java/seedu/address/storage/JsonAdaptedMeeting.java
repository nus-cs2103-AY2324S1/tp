package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.*;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

import javax.swing.text.html.Option;
import java.util.Optional;

public class JsonAdaptedMeeting {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String date;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("name") String name, @JsonProperty("date") String date,
                              @JsonProperty("startTime") String startTime, @JsonProperty("endTime") String endTime) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Event source) {
        this.name = source.getName().name;
        this.date = source.getStartDate().toString();
        this.startTime = source.hasStartTime() ? source.getStartTime().toString() : "";
        this.endTime = source.hasEndTime() ? source.getEndTime().toString() : "";
    }


    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Meeting toModelType() throws IllegalValueException {

        if (this.name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final EventName modelName = new EventName(name);

        if (this.date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EventDate.class.getSimpleName()));
        }
        if (!EventDate.isValidDate(this.date)) {
            throw new IllegalValueException(EventDate.MESSAGE_CONSTRAINTS);
        }
        final EventDate modelEventDate = new EventDate(this.date);

        if (this.startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EventTime.class.getSimpleName()));
        }
        if (!EventTime.isValidTime(this.startTime)) {
            throw new IllegalValueException(EventTime.MESSAGE_CONSTRAINTS);
        }
        final EventTime modelEventStartTime = EventTime.of(this.startTime);

        if (this.endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EventTime.class.getSimpleName()));
        }
        if (!EventTime.isValidTime(this.endTime)) {
            throw new IllegalValueException(EventTime.MESSAGE_CONSTRAINTS);
        }
        final EventTime modelEventEndTime = EventTime.of(this.endTime);

        return new Meeting(modelName, modelEventDate,
                Optional.of(modelEventStartTime), Optional.of(modelEventEndTime));
    }

    @Override
    public String toString() {
        return "Name: " + name + " Date: " + date + " Start Time: " + startTime + " End Time: " + endTime;
    }
}
