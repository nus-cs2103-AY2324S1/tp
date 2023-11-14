package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseData;

public class ClearTeachCommandTest {

    @Test
    public void execute_emptyUserPref_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult expectedCommandResult = new CommandResult(ClearTeachCommand.MESSAGE_SUCCESS,
                false, false, true);

        assertCommandSuccess(new ClearTeachCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyUserPref_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Course course = CourseData.getCourseList().get(0);
        model.setTeaching(course);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(ClearTeachCommand.MESSAGE_SUCCESS,
                false, false, true);

        assertCommandSuccess(new ClearTeachCommand(), model, expectedCommandResult, expectedModel);
    }

}
