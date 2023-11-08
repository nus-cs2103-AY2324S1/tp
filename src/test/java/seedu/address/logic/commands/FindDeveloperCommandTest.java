package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDevelopers.CARL;
import static seedu.address.testutil.TypicalDevelopers.ELLE;
import static seedu.address.testutil.TypicalDevelopers.FIONA;
import static seedu.address.testutil.TypicalDevelopers.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindDeveloperCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.developer.NameDeveloperContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindDeveloperCommand}.
 */
public class FindDeveloperCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameDeveloperContainsKeywordsPredicate firstPredicate =
                new NameDeveloperContainsKeywordsPredicate(Collections.singletonList("first"));
        NameDeveloperContainsKeywordsPredicate secondPredicate =
                new NameDeveloperContainsKeywordsPredicate(Collections.singletonList("second"));

        FindDeveloperCommand findFirstCommand = new FindDeveloperCommand(firstPredicate);
        FindDeveloperCommand findSecondCommand = new FindDeveloperCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDeveloperCommand findFirstCommandCopy = new FindDeveloperCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different developer -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        //String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        String expectedMessage = "There are the 0 developers with matching information.";
        NameDeveloperContainsKeywordsPredicate predicate = preparePredicate("hii");
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDeveloperList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        //String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        String expectedMessage = "There are the 3 developers with matching information.";
        NameDeveloperContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredDeveloperList());
    }

    @Test
    public void toStringMethod() {
        NameDeveloperContainsKeywordsPredicate predicate = new NameDeveloperContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindDeveloperCommand findDeveloperCommand = new FindDeveloperCommand(predicate);
        String expected = FindDeveloperCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findDeveloperCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameDeveloperContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameDeveloperContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
