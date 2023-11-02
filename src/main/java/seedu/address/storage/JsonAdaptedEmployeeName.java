package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.name.EmployeeName;

/**
 * Jackson-friendly version of {@link EmployeeName}, in a single string format.
 */
public class JsonAdaptedEmployeeName {
    private final String name;

    /**
     * Constructs a {@code JsonAdaptedDepartment} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedEmployeeName(String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Name} into this class for Jackson use.
     */
    public JsonAdaptedEmployeeName(EmployeeName name) {
        this.name = name.fullName;
    }

    /**
     * Converts a given {@code Department} into this class for Jackson use.
     */
    @JsonValue
    public String getDepartmentName() {
        return this.name;
    }

    /**
     * Converts this Jackson-friendly adapted department object into the model's {@code Department} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted department.
     */
    public EmployeeName toModelType() throws IllegalValueException {
        if (!EmployeeName.isValidName(this.name)) {
            throw new IllegalValueException(EmployeeName.MESSAGE_CONSTRAINTS);
        }
        return new EmployeeName(this.name);
    }

}
