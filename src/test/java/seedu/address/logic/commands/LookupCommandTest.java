package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code LookupCommand}.
 */
public class LookupCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate("T11", null,
                        TypicalPersons.KEYWORD_MATCHING_MEIER, null, null, null);
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate("T10", null,
                        null, null, null, null);

        LookupCommand lookupFirstCommand = new LookupCommand(firstPredicate);
        LookupCommand lookupSecondCommand = new LookupCommand(secondPredicate);

        // same object -> returns true
        assertTrue(lookupFirstCommand.equals(lookupFirstCommand));
        assertTrue(lookupSecondCommand.equals(lookupSecondCommand));

        // same values -> returns true
        LookupCommand lookupFirstCommandCopy = new LookupCommand(firstPredicate);
        LookupCommand lookupSecondCommandCopy = new LookupCommand(secondPredicate);
        assertTrue(lookupFirstCommand.equals(lookupFirstCommandCopy));
        assertTrue(lookupSecondCommand.equals(lookupSecondCommandCopy));

        // different types -> returns false
        assertFalse(lookupFirstCommand.equals(1));
        assertFalse(lookupSecondCommand.equals(1));

        // null -> returns false
        assertFalse(lookupFirstCommand.equals(null));
        assertFalse(lookupSecondCommand.equals(null));

        // different person -> returns false
        assertFalse(lookupFirstCommand.equals(lookupSecondCommand));
        assertFalse(lookupSecondCommand.equals(lookupFirstCommand));
    }

    @Test
    public void execute_zeroKeywords_allPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW,
                expectedModel.getFilteredPersonList().size());
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(null,
                null, null, null, null, null);
        LookupCommand command = new LookupCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleKeywords_multiplePersonsFound() {
        expectedModel.addPerson(HOON);
        model.addPerson(HOON);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(null,
                null, TypicalPersons.KEYWORD_MATCHING_MEIER, null, null, null);
        LookupCommand command = new LookupCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, HOON), model.getFilteredPersonList());
        expectedModel.deletePerson(HOON);
        model.deletePerson(HOON);
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(null,
                null, TypicalPersons.KEYWORD_MATCHING_MEIER, null, null, "friends");
        LookupCommand command = new LookupCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentKeywords_noPersonsFound() {
        // single keyword
        String expectedMessage = LookupCommand.MESSAGE_NO_MATCH;
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate("T9999",
                null, null, null, null, null);
        LookupCommand command = new LookupCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());

        // multiple keywords
        predicate = new PersonContainsKeywordsPredicate("T11",
                null, "thisIsATestName", null, null, "tagTagTag");
        command = new LookupCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate("T11",
                null, TypicalPersons.KEYWORD_MATCHING_MEIER, null, null, null);
        LookupCommand lookupCommand = new LookupCommand(predicate);
        String expected = LookupCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, lookupCommand.toString());
    }
}
