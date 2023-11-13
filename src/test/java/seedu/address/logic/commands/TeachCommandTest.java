package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.TeachCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.course.UniqueCourseList;
import seedu.address.model.person.predicates.TeachingCoursePredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code TeachCommand}.
 */
public class TeachCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final Course course = UniqueCourseList.getList().get(0);

    @Test
    public void execute_validCourse_success() {
        TeachCommand teachCommand = new TeachCommand(course);

        CommandResult expectedCommandResult = new CommandResult(course.getCourseCode() + MESSAGE_SUCCESS,
                false, false, true);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setTeaching(course);

        // Update expected model
        TeachingCoursePredicate predicate = new TeachingCoursePredicate(List.of(course));
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(teachCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        TeachCommand teachCommand = new TeachCommand(course);
        TeachCommand otherTeachCommand = new TeachCommand(UniqueCourseList.getList().get(1));

        // same object -> returns true
        assert (teachCommand.equals(teachCommand));

        // same values -> returns true
        TeachCommand teachCommandCopy = new TeachCommand(course);
        assert (teachCommand.equals(teachCommandCopy));

        // different types -> returns false
        assert (!teachCommand.equals(1));

        // null -> returns false
        assert (!teachCommand.equals(null));

        // different person -> returns false
        assert (!teachCommand.equals(otherTeachCommand));
    }
}
