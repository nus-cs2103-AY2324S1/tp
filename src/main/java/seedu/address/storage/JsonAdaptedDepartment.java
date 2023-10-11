package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.department.Department;

/**
 * Jackson-friendly version of {@link Department}.
 */
class JsonAdaptedDepartment {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedDepartment(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedDepartment(Department source) {
        tagName = source.departmentName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Department toModelType() throws IllegalValueException {
        if (!Department.isValidDepartmentName(tagName)) {
            throw new IllegalValueException(Department.MESSAGE_CONSTRAINTS);
        }
        return new Department(tagName);
    }

}
