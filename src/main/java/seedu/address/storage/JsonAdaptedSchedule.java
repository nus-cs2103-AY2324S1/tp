package seedu.address.storage;

import static seedu.address.model.schedule.Time.DATETIME_INPUT_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;
import seedu.address.model.schedule.Status;

/**
 * Jackson-friendly version of {@link seedu.address.model.schedule.Schedule}.
 */
class JsonAdaptedSchedule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Schedule's %s field is missing!";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    private final String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_INPUT_FORMAT)
    private final String startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_INPUT_FORMAT)
    private final String endTime;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("name") String name, @JsonProperty("startTime") String startTime,
                               @JsonProperty("endTime") String endTime, @JsonProperty("status") String status) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    /**
     * Converts a given {@code Schedule} into this class for Jackson use.
     */
    public JsonAdaptedSchedule(Schedule source) {
        name = source.getTutor().getName().fullName;
        startTime = source.getStartTime().getTime().format(formatter);
        endTime = source.getEndTime().getTime().format(formatter);
        status = source.getStatus().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted schedule.
     */
    public Schedule toModelType(ReadOnlyAddressBook addressBook) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StartTime.class.getSimpleName()));
        }
        if (!StartTime.isValidStartTime(startTime)) {
            throw new IllegalValueException(StartTime.MESSAGE_CONSTRAINTS);
        }
        final StartTime modelStartTime = new StartTime(LocalDateTime.parse(startTime, formatter));

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EndTime.class.getSimpleName()));
        }
        if (!EndTime.isValidEndTime(endTime)) {
            throw new IllegalValueException(EndTime.MESSAGE_CONSTRAINTS);
        }
        final EndTime modelEndTime = new EndTime(LocalDateTime.parse(endTime, formatter));

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        return new Schedule(getTutorFromName(modelName, addressBook), modelStartTime, modelEndTime,
            Status.valueOf(status));
    }

    /**
     * Helper method to find the tutor object given the name stored in json file.
     *
     * @throws PersonNotFoundException if no matching names were found in the tutors list.
     */
    private Person getTutorFromName(Name name, ReadOnlyAddressBook addressBook) throws PersonNotFoundException {
        ObservableList<Person> tutors = addressBook.getPersonList();
        return tutors.stream().filter(o -> name.equals(o.getName())).findFirst()
            .orElseThrow(PersonNotFoundException::new);
    }

}
