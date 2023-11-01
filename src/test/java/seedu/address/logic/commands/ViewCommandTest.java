package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.NONEXISTENT_STUDENT_NUMBER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.testutil.TypicalStudents;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewCommand}.
 */
public class ViewCommandTest {

    private Model model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());

    @Test
    public void execute_validStudentNumber_success() {
        Student studentToView = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(studentToView.getStudentNumber());

        String expectedMessage = String.format(ViewCommand.MESSAGE_COMMAND_SUCCESS,
            studentToView.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setSelectedStudent(studentToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistentStudentNumber_failure() {
        ViewCommand viewCommand = new ViewCommand(NONEXISTENT_STUDENT_NUMBER);

        assertCommandFailure(viewCommand, model, ViewCommand.MESSAGE_COMMAND_FAILURE);
    }

    @Test
    public void equalsMethod() {
        ViewCommand viewCommand = new ViewCommand(new StudentNumber(VALID_STUDENT_NUMBER_AMY));
        ViewCommand commandWithSameValue = new ViewCommand(new StudentNumber(VALID_STUDENT_NUMBER_AMY));
        ViewCommand commandWithDifferentValue = new ViewCommand(new StudentNumber(VALID_STUDENT_NUMBER_BOB));

        // same object -> returns true
        assertTrue(viewCommand.equals(viewCommand));

        // same value -> returns true
        assertTrue(viewCommand.equals(commandWithSameValue));

        // different value -> returns false
        assertFalse(viewCommand.equals(commandWithDifferentValue));

        // null value -> returns false
        assertFalse(viewCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        Student target = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(target.getStudentNumber());
        String expected = ViewCommand.class.getCanonicalName() + "{studentNumber="
            + target.getStudentNumber() + "}";
        assertEquals(expected, viewCommand.toString());
    }
}
