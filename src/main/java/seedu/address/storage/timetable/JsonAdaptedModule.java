package seedu.address.storage.timetable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.Module;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {

    private final String name;
    private final String timeBlockString;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given {@code ModuleName}.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String name,
        @JsonProperty("timeblock") String timeBlockString) {
        this.name = name;
        this.timeBlockString = timeBlockString;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        name = source.getName();
        timeBlockString = source.getTimeBlockString();
    }

    /**
     * Converts this Jackson-friendly adapted Module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException("Module name should not be null");
        }
        if (timeBlockString == null) {
            throw new IllegalValueException("Time block should not be null");
        }

        return new Module(name, timeBlockString);
    }
}
