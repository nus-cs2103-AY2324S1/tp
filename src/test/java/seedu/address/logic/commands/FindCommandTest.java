package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BENSON;
import static seedu.address.testutil.TypicalEmployees.CARL;
import static seedu.address.testutil.TypicalEmployees.DANIEL;
import static seedu.address.testutil.TypicalEmployees.ELLE;
import static seedu.address.testutil.TypicalEmployees.FIONA;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.EmployeeContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        EmployeeContainsKeywordsPredicate firstPredicate =
                new EmployeeContainsKeywordsPredicate(Collections.singletonList("first"));
        EmployeeContainsKeywordsPredicate secondPredicate =
                new EmployeeContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEmployeeFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 0);
        EmployeeContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());
    }

    @Test
    public void execute_multipleKeywords_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 6);
        EmployeeContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz manager IT");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA), model.getFilteredEmployeeList());
    }

    @Test
    public void toStringMethod() {
        EmployeeContainsKeywordsPredicate predicate = new EmployeeContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into an {@code EmployeeContainsKeywordsPredicate}.
     */
    private EmployeeContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EmployeeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
