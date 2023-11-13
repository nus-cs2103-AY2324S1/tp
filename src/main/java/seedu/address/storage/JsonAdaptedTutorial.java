package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.tutorial.Tutorial;

/**
 * Jackson-friendly version of {@link Tutorial}.
 */
public class JsonAdaptedTutorial {
    public static final String MESSAGE_INVALID_MODULE = "This module code is invalid!";
    private final String moduleCode;
    private final String tutName;
    private final String time;

    /**
     * Constructs a {@code JsonAdaptedTutorial} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTutorial(@JsonProperty("moduleCode") String moduleCode,
                                    @JsonProperty("tutName") String tutName,
                                            @JsonProperty("time") String time) {
        this.moduleCode = moduleCode;
        this.tutName = tutName;
        this.time = time;
    }

    /**
     * Converts a given {@code Tutorial} into this class for Jackson use.
     */
    public JsonAdaptedTutorial(Tutorial source) {
        this.moduleCode = source.getModuleCode();
        this.tutName = source.getTutName();
        this.time = source.getTime();
    }

    /**
     * Converts this Jackson-friendly adapted tutorial object into the model's {@code Tutorial} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutorial.
     */
    public Tutorial toModelType() throws IllegalValueException {
        final Module module = new Module(moduleCode);
        return new Tutorial(module, tutName, time);
    }

}
