package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ManageHr;
import seedu.address.model.ReadOnlyManageHr;
import seedu.address.model.employee.Employee;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "managehr")
class JsonSerializableManageHr {

    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "Employees list contains duplicate employee(s).";

    private final List<JsonAdaptedEmployee> employees = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableManageHR} with the given employees.
     */
    @JsonCreator
    public JsonSerializableManageHr(@JsonProperty("employees") List<JsonAdaptedEmployee> employees) {
        this.employees.addAll(employees);
    }

    /**
     * Converts a given {@code ReadOnlyManageHR} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableManageHR}.
     */
    public JsonSerializableManageHr(ReadOnlyManageHr source) {
        employees.addAll(source.getEmployeeList().stream().map(JsonAdaptedEmployee::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ManageHR} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ManageHr toModelType() throws IllegalValueException {
        ManageHr manageHR = new ManageHr();
        for (JsonAdaptedEmployee jsonAdaptedEmployee : employees) {
            Employee employee = jsonAdaptedEmployee.toModelType();
            if (manageHR.hasEmployee(employee)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EMPLOYEE);
            }
            manageHR.addEmployee(employee);
        }
        return manageHR;
    }

}
