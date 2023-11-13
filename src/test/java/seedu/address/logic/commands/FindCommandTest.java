package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalWellNus.getTypicalWellNus;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalWellNus(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalWellNus(), new UserPrefs());

    @Test
    public void equals() {

        String[] keywords = new String[] {"Bernice"};
        String[] otherKeywords = new String[] {"Alice"};
        FindCommand findFirstCommand = new FindCommand(keywords);
        FindCommand findSecondCommand = new FindCommand(otherKeywords);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(keywords);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    // Find empty string
    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        String input = " ";
        String[] keywords = input.split("\\s+");
        FindCommand command = new FindCommand(keywords);
        expectedModel.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    // Find first name
    @Test
    public void execute_firstName_studentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        String input = "Carl";
        String[] keywords = input.split("\\s+");
        FindCommand command = new FindCommand(keywords);
        expectedModel.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredStudentList());
    }

    // Find last name
    @Test
    public void execute_lastName_studentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        String input = "Kurz";
        String[] keywords = input.split("\\s+");
        FindCommand command = new FindCommand(keywords);
        expectedModel.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredStudentList());
    }

    // Find full name
    @Test
    public void execute_fullName_studentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        String input = "Carl Kurz";
        String[] keywords = input.split("\\s+");
        FindCommand command = new FindCommand(keywords);
        expectedModel.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredStudentList());
    }

    @Test
    public void toStringMethod() {
        String[] keywords = new String[] {"Bernice"};
        FindCommand findCommand = new FindCommand(keywords);
        String expected = FindCommand.class.getCanonicalName() + "{name keyword 0=Bernice}";
        assertEquals(expected, findCommand.toString());
    }
}
