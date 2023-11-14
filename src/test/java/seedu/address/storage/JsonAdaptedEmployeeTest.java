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
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.OvertimeHours;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Position;
import seedu.address.model.employee.Salary;

public class JsonAdaptedEmployeeTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_POSITION = "";
    private static final String INVALID_ID = "";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DEPARTMENT = "#friend";
    private static final String INVALID_SALARY = "$5000";
    private static final int INVALID_OVERTIME_HOURS = -1;
    private static final String INVALID_LEAVE_DATE = "32-12-2023";

    private static final String VALID_NAME = BENSON.getName().fullName;
    private static final String VALID_POSITION = BENSON.getPosition().value;
    private static final String VALID_ID = BENSON.getId().value;
    private static final String VALID_PHONE = BENSON.getPhone().value;
    private static final String VALID_EMAIL = BENSON.getEmail().value;
    private static final List<JsonAdaptedDepartment> VALID_DEPARTMENTS = BENSON.getDepartments().stream()
            .map(JsonAdaptedDepartment::new)
            .collect(Collectors.toList());
    private static final String VALID_SALARY = BENSON.getSalary().value;
    private static final int VALID_OVERTIME_HOURS = BENSON.getOvertimeHours().value;
    private static final List<JsonAdaptedLeave> VALID_LEAVELIST = BENSON.getLeaveList().leaveList.stream()
            .map(JsonAdaptedLeave::new)
            .collect(Collectors.toList());;
    private static final List<JsonAdaptedRemark> VALID_REMARKLIST = BENSON.getRemarkList().remarkList.stream()
            .map(JsonAdaptedRemark::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEmployeeDetails_returnsEmployee() throws Exception {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(BENSON);
        assertEquals(BENSON, employee.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(INVALID_NAME, VALID_POSITION, VALID_ID, VALID_PHONE,
                        VALID_EMAIL, VALID_DEPARTMENTS, VALID_SALARY, VALID_OVERTIME_HOURS,
                        VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(null, VALID_POSITION, VALID_ID, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENTS, VALID_SALARY, VALID_OVERTIME_HOURS, VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidPosition_throwsIllegalValueException() {
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(VALID_NAME, INVALID_POSITION, VALID_ID, VALID_PHONE,
                        VALID_EMAIL, VALID_DEPARTMENTS, VALID_SALARY, VALID_OVERTIME_HOURS,
                        VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = Position.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullPosition_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, null, VALID_ID, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENTS, VALID_SALARY, VALID_OVERTIME_HOURS, VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(VALID_NAME, VALID_POSITION, INVALID_ID, VALID_PHONE,
                        VALID_EMAIL, VALID_DEPARTMENTS, VALID_SALARY, VALID_OVERTIME_HOURS,
                        VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_POSITION, null, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENTS, VALID_SALARY, VALID_OVERTIME_HOURS, VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(VALID_NAME, VALID_POSITION, VALID_ID, INVALID_PHONE,
                        VALID_EMAIL, VALID_DEPARTMENTS, VALID_SALARY, VALID_OVERTIME_HOURS,
                        VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_POSITION, VALID_ID, null,
                VALID_EMAIL, VALID_DEPARTMENTS, VALID_SALARY, VALID_OVERTIME_HOURS, VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(VALID_NAME, VALID_POSITION, VALID_ID, VALID_PHONE,
                        INVALID_EMAIL, VALID_DEPARTMENTS, VALID_SALARY, VALID_OVERTIME_HOURS,
                        VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_POSITION, VALID_ID, VALID_PHONE,
                null, VALID_DEPARTMENTS, VALID_SALARY, VALID_OVERTIME_HOURS, VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidDepartments_throwsIllegalValueException() {
        List<JsonAdaptedDepartment> invalidDepartments = new ArrayList<>(VALID_DEPARTMENTS);
        invalidDepartments.add(new JsonAdaptedDepartment(INVALID_DEPARTMENT));
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(VALID_NAME, VALID_POSITION, VALID_ID, VALID_PHONE,
                        VALID_EMAIL, invalidDepartments, VALID_SALARY, VALID_OVERTIME_HOURS,
                        VALID_LEAVELIST, VALID_REMARKLIST);
        assertThrows(IllegalValueException.class, employee::toModelType);
    }

    @Test
    public void toModelType_invalidSalary_throwsIllegalValueException() {
        JsonAdaptedEmployee employee =
                new JsonAdaptedEmployee(VALID_NAME, VALID_POSITION, VALID_ID, VALID_PHONE,
                        VALID_EMAIL, VALID_DEPARTMENTS, INVALID_SALARY, VALID_OVERTIME_HOURS,
                        VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = Salary.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_nullSalary_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_POSITION, VALID_ID, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENTS, null, VALID_OVERTIME_HOURS, VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }

    @Test
    public void toModelType_invalidOvertimeHours_throwsIllegalValueException() {
        JsonAdaptedEmployee employee = new JsonAdaptedEmployee(VALID_NAME, VALID_POSITION, VALID_ID, VALID_PHONE,
                VALID_EMAIL, VALID_DEPARTMENTS, VALID_SALARY, INVALID_OVERTIME_HOURS,
                VALID_LEAVELIST, VALID_REMARKLIST);
        String expectedMessage = OvertimeHours.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, employee::toModelType);
    }
}
