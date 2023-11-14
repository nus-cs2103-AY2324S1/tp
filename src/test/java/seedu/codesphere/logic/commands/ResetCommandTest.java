package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.testutil.CourseBuilder;
import seedu.codesphere.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ResetCommand.
 */
public class ResetCommandTest {
    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    void execute_resetCommand_success() {
        CourseBuilder courseBuilder = new CourseBuilder().withStudents(TypicalStudents.getTypicalStudentList());
        Course validCourse = courseBuilder.build();
        StageManager.getInstance().setCourseStage(validCourse);

        CommandResult commandResult = new ResetCommand().execute(model);
        String expectedMessage = ResetCommand.MESSAGE_SUCCESS;
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void equals() {
        final ResetCommand standardCommand = new ResetCommand();

        // same values -> returns true
        ResetCommand commandWithSameValues = new ResetCommand();
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
    }
}
