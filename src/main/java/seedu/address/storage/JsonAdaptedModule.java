package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {
    private final String moduleCode;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("moduleCode") String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        this.moduleCode = source.getModuleCode();
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        if (!Module.isValidModule(this.moduleCode)) {
            throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
        }
        return new Module(moduleCode);
    }
}
