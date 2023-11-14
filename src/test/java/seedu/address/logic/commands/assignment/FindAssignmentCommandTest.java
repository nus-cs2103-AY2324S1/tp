package seedu.address.logic.commands.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT3;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT4;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.AssignmentNameContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindAssignmentCommand}.
 */
public class FindAssignmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        AssignmentNameContainsKeywordsPredicate firstPredicate =
                new AssignmentNameContainsKeywordsPredicate(Collections.singletonList("first"));
        AssignmentNameContainsKeywordsPredicate secondPredicate =
                new AssignmentNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindAssignmentCommand findFirstCommand = new FindAssignmentCommand(firstPredicate);
        FindAssignmentCommand findSecondCommand = new FindAssignmentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAssignmentCommand findFirstCommandCopy = new FindAssignmentCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noAssignmentsFound() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, 0);
        AssignmentNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindAssignmentCommand command = new FindAssignmentCommand(predicate);
        expectedModel.updateFilteredAssignmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAssignmentList());
    }

    @Test
    public void execute_multipleKeywords_multipleAssignmentsFound() {
        String expectedMessage = String.format(MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, 2);
        AssignmentNameContainsKeywordsPredicate predicate = preparePredicate("CS2105");
        FindAssignmentCommand command = new FindAssignmentCommand(predicate);
        expectedModel.updateFilteredAssignmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ASSIGNMENT3, ASSIGNMENT4), model.getFilteredAssignmentList());
    }

    @Test
    public void toStringMethod() {
        AssignmentNameContainsKeywordsPredicate predicate =
                new AssignmentNameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindAssignmentCommand findCommand = new FindAssignmentCommand(predicate);
        String expected = FindAssignmentCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code AssignmentNameContainsKeywordsPredicate}.
     */
    private AssignmentNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new AssignmentNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
