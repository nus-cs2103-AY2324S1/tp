package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_STALLS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStalls.AUNTIES_COOKING;
import static seedu.address.testutil.TypicalStalls.BEVERAGES;
import static seedu.address.testutil.TypicalStalls.CHINESE;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.LocationContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindLocationCommand}.
 */
public class FindLocationCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        LocationContainsKeywordsPredicate firstPredicate =
                new LocationContainsKeywordsPredicate(Collections.singletonList("first"));
        LocationContainsKeywordsPredicate secondPredicate =
                new LocationContainsKeywordsPredicate(Collections.singletonList("second"));

        FindLocationCommand findFirstCommand = new FindLocationCommand(firstPredicate);
        FindLocationCommand findSecondCommand = new FindLocationCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindLocationCommand findFirstCommandCopy = new FindLocationCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different location -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStallFound() {
        // EP: No keywords
        String expectedMessage = String.format(MESSAGE_STALLS_LISTED_OVERVIEW, 0);
        LocationContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindLocationCommand command = new FindLocationCommand(predicate);
        expectedModel.updateFilteredStallList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStallList());
    }

    @Test
    public void execute_multipleKeywords_multipleStallsFound() {
        // EP: Multiple keywords
        String expectedMessage = String.format(MESSAGE_STALLS_LISTED_OVERVIEW, 3);
        LocationContainsKeywordsPredicate predicate = preparePredicate("deck wall");
        FindLocationCommand command = new FindLocationCommand(predicate);
        expectedModel.updateFilteredStallList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AUNTIES_COOKING, BEVERAGES, CHINESE), model.getFilteredStallList());
    }

    @Test
    public void toStringMethod() {
        LocationContainsKeywordsPredicate predicate = new LocationContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindLocationCommand findLocationCommand = new FindLocationCommand(predicate);
        String expected = FindLocationCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findLocationCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code LocationContainsKeywordsPredicate}.
     */
    private LocationContainsKeywordsPredicate preparePredicate(String userInput) {
        return new LocationContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
