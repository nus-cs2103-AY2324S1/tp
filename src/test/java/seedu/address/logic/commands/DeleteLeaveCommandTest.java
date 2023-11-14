package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_IT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OVERTIME_HOURS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARKLIST_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteLeaveCommand.MESSAGE_NO_LEAVES_FOUND;
import static seedu.address.logic.commands.DeleteLeaveCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalEmployees.BOB;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;

import java.time.LocalDate;

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

class DeleteLeaveCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_invalidId_failure() {
        LocalDate leaveDate = BOB.getLeaveList().getLeave(0).leaveDate;
        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(new Id("EID0000-0000"), leaveDate, leaveDate);
        assertCommandFailure(deleteLeaveCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    @Test
    void execute_leavesExist_success() {
        LocalDate leaveDate = BOB.getLeaveList().getLeave(0).leaveDate;
        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(BOB.getId(), leaveDate, leaveDate);
        LeaveList editedLeaveList = new LeaveList(BOB.getLeaveList().getCopiedLeaveList().leaveList);
        editedLeaveList.deleteLeave(new Leave(leaveDate));
        Employee editedEmployee = new EmployeeBuilder().withName(BOB.getName().fullName)
                .withPosition(BOB.getPosition().value)
                .withId(BOB.getId().value)
                .withPhone(BOB.getPhone().value)
                .withEmail(BOB.getEmail().value)
                .withDepartments(VALID_DEPARTMENT_FINANCE, VALID_DEPARTMENT_IT)
                .withSalary(BOB.getSalary().value)
                .withOvertimeHours(BOB.getOvertimeHours().value)
                .withLeaveList(editedLeaveList.leaveList)
                .withRemarkList(BOB.getRemarkList().remarkList)
                .build();
        String expectedMessage = String.format(MESSAGE_SUCCESS, Messages.formatLeaves(editedEmployee));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        model.addEmployee(BOB);
        expectedModel.addEmployee(editedEmployee);

        assertCommandSuccess(deleteLeaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_atLeastOneLeaveExists_success() {
        LocalDate startLeave = BOB.getLeaveList().getLeave(0).leaveDate;
        LocalDate endLeave = startLeave.plusDays(14);
        // leave period to delete exceeds maximum number of leaves
        // means at least one date in leave period is not in employee's leave list
        DeleteLeaveCommand command = new DeleteLeaveCommand(BOB.getId(), startLeave, endLeave);
        LeaveList editedLeaveList = new LeaveList();
        editedLeaveList.deleteLeave(new Leave(startLeave)); // since BOB only has one leave
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

        String expectedMessage = String.format(MESSAGE_SUCCESS, Messages.formatLeaves(editedEmployee));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        model.addEmployee(BOB);
        expectedModel.addEmployee(editedEmployee);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_leavesNotExist_failure() {
        LocalDate leaveDate = BOB.getLeaveList().getLeave(0).leaveDate.plusDays(2);
        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(BOB.getId(), leaveDate, leaveDate);

        model.addEmployee(BOB);

        assertCommandFailure(deleteLeaveCommand, model, MESSAGE_NO_LEAVES_FOUND);
    }


    @Test
    public void equals_test() {
        LocalDate leaveDate = BOB.getLeaveList().getLeave(0).leaveDate.plusDays(2);

        DeleteLeaveCommand firstCommand = new DeleteLeaveCommand(BOB.getId(), leaveDate, leaveDate);
        DeleteLeaveCommand secondCommand = new DeleteLeaveCommand(BOB.getId(), leaveDate,
                leaveDate.plusDays(2));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DeleteLeaveCommand copyCommand = new DeleteLeaveCommand(BOB.getId(), leaveDate, leaveDate);
        assertTrue(firstCommand.equals(copyCommand));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different employee -> returns false
        assertFalse(firstCommand.equals(secondCommand));

    }
}
