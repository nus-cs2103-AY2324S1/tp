package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_STALLS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStalls.AUNTIES_COOKING;
import static seedu.address.testutil.TypicalStalls.DRINKS_STALL;
import static seedu.address.testutil.TypicalStalls.ECONOMIC_RICE;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindStallCommand}.
 */
public class FindStallCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindStallCommand findFirstCommand = new FindStallCommand(firstPredicate);
        FindStallCommand findSecondCommand = new FindStallCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStallCommand findFirstCommandCopy = new FindStallCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different stall -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStallFound() {
        // EP: No keywords
        String expectedMessage = String.format(MESSAGE_STALLS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindStallCommand command = new FindStallCommand(predicate);
        expectedModel.updateFilteredStallList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStallList());
    }

    @Test
    public void execute_multipleKeywords_multipleStallsFound() {
        // EP: Multiple keywords
        String expectedMessage = String.format(MESSAGE_STALLS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Rice Drinks Cooking");
        FindStallCommand command = new FindStallCommand(predicate);
        expectedModel.updateFilteredStallList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AUNTIES_COOKING, DRINKS_STALL, ECONOMIC_RICE), model.getFilteredStallList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindStallCommand findStallCommand = new FindStallCommand(predicate);
        String expected = FindStallCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findStallCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
