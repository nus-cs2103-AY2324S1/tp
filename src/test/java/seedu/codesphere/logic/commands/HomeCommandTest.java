package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.testutil.CourseBuilder;

public class HomeCommandTest {
    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void execute_stageChangedToHome_success() throws CommandException {
        HomeCommand homeCommand = new HomeCommand();
        String expectedMessage = HomeCommand.MESSAGE_SUCCESS;
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);

        CommandResult commandResult = homeCommand.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_stageAlreadyAtHome_success() throws CommandException {
        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        HomeCommand homeCommand = new HomeCommand();
        String expectedMessage = HomeCommand.MESSAGE_HOME_ALREADY;

        CommandResult commandResult = homeCommand.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        HomeCommand homeCommand = new HomeCommand();
        HomeCommand homeCommandOther = new HomeCommand();

        // same object -> returns true
        assertTrue(homeCommand.equals(homeCommand));

        // another home command -> returns true
        assertTrue(homeCommand.equals(homeCommandOther));

        // different types -> returns false
        assertFalse(homeCommand.equals(1));

        // null -> returns false
        assertFalse(homeCommand.equals(null));

    }
}
