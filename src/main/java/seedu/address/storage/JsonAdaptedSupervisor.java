package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.name.EmployeeName;

/**
 * Jackson-friendly version of {@link EmployeeName}.
 */
class JsonAdaptedSupervisor {

    private final String managerName;

    /**
     * Constructs a {@code JsonAdaptedSupervisor} with the given {@code managerName}.
     */
    @JsonCreator
    public JsonAdaptedSupervisor(String managerName) {
        this.managerName = managerName;
    }

    /**
     * Converts a given {@code Name} into this class for Jackson use.
     */
    public JsonAdaptedSupervisor(EmployeeName source) {
        managerName = source.fullName;
    }

    @JsonValue
    public String getManagerName() {
        return managerName;
    }

    /**
     * Converts this Jackson-friendly adapted department object into the model's {@code Department} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted department.
     */
    public EmployeeName toModelType() throws IllegalValueException {
        if (!EmployeeName.isValidName(managerName)) {
            throw new IllegalValueException(EmployeeName.MESSAGE_CONSTRAINTS);
        }
        return new EmployeeName(managerName);
    }

}
