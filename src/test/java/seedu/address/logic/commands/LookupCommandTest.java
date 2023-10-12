package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
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

        LookupCommand LookupFirstCommand = new LookupCommand(firstPredicate);
        LookupCommand LookupSecondCommand = new LookupCommand(secondPredicate);

        // same object -> returns true
        assertTrue(LookupFirstCommand.equals(LookupFirstCommand));
        assertTrue(LookupSecondCommand.equals(LookupSecondCommand));

        // same values -> returns true
        LookupCommand LookupFirstCommandCopy = new LookupCommand(firstPredicate);
        LookupCommand LookupSecondCommandCopy = new LookupCommand(secondPredicate);
        assertTrue(LookupFirstCommand.equals(LookupFirstCommandCopy));
        assertTrue(LookupSecondCommand.equals(LookupSecondCommandCopy));

        // different types -> returns false
        assertFalse(LookupFirstCommand.equals(1));
        assertFalse(LookupSecondCommand.equals(1));

        // null -> returns false
        assertFalse(LookupFirstCommand.equals(null));
        assertFalse(LookupSecondCommand.equals(null));

        // different person -> returns false
        assertFalse(LookupFirstCommand.equals(LookupSecondCommand));
        assertFalse(LookupSecondCommand.equals(LookupFirstCommand));
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
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(null,
                null, TypicalPersons.KEYWORD_MATCHING_MEIER, null, null, null);
        LookupCommand command = new LookupCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, HOON), model.getFilteredPersonList());
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
        LookupCommand LookupCommand = new LookupCommand(predicate);
        String expected = LookupCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, LookupCommand.toString());
    }
}
