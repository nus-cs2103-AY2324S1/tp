package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Role;
import seedu.address.model.employee.Salary;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;

/**
 * Jackson-friendly version of {@link Employee}.
 */
class JsonAdaptedEmployee {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Employee's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String salary;
    private final String leave;
    private final String role;
    private final List<JsonAdaptedSupervisor> supervisors = new ArrayList<>();
    private final List<JsonAdaptedDepartmentName> departments = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEmployee} with the given employee details.
     */
    @JsonCreator
    public JsonAdaptedEmployee(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                               @JsonProperty("email") String email, @JsonProperty("address") String address,
                               @JsonProperty("salary") String salary, @JsonProperty("leave") String leave,
                               @JsonProperty("role") String role,
                               @JsonProperty("supervisors") List<JsonAdaptedSupervisor> supervisors,
                               @JsonProperty("departments") List<JsonAdaptedDepartmentName> departments) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.leave = leave;
        this.role = role;
        if (supervisors != null) {
            this.supervisors.addAll(supervisors);
        }
        if (departments != null) {
            this.departments.addAll(departments);
        }
    }

    /**
     * Converts a given {@code Employee} into this class for Jackson use.
     */
    public JsonAdaptedEmployee(Employee source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        salary = source.getSalary().value;
        leave = source.getLeave().value;
        role = source.getRole().toString();
        supervisors.addAll(source.getSupervisors().stream()
                .map(JsonAdaptedSupervisor::new)
                .collect(Collectors.toList()));
        departments.addAll(source.getDepartments().stream()
                .map(JsonAdaptedDepartmentName::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted employee object into the model's {@code Employee} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted employee.
     */
    public Employee toModelType() throws IllegalValueException {
        final List<DepartmentName> employeeDepartments = new ArrayList<>();
        for (JsonAdaptedDepartmentName department : departments) {
            employeeDepartments.add(department.toModelType());
        }
        final List<EmployeeName> managers = new ArrayList<>();
        for (JsonAdaptedSupervisor supervisor : supervisors) {
            managers.add(supervisor.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, EmployeeName.class.getSimpleName()));
        }
        if (!EmployeeName.isValidName(name)) {
            throw new IllegalValueException(EmployeeName.MESSAGE_CONSTRAINTS);
        }
        final EmployeeName modelName = new EmployeeName(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);

        if (leave == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Leave.class.getSimpleName()));
        }
        if (!Leave.isValidLeave(leave)) {
            throw new IllegalValueException(Leave.MESSAGE_CONSTRAINTS);
        }
        final Leave modelLeave = new Leave(leave);

        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        final Role modelRole = new Role(role);
        final Set<EmployeeName> modelSupervisors = new HashSet<>(managers);
        final Set<DepartmentName> modelDepartments = new HashSet<>(employeeDepartments);
        return new Employee(modelName, modelPhone, modelEmail, modelAddress, modelSalary, modelLeave,
                 modelRole, modelSupervisors, modelDepartments);
    }

}
