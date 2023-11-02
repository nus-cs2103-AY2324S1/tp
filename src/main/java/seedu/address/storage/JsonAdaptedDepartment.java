package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.department.Department;
import seedu.address.model.name.Name;

/**
 * Jackson-friendly version of {@link Department}, in an object format.
 */
class JsonAdaptedDepartment {

    private final String name;
    private final List<JsonAdaptedName> employees = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedDepartment} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedDepartment(@JsonProperty("name") String name,
                                @JsonProperty("employees") List<JsonAdaptedName> employees) {
        this.name = name;
        if (employees != null) {
            this.employees.addAll(employees);
        }
    }

    /**
     * Converts a given {@code Department} into this class for Jackson use.
     */
    public JsonAdaptedDepartment(Department source) {

        this.name = source.getName();
        employees.addAll(source.getEmployees().stream()
                .map(JsonAdaptedName::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted department object into the model's {@code Department} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted department.
     */
    public Department toModelType() throws IllegalValueException {
        if (!Name.isValidName(this.name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        Set<Name> employees = new HashSet<>();
        for (JsonAdaptedName jsonName : this.employees) {
            employees.add(jsonName.toModelType());
        }
        return new Department(new Name(this.name), employees);
    }

}
