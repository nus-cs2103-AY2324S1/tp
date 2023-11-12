package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_IT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEAVELIST_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OVERTIME_HOURS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARKLIST_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.BOB;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.LeaveList;
import seedu.address.testutil.EmployeeBuilder;

public class EditLeaveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_oldLeaveExist_success() {
        LocalDate oldLeaveDate = BOB.getLeaveList().getLeave(0).leaveDate;
        LocalDate newLeaveDate = LocalDate.of(2023, 12, 12);
        EditLeaveCommand editLeaveCommand = new EditLeaveCommand(BOB.getId(), oldLeaveDate, newLeaveDate);
        LeaveList editedLeaveList = new LeaveList();
        editedLeaveList.addLeave(new Leave(newLeaveDate)); // since bob only has one leave
        Employee editedEmployee = new EmployeeBuilder().withName(VALID_NAME_BOB)
                .withPosition(VALID_POSITION_BOB)
                .withId(VALID_ID_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withDepartments(VALID_DEPARTMENT_FINANCE, VALID_DEPARTMENT_IT)
                .withSalary(VALID_SALARY_BOB)
                .withOvertimeHours(VALID_OVERTIME_HOURS_BOB)
                .withLeaveList(editedLeaveList.leaveList)
                .withRemarkList(VALID_REMARKLIST_BOB)
                .build();
        String expectedMessage = String.format(EditLeaveCommand.MESSAGE_SUCCESS, oldLeaveDate, newLeaveDate,
                Messages.formatLeaves(editedEmployee));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        model.addEmployee(BOB);
        expectedModel.addEmployee(editedEmployee);

        assertCommandSuccess(editLeaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidId_failure() {
        LocalDate oldLeaveDate = BOB.getLeaveList().getLeave(0).leaveDate;
        LocalDate newLeaveDate = LocalDate.of(2023, 12, 12);
        EditLeaveCommand editLeaveCommand = new EditLeaveCommand(new Id("EID0000-0000"), oldLeaveDate, newLeaveDate);
        assertCommandFailure(editLeaveCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    @Test
    void execute_oldLeaveNotExist_failure() {
        LocalDate oldLeaveDate = BOB.getLeaveList().getLeave(0).leaveDate.plusDays(2);
        LocalDate newLeaveDate = LocalDate.of(2023, 12, 12);
        EditLeaveCommand editLeaveCommand = new EditLeaveCommand(BOB.getId(), oldLeaveDate, newLeaveDate);
        model.addEmployee(BOB);
        assertCommandFailure(editLeaveCommand, model, EditLeaveCommand.MESSAGE_NON_EXISTENT_LEAVE);
    }

    @Test
    void execute_newLeaveExist_failure() {
        VALID_LEAVELIST_BOB.add(new Leave(LocalDate.parse("2023-10-31", DateTimeFormatter.ISO_LOCAL_DATE)));
        LocalDate oldLeaveDate = BOB.getLeaveList().getLeave(0).leaveDate;
        LocalDate newLeaveDate = BOB.getLeaveList().getLeave(1).leaveDate;
        EditLeaveCommand editLeaveCommand = new EditLeaveCommand(BOB.getId(), oldLeaveDate, newLeaveDate);
        model.addEmployee(BOB);
        assertCommandFailure(editLeaveCommand, model, EditLeaveCommand.MESSAGE_DUPLICATE_LEAVE);
    }

    @Test
    void execute_oldLeaveNewLeaveSame_failure() {
        // also tests for if new leave already exists (since to reach this stage, old leave has to exist)
        LocalDate oldLeaveDate = BOB.getLeaveList().getLeave(0).leaveDate;
        EditLeaveCommand editLeaveCommand = new EditLeaveCommand(BOB.getId(), oldLeaveDate, oldLeaveDate);
        model.addEmployee(BOB);
        assertCommandFailure(editLeaveCommand, model, EditLeaveCommand.MESSAGE_DUPLICATE_LEAVE);
    }

    @Test
    public void equals() {
        LocalDate oldLeaveDate = BOB.getLeaveList().getLeave(0).leaveDate;

        EditLeaveCommand firstCommand = new EditLeaveCommand(BOB.getId(), oldLeaveDate,
                oldLeaveDate.plusDays(2));
        EditLeaveCommand secondCommand = new EditLeaveCommand(BOB.getId(), oldLeaveDate,
                oldLeaveDate.plusDays(5));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        EditLeaveCommand copyCommand = new EditLeaveCommand(BOB.getId(), oldLeaveDate,
                oldLeaveDate.plusDays(2));
        assertTrue(firstCommand.equals(copyCommand));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different values -> return false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
