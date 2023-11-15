package seedu.address.storage.timetable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.Cca;

/**
 * Jackson-friendly version of {@link Cca}.
 */
public class JsonAdaptedCca {

    private final String name;
    private final String timeBlockString;

    /**
     * Constructs a {@code JsonAdaptedCca} with the given {@code CcaName}.
     */
    @JsonCreator
    public JsonAdaptedCca(@JsonProperty("name") String name,
        @JsonProperty("timeblock") String timeBlockString) {
        this.name = name;
        this.timeBlockString = timeBlockString;
    }

    /**
     * Converts a given {@code Cca} into this class for Jackson use.
     */
    public JsonAdaptedCca(Cca source) {
        name = source.getName();
        timeBlockString = source.getTimeBlockString();
    }

    /**
     * Converts this Jackson-friendly adapted Cca object into the model's {@code Cca} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted cca.
     */
    public Cca toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException("CCA name should not be null");
        }
        if (timeBlockString == null) {
            throw new IllegalValueException("Time block should not be null");
        }

        return new Cca(name, timeBlockString);
    }
}
