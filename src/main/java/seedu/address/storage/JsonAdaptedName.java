package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Employee;
import seedu.address.model.name.Name;

/**
 * Jackson-friendly version of {@link Name}, in a single string format.
 */
public class JsonAdaptedName {
    private final String name;

    /**
     * Constructs a {@code JsonAdaptedDepartment} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedName(String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Name} into this class for Jackson use.
     */
    public JsonAdaptedName(Name name) {
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
    public Name toModelType() throws IllegalValueException {
        if (!Name.isValidName(this.name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(this.name);
    }

}
