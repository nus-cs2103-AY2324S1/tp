package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_EMPLOYEES_ON_LEAVE_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.BOB;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code ListLeaveCommand}.
 */
public class ListLeaveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        LocalDate firstDate = LocalDate.parse("2023-11-11", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate secondDate = LocalDate.parse("2023-12-31", DateTimeFormatter.ISO_LOCAL_DATE);

        ListLeaveCommand firstListLeaveCommand = new ListLeaveCommand(firstDate);
        ListLeaveCommand secondListLeaveCommand = new ListLeaveCommand(secondDate);

        // same object -> returns true
        assertTrue(firstListLeaveCommand.equals(firstListLeaveCommand));

        // same values -> returns true
        ListLeaveCommand firstListLeaveCommandCopy = new ListLeaveCommand(firstDate);
        assertTrue(firstListLeaveCommand.equals(firstListLeaveCommandCopy));

        // different types -> returns false
        assertFalse(firstListLeaveCommand.equals(1));

        // null -> returns false
        assertFalse(firstListLeaveCommand.equals(null));

        // different employee -> returns false
        assertFalse(firstListLeaveCommand.equals(secondListLeaveCommand));
    }

    @Test
    public void execute_noEmployeeOnLeave_noEmployeeListed() {
        LocalDate date = LocalDate.parse("2023-11-11", DateTimeFormatter.ISO_LOCAL_DATE);
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_ON_LEAVE_OVERVIEW, 0, date);
        ListLeaveCommand command = new ListLeaveCommand(date);
        expectedModel.updateFilteredEmployeeList(employee -> employee.isOnLeave(date));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());
    }

    @Test
    public void execute_employeeOnLeave_employeeListed() {
        LocalDate date = LocalDate.parse("2023-11-01", DateTimeFormatter.ISO_LOCAL_DATE);
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_ON_LEAVE_OVERVIEW, 1, date);
        expectedModel.addEmployee(BOB);
        model.addEmployee(BOB);
        ListLeaveCommand command = new ListLeaveCommand(date);
        expectedModel.updateFilteredEmployeeList(employee -> employee.isOnLeave(date));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BOB), model.getFilteredEmployeeList());
    }

    @Test
    public void toStringMethod() {
        LocalDate date = LocalDate.parse("2023-11-11", DateTimeFormatter.ISO_LOCAL_DATE);
        ListLeaveCommand listLeaveCommand = new ListLeaveCommand(date);
        String expected = ListLeaveCommand.class.getCanonicalName() + "{date=" + date + "}";
        assertEquals(expected, listLeaveCommand.toString());
    }

}
