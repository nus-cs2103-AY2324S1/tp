package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_IT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVELIST_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OVERTIME_HOURS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BOB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.remark.Remark;
import seedu.address.testutil.EmployeeBuilder;

public class EmployeeTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Employee employee = new EmployeeBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> employee.getDepartments().remove(0));
    }

    @Test
    public void isSameEmployee() {
        // same object -> returns true
        assertTrue(ALICE.isSameEmployee(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameEmployee(null));

        // same id, all other attributes different -> returns true
        Employee editedAlice = new EmployeeBuilder(ALICE).withName(VALID_NAME_BOB).withPosition(VALID_POSITION_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withDepartments(VALID_DEPARTMENT_IT).build();
        assertTrue(ALICE.isSameEmployee(editedAlice));

        // different id, all other attributes same -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.isSameEmployee(editedAlice));
    }

    @Test
    public void isOnLeaveToday_validEmployee_returnsTrue() {
        Employee employee = new EmployeeBuilder().withLeaveList(
            new ArrayList<Leave>(Arrays.asList(new Leave(LocalDate.now())))).build();
        assertTrue(employee.isOnLeaveToday());
    }

    @Test
    public void isOnLeaveToday_invalidEmployee_returnsFalse() {
        Employee employee = new EmployeeBuilder().withLeaveList(new ArrayList<Leave>()).build();
        assertFalse(employee.isOnLeaveToday());
    }

    @Test
    public void getNumOfLeaves_validEmployee_returnsNumOfLeaves() {
        Employee employee = new EmployeeBuilder().withLeaveList(new ArrayList<Leave>()).build();
        assertEquals(employee.getNumOfLeaves(), 0);
    }

    @Test
    public void getOvertimePay_validEmployee_returnsOvertimePay() {
        Employee employee = new EmployeeBuilder().withSalary("9000").withOvertimeHours(5).build();
        assertEquals(employee.getOvertimePay(), Math.round((5 * 9000 * 1.5 / (52 * 44) * 100)) / 100d);
    }

    @Test
    public void equals() {
        // same values -> returns true
        Employee aliceCopy = new EmployeeBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different employee -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Employee editedAlice = new EmployeeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different position -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withPosition(VALID_POSITION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different id -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different departments -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withDepartments(VALID_DEPARTMENT_IT).build();
        assertFalse(ALICE.equals(editedAlice));

        // different salary -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withSalary(VALID_SALARY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different isOnLeave -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withLeaveList(VALID_LEAVELIST_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different overtime hours left -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withOvertimeHours(VALID_OVERTIME_HOURS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different remark list -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withRemarkList(new ArrayList<>(List.of(new Remark("Remark")))).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Employee.class.getCanonicalName() + "{name=" + ALICE.getName()
                + ", position=" + ALICE.getPosition() + ", id=" + ALICE.getId() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", salary=" + ALICE.getSalary()
                + ", departments=" + ALICE.getDepartments() + ", overtimeHours=" + ALICE.getOvertimeHours()
                + ", leaves=" + ALICE.getLeaveList()
                + ", remarks=" + ALICE.getRemarkList() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
