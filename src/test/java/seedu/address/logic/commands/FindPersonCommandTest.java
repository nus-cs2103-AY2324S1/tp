package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_PERSON_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonNameOrGroupContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class FindPersonCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonNameOrGroupContainsKeywordsPredicate firstPredicate =
                new PersonNameOrGroupContainsKeywordsPredicate(Collections.singletonList("first"));
        PersonNameOrGroupContainsKeywordsPredicate secondPredicate =
                new PersonNameOrGroupContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPersonCommand findPersonFirstCommand = new FindPersonCommand(firstPredicate);
        FindPersonCommand findPersonSecondCommand = new FindPersonCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findPersonFirstCommand.equals(findPersonFirstCommand));

        // same values -> returns true
        FindPersonCommand findFirstCommandCopy = new FindPersonCommand(firstPredicate);
        assertTrue(findPersonFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findPersonFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findPersonFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findPersonFirstCommand.equals(findPersonSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonNameOrGroupContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        // Name only
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonNameOrGroupContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());

        // Group only
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        predicate = preparePredicate("friends family");
        command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL), model.getFilteredPersonList());

        // Name and group
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        predicate = preparePredicate("Pauline family Best");
        command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSON_LISTED_OVERVIEW, 1);
        PersonNameOrGroupContainsKeywordsPredicate predicate = preparePredicate("Kurz family");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        PersonNameOrGroupContainsKeywordsPredicate predicate =
                new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindPersonCommand findPersonCommand = new FindPersonCommand(predicate);
        String expected = FindPersonCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findPersonCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PersonNameOrGroupContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
