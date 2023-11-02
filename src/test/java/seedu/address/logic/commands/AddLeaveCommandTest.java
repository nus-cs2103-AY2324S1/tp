package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.BOB;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.Leave;
import seedu.address.testutil.EmployeeBuilder;

public class AddLeaveCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validIdValidDates_success() {
        LocalDate startDate = LocalDate.of(2023, 11, 13);
        LocalDate endDate = LocalDate.of(2023, 11, 15);
        Employee employeeToAddLeave = model.getAddressBook().getEmployeeList()
                .get(INDEX_FIRST_EMPLOYEE.getZeroBased());

        AddLeaveCommand command = new AddLeaveCommand(employeeToAddLeave.getId(), startDate, endDate);

        ArrayList<Leave> expectedList = new ArrayList<>();
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        for (int i = 0; i < numOfDays; i++) {
            expectedList.add(new Leave(startDate.plusDays(i)));
        }

        Employee expectedEmployeeToAddLeave =
                new EmployeeBuilder(employeeToAddLeave).withLeaveList(expectedList).build();
        expectedModel.setEmployee(expectedModel.getAddressBook().getEmployeeList().get(0), expectedEmployeeToAddLeave);
        String expectedMessage = String.format(AddLeaveCommand.MESSAGE_SUCCESS,
                Messages.formatLeaves(expectedEmployeeToAddLeave));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdValidDates_failure() {
        LocalDate startDate = LocalDate.of(2023, 11, 13);
        LocalDate endDate = LocalDate.of(2023, 11, 15);
        Id invalidId = new Id("EID5555-5555"); // no employee has this Id

        AddLeaveCommand command = new AddLeaveCommand(invalidId, startDate, endDate);
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE);

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_addLeaveInEmptyEmployeeList_failure() {
        Model model = new ModelManager();
        LocalDate startDate = LocalDate.of(2023, 11, 13);
        LocalDate endDate = LocalDate.of(2023, 11, 15);
        Id validId = new Id(VALID_ID_AMY);
        AddLeaveCommand command = new AddLeaveCommand(validId, startDate, endDate);
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE);

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_notEnoughLeaves_failure() {
        LocalDate startDate = LocalDate.of(2023, 11, 13);
        LocalDate endDate = LocalDate.of(2023, 11, 29);
        Employee employeeToAddLeave = model.getAddressBook().getEmployeeList()
                .get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        AddLeaveCommand command = new AddLeaveCommand(employeeToAddLeave.getId(), startDate, endDate);

        assertCommandFailure(command, model, AddLeaveCommand.MESSAGE_NOT_ENOUGH_LEAVES);
    }

    @Test
    public void execute_addDuplicateLeaves_failure() {
        LocalDate startDate = LocalDate.of(2023, 11, 1);
        LocalDate endDate = LocalDate.of(2023, 11, 3);

        model.addEmployee(BOB);
        AddLeaveCommand command = new AddLeaveCommand(BOB.getId(), startDate, endDate);

        assertCommandFailure(command, model, AddLeaveCommand.MESSAGE_DUPLICATE_LEAVE);
    }

    @Test
    public void equals() {

        LocalDate leaveDate = BOB.getLeaveList().getLeave(0).leaveDate.plusDays(2);

        AddLeaveCommand firstCommand = new AddLeaveCommand(BOB.getId(), leaveDate, leaveDate);
        AddLeaveCommand secondCommand = new AddLeaveCommand(BOB.getId(), leaveDate,
                leaveDate.plusDays(1));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        AddLeaveCommand firstCommandCopy = new AddLeaveCommand(BOB.getId(), leaveDate, leaveDate);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommandCopy.equals(1));

        // null -> returns false
        assertFalse(firstCommandCopy.equals(null));

        // different employee -> returns false
        assertFalse(firstCommandCopy.equals(secondCommand));
    }


}
