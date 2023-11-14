package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.RoleContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCourseCommand}.
 */
public class FindRoleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        RoleContainsKeywordsPredicate firstPredicate =
                new RoleContainsKeywordsPredicate(Collections.singletonList("first"));
        RoleContainsKeywordsPredicate secondPredicate =
                new RoleContainsKeywordsPredicate(Collections.singletonList("second"));

        FindRoleCommand findFirstCommand = new FindRoleCommand(firstPredicate);
        FindRoleCommand findSecondCommand = new FindRoleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindRoleCommand findFirstCommandCopy = new FindRoleCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCourseFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        RoleContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindRoleCommand command = new FindRoleCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /*
    @Test
    public void execute_multipleKeywords_multipleCoursesFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        RoleContainsKeywordsPredicate predicate = preparePredicate("[Professor]");
        FindRoleCommand command = new FindRoleCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, GEORGE), model.getFilteredPersonList());
    }
    */

    @Test
    public void toStringMethod() {
        RoleContainsKeywordsPredicate predicate = new RoleContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindRoleCommand findCommand = new FindRoleCommand(predicate);
        String expected = FindRoleCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private RoleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new RoleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

