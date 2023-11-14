package seedu.codesphere.logic.commands;

import static seedu.codesphere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;

import org.junit.jupiter.api.Test;

import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;

public class ClearCourseCommandTest {
    @Test
    public void execute_emptyCourseList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCourseCommand(), model, ClearCourseCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyCourseList_success() {
        Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalCourseList(), new UserPrefs());
        expectedModel.setCourseList(new CourseList());

        assertCommandSuccess(new ClearCourseCommand(), model, ClearCourseCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
