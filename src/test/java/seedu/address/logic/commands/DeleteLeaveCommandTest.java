package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_IT;
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
