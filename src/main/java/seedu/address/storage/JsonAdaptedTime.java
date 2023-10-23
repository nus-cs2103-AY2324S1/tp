package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Time;
import seedu.address.model.TimeInterval;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JsonAdaptedTime {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Interval's %s time field is missing!";

    private final String start;
    private final String end;

    /**
     * Constructs a {@code JsonAdaptedTime} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTime(@JsonProperty("start") String start, @JsonProperty("end") String end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedTime(TimeInterval source) {
        this.start = source.getStart().toString();
        this.end = source.getEnd().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public TimeInterval toModelType() throws IllegalValueException {
        if (start == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "start"));
        }
        if (end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "end"));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        String[] startArray = start.split(" ");
        String[] endArray = end.split(" ");
        DayOfWeek startDay = Time.decodeDay(startArray[0]);
        DayOfWeek endDay = Time.decodeDay(endArray[0]);
        LocalTime startTime = LocalTime.parse(startArray[1], formatter);
        LocalTime endTime = LocalTime.parse(endArray[1], formatter);

        return new TimeInterval(new Time(startDay,startTime), new Time(endDay,endTime));
    }
}
