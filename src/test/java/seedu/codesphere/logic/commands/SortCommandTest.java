package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.testutil.Assert.assertThrows;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourses;

import org.junit.jupiter.api.Test;

import seedu.codesphere.commons.exceptions.IllegalValueException;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.parser.ParserUtil;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.Field;
import seedu.codesphere.model.student.SortCriteria;
import seedu.codesphere.testutil.SortCriteriaBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
class SortCommandTest {

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void constructor_nullSortCriteria_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

    @Test
    void execute_nameSortCriteriaAcceptedByModel_sortSuccess() {
        SortCriteria validSortCriteria = new SortCriteriaBuilder(Field.NAME).build();
        Course validCourse = getTypicalCourses().get(1);

        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);

        CommandResult commandResult = new SortCommand(validSortCriteria).execute(model);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, Messages.format(validSortCriteria));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    void execute_tagSortCriteriaAcceptedByModel_sortSuccess() {
        SortCriteria validSortCriteria = new SortCriteriaBuilder(Field.TAG).build();
        Course validCourse = getTypicalCourses().get(1);

        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);

        CommandResult commandResult = new SortCommand(validSortCriteria).execute(model);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, Messages.format(validSortCriteria));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    void execute_invalidSortCriteria_throwsIllegalValueException() {
        String invalidSortCriteria = "invalid";
        assertThrows(IllegalValueException.class, () -> ParserUtil.parseSortCriteria(invalidSortCriteria));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        SortCriteria validSortCriteria = new SortCriteriaBuilder(Field.NAME).build();
        SortCommand sortCommand = new SortCommand(validSortCriteria);
        assertThrows(NullPointerException.class, () -> sortCommand.execute(null));
    }

    @Test
    void equals() {
        SortCriteria validNameSortCriteria = new SortCriteriaBuilder(Field.NAME).build();
        SortCriteria validTagSortCriteria = new SortCriteriaBuilder(Field.TAG).build();
        final SortCommand standardCommand = new SortCommand(validNameSortCriteria);

        // same values -> returns true
        SortCommand commandWithSameValues = new SortCommand(validNameSortCriteria);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different sort criteria -> returns false
        assertFalse(standardCommand.equals(new SortCommand(validTagSortCriteria)));
    }

    @Test
    void toStringMethod() {
        SortCriteria validSortCriteria = new SortCriteriaBuilder(Field.NAME).build();
        SortCommand sortCommand = new SortCommand(validSortCriteria);
        String expected = SortCommand.class.getCanonicalName() + "{sort criteria=" + validSortCriteria + "}";
        assertEquals(expected, sortCommand.toString());
    }
}
