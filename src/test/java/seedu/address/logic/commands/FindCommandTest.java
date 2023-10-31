package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_LESSONS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lessons.LessonContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
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

    @Test
    public void execute_searchNotFoundLesson() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 0);
        String trimmedArgs = "lesson does not exist";
        FindCommand command = new FindCommand(trimmedArgs);
        expectedModel.setState(State.SCHEDULE);
        model.setState(State.SCHEDULE);
        expectedModel.updateFilteredScheduleList(new LessonContainsKeywordsPredicate(trimmedArgs));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

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

    @Test
    public void toStringMethod() {
        String trimmedArgs = "keyword";
        FindCommand findCommand = new FindCommand(trimmedArgs);
        String expected = FindCommand.class.getCanonicalName() + "{trimmed args=" + trimmedArgs + "}";
        assertEquals(expected, findCommand.toString());
    }

}
