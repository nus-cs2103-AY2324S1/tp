package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.HOON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.StudentContainsKeywordsPredicate;
import seedu.address.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) for {@code LookupCommand}.
 */
public class LookupCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StudentContainsKeywordsPredicate firstPredicate =
                new StudentContainsKeywordsPredicate("T11", null,
                        TypicalStudents.KEYWORD_MATCHING_MEIER, null, null, null);
        StudentContainsKeywordsPredicate secondPredicate =
                new StudentContainsKeywordsPredicate("T10", null,
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

        // different student -> returns false
        assertFalse(lookupFirstCommand.equals(lookupSecondCommand));
        assertFalse(lookupSecondCommand.equals(lookupFirstCommand));
    }

    @Test
    public void execute_zeroKeywords_allPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW,
                expectedModel.getFilteredStudentList().size());
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(null,
                null, null, null, null, null);
        LookupCommand command = new LookupCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredStudentList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_singleKeywords_multiplePersonsFound() {
        expectedModel.addStudent(HOON);
        model.addStudent(HOON);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(null,
                null, TypicalStudents.KEYWORD_MATCHING_MEIER, null, null, null);
        LookupCommand command = new LookupCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, HOON), model.getFilteredStudentList());
        expectedModel.deleteStudent(HOON);
        model.deleteStudent(HOON);
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(null,
                null, TypicalStudents.KEYWORD_MATCHING_MEIER, null, null, "friends");
        LookupCommand command = new LookupCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredStudentList());
    }

    @Test
    public void execute_nonExistentKeywords_noPersonsFound() {
        // single keyword
        String expectedMessage = LookupCommand.MESSAGE_NO_MATCH;
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate("T9999",
                null, null, null, null, null);
        LookupCommand command = new LookupCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());

        // multiple keywords
        predicate = new StudentContainsKeywordsPredicate("T11",
                null, "thisIsATestName", null, null, "tagTagTag");
        command = new LookupCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void toStringMethod() {
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate("T11",
                null, TypicalStudents.KEYWORD_MATCHING_MEIER, null, null, null);
        LookupCommand lookupCommand = new LookupCommand(predicate);
        String expected = LookupCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, lookupCommand.toString());
    }
}
