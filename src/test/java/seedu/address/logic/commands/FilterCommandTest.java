package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BENSON;
import static seedu.address.testutil.TypicalEmployees.DANIEL;
import static seedu.address.testutil.TypicalEmployees.getTypicalManageHr;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.ContainsDepartmentPredicate;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalManageHr(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalManageHr(), new UserPrefs());

    @Test
    public void execute_noEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 0);
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate(" ");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());
    }

    @Test
    public void execute_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 3);
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate("investment");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredEmployeeList());

        expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 1);
        predicate = new ContainsDepartmentPredicate("logistics");
        command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredEmployeeList());
    }

    @Test
    public void equals() {
        ContainsDepartmentPredicate firstPredicate = new ContainsDepartmentPredicate("first");
        ContainsDepartmentPredicate secondPredicate = new ContainsDepartmentPredicate("second");

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand findFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void toStringMethod() {
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate("keyword");
        FilterCommand findCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }
}
