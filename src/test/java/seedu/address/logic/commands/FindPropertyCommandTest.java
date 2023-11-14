package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandPropertyTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProperties.AQUAVISTA;
import static seedu.address.testutil.TypicalProperties.HORIZONVIEW;
import static seedu.address.testutil.TypicalProperties.SKYVISTA;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.PropNameContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindPropertyCommand}.
 */
public class FindPropertyCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void equals() {
        PropNameContainsKeywordsPredicate firstPredicate =
                new PropNameContainsKeywordsPredicate(Collections.singletonList("first"));
        PropNameContainsKeywordsPredicate secondPredicate =
                new PropNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPropertyCommand findFirstCommand = new FindPropertyCommand(firstPredicate);
        FindPropertyCommand findSecondCommand = new FindPropertyCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPropertyCommand findFirstCommandCopy = new FindPropertyCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPropertyFound() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 0);
        PropNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void execute_multipleKeywords_multiplePropertiesFound() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 3);
        PropNameContainsKeywordsPredicate predicate = preparePredicate("Aquavi Skyvista Horizonview");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AQUAVISTA, SKYVISTA, HORIZONVIEW), model.getFilteredPropertyList());
    }

    @Test
    public void toStringMethod() {
        PropNameContainsKeywordsPredicate predicate = new PropNameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindPropertyCommand findCommand = new FindPropertyCommand(predicate);
        String expected = FindPropertyCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code PropNameContainsKeywordsPredicate}.
     */
    private PropNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PropNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
