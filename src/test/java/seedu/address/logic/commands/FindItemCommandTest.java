package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_STALLS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStalls.HOON;
import static seedu.address.testutil.TypicalStalls.IDA;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBookWithMenuAndReview;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.MenuContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindItemCommand}.
 */
public class FindItemCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithMenuAndReview(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithMenuAndReview(), new UserPrefs());

    @Test
    public void equals() {
        MenuContainsKeywordsPredicate firstPredicate =
                new MenuContainsKeywordsPredicate(Collections.singletonList("first"));
        MenuContainsKeywordsPredicate secondPredicate =
                new MenuContainsKeywordsPredicate(Collections.singletonList("second"));

        FindItemCommand findFirstCommand = new FindItemCommand(firstPredicate);
        FindItemCommand findSecondCommand = new FindItemCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindItemCommand findFirstCommandCopy = new FindItemCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different items -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStallFound() {
        // EP: No keywords
        String expectedMessage = String.format(MESSAGE_STALLS_LISTED_OVERVIEW, 0);
        MenuContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindItemCommand command = new FindItemCommand(predicate);
        expectedModel.updateFilteredStallList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStallList());
    }

    @Test
    public void execute_multipleKeywords_multipleStallsFound() {
        // EP: Multiple keywords
        String expectedMessage = String.format(MESSAGE_STALLS_LISTED_OVERVIEW, 2);
        MenuContainsKeywordsPredicate predicate = preparePredicate("chicken laksa");
        FindItemCommand command = new FindItemCommand(predicate);
        expectedModel.updateFilteredStallList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HOON, IDA), model.getFilteredStallList());
    }

    @Test
    public void toStringMethod() {
        MenuContainsKeywordsPredicate predicate = new MenuContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindItemCommand findItemCommand = new FindItemCommand(predicate);
        String expected = FindItemCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findItemCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code MenuContainsKeywordsPredicate}.
     */
    private MenuContainsKeywordsPredicate preparePredicate(String userInput) {
        return new MenuContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
