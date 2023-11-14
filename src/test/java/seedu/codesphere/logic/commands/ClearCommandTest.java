package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourses;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.testutil.CourseBuilder;
import seedu.codesphere.testutil.TypicalStudents;

public class ClearCommandTest {
    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void execute_emptyStudentList_success() {
        Course validCourse = getTypicalCourses().get(1);

        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);

        CommandResult commandResult = new ClearCommand().execute(model);
        String expectedMessage = ClearCommand.MESSAGE_SUCCESS;
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void execute_nonEmptyStudentList_success() {
        CourseBuilder courseBuilder = new CourseBuilder().withStudents(TypicalStudents.getTypicalStudentList());
        Course validCourse = courseBuilder.build();
        StageManager.getInstance().setCourseStage(validCourse);

        CommandResult commandResult = new ClearCommand().execute(model);
        String expectedMessage = ClearCommand.MESSAGE_SUCCESS;
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

}
