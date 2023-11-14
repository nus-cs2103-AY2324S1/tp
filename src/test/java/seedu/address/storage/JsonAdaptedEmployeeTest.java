package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEmployee.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Role;
import seedu.address.model.employee.Salary;
import seedu.address.model.name.EmployeeName;

public class JsonAdaptedEmployeeTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_SALARY = "11DD";
    private static final String INVALID_LEAVE = "3day";
    private static final String INVALID_ROLE = "sub";
    private static final String INVALID_MANAGER = "B@t";
    private static final String INVALID_DEPARTMENT = "#2business";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_SALARY = BENSON.getSalary().toString();
    private static final String VALID_LEAVE = BENSON.getLeave().toString();
    private static final String VALID_ROLE = BENSON.getRole().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedSupervisor> VALID_MANAGERS = BENSON.getSupervisors().stream()
            .map(JsonAdaptedSupervisor::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedDepartmentName> VALID_DEPARTMENTS = BENSON.getDepartments().stream()
            .map(JsonAdaptedDepartmentName::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEmployeeDetails_returnsEmployee() throws Exception {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(BENSON);
        assertEquals(BENSON, employee.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_SALARY, VALID_LEAVE, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = EmployeeName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(null, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_SALARY, VALID_LEAVE, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EmployeeName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_SALARY, VALID_LEAVE, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_SALARY, VALID_LEAVE, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_SALARY, VALID_LEAVE, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_SALARY, VALID_LEAVE, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_SALARY, VALID_LEAVE, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_SALARY, VALID_LEAVE, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }
    @Test
    public void toModelType_invalidSalary_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                INVALID_SALARY, VALID_LEAVE, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = Salary.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullSalary_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_LEAVE, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidLeave_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SALARY, INVALID_LEAVE, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = Leave.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullLeave_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SALARY, null, VALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Leave.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SALARY, VALID_LEAVE, INVALID_ROLE, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SALARY, VALID_LEAVE, null, VALID_MANAGERS, VALID_DEPARTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidSupervisors_throwsIllegalValueException() {
        List<JsonAdaptedSupervisor> invalidSupervisors = new ArrayList<>(VALID_MANAGERS);
        invalidSupervisors.add(new JsonAdaptedSupervisor(INVALID_MANAGER));
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_SALARY, VALID_LEAVE, VALID_ROLE, invalidSupervisors, VALID_DEPARTMENTS);
        assertThrows(IllegalValueException.class, employee::toModelType);
    }

    @Test
    public void toModelType_invalidDepartments_throwsIllegalValueException() {
        List<JsonAdaptedDepartmentName> invalidDepartments = new ArrayList<>(VALID_DEPARTMENTS);
        invalidDepartments.add(new JsonAdaptedDepartmentName(INVALID_DEPARTMENT));
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_SALARY, VALID_LEAVE, VALID_ROLE, VALID_MANAGERS, invalidDepartments);
        assertThrows(IllegalValueException.class, employee::toModelType);
    }

}
