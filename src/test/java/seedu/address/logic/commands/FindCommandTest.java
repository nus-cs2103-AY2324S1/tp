package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_LESSONS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.LessonContainsKeywordsPredicate;
import seedu.address.model.lessons.TaskList;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.state.State;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());

    @Test
    public void equals() {
        String firstTrimmedArg = "first";
        String secondTrimmedArg = "second";

        FindCommand findFirstCommand = new FindCommand(firstTrimmedArg);
        FindCommand findSecondCommand = new FindCommand(secondTrimmedArg);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstTrimmedArg);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /* Tests for zero keywords, should find all persons/lessons */

    @Test
    public void execute_zeroKeywords_allPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        String trimmedArgs = " ";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.STUDENT);
        model.setState(State.STUDENT);
        expectedModel.updateFilteredPersonList(new NameContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_zeroKeywords_allLessonsFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 5);
        String trimmedArgs = " ";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.SCHEDULE);
        model.setState(State.SCHEDULE);
        expectedModel.updateFilteredScheduleList(new LessonContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /* Test cases for finding lessons */

    // Find one lesson
    @Test
    public void execute_searchOneLesson() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 1);
        String trimmedArgs = "random";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.SCHEDULE);
        model.setState(State.SCHEDULE);
        expectedModel.updateFilteredScheduleList(new LessonContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    // Find no lessons
    @Test
    public void execute_searchNotFoundLesson() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 0);
        String trimmedArgs = "lesson which does not exist";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.SCHEDULE);
        model.setState(State.SCHEDULE);
        expectedModel.updateFilteredScheduleList(new LessonContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    // Find multiple lessons
    @Test
    public void execute_searchMultipleLessons() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 4);
        String trimmedArgs = "lesson";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.SCHEDULE);
        model.setState(State.SCHEDULE);
        expectedModel.updateFilteredScheduleList(new LessonContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /* Test cases for finding students */

    // Find one student
    @Test
    public void execute_searchOneStudent() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        String trimmedArgs = "ALICE";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.STUDENT);
        model.setState(State.STUDENT);
        expectedModel.updateFilteredPersonList(new NameContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    // Find no students
    @Test
    public void execute_searchNotFoundStudent() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        String trimmedArgs = "student who does not exist";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.STUDENT);
        model.setState(State.STUDENT);
        expectedModel.updateFilteredPersonList(new NameContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    // Find multiple students: Find "on" which returns 2 students: Benson and Fiona
    @Test
    public void execute_searchMultipleStudents() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        String trimmedArgs = "on";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.STUDENT);
        model.setState(State.STUDENT);
        expectedModel.updateFilteredPersonList(new NameContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredPersonList().get(0), BENSON);
        assertEquals(model.getFilteredPersonList().get(1), FIONA);
    }

    /* Test for name contains search string */
    // Find student with exact name
    @Test
    public void execute_studentNameExactMatch() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        String trimmedArgs = "Carl Kurz";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.STUDENT);
        model.setState(State.STUDENT);
        expectedModel.updateFilteredPersonList(new NameContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredPersonList().get(0), CARL);
    }
    // Find student with the middle part of name
    @Test
    public void execute_studentNamePartialMatch() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        String trimmedArgs = "arl Ku";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.STUDENT);
        model.setState(State.STUDENT);
        expectedModel.updateFilteredPersonList(new NameContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredPersonList().get(0), CARL);
    }
    // Find lesson with exact name
    @Test
    public void execute_lessonNameExactMatch() throws ParseException {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 1);
        String trimmedArgs = "some lesson";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.SCHEDULE);
        model.setState(State.SCHEDULE);
        expectedModel.updateFilteredScheduleList(new LessonContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    // Find lesson with the middle part of name
    @Test
    public void execute_lessonNamePartialMatch() throws ParseException {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 1);
        String trimmedArgs = "me les";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.SCHEDULE);
        model.setState(State.SCHEDULE);
        expectedModel.updateFilteredScheduleList(new LessonContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /* Test for search string case sensitivity */
    @Test
    public void execute_studentNameExactWrongCase() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        String trimmedArgs = "caRl kURz";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.STUDENT);
        model.setState(State.STUDENT);
        expectedModel.updateFilteredPersonList(new NameContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredPersonList().get(0), CARL);
    }
    @Test
    public void execute_studentNamePartialWrongCase() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        String trimmedArgs = "rL Ku";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.STUDENT);
        model.setState(State.STUDENT);
        expectedModel.updateFilteredPersonList(new NameContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredPersonList().get(0), CARL);
    }
    @Test
    public void execute_lessonNameExactWrongCase() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 1);
        String trimmedArgs = "sOmE LeSSon";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.SCHEDULE);
        model.setState(State.SCHEDULE);
        expectedModel.updateFilteredScheduleList(new LessonContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_lessonNamePartialWrongCase() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 1);
        String trimmedArgs = "mE LeS";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.SCHEDULE);
        model.setState(State.SCHEDULE);
        expectedModel.updateFilteredScheduleList(new LessonContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    @Test
    public void toStringMethod() {
        String trimmedArgs = "keyword";
        FindCommand findCommand = new FindCommand(trimmedArgs);
        String expected = FindCommand.class.getCanonicalName() + "{trimmed args=" + trimmedArgs + "}";
        assertEquals(expected, findCommand.toString());
    }

}
