package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codesphere.logic.commands.CommandTestUtil.showCourseAtIndex;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_FIRST_COURSE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ResetCourseCommand.
 */
public class ResetCourseCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCourseList(), new UserPrefs());
        expectedModel = new ModelManager(model.getCourseList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ResetCourseCommand(), model, ResetCourseCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showCourseAtIndex(model, INDEX_FIRST_COURSE);
        assertCommandSuccess(new ResetCourseCommand(), model, ResetCourseCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        final ResetCourseCommand standardCommand = new ResetCourseCommand();

        // same values -> returns true
        ResetCourseCommand commandWithSameValues = new ResetCourseCommand();
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
    }
}
