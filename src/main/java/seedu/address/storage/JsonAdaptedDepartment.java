package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.department.Department;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;

/**
 * Jackson-friendly version of {@link Department}, in an object format.
 */
class JsonAdaptedDepartment {

    private final String name;
    private final List<JsonAdaptedEmployeeName> employees = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedDepartment} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedDepartment(@JsonProperty("name") String name,
                                @JsonProperty("employees") List<JsonAdaptedEmployeeName> employees) {
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
                .map(JsonAdaptedEmployeeName::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted department object into the model's {@code Department} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted department.
     */
    public Department toModelType() throws IllegalValueException {
        if (!DepartmentName.isValidName(this.name)) {
            throw new IllegalValueException(DepartmentName.MESSAGE_CONSTRAINTS);
        }
        Set<EmployeeName> employees = new HashSet<>();
        for (JsonAdaptedEmployeeName jsonName : this.employees) {
            employees.add(jsonName.toModelType());
        }
        return new Department(new DepartmentName(this.name), employees);
    }

}
