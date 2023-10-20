package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validStudentNumber_success() {
        Student studentToDelete = TypicalStudents.getTypicalPersons().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(studentToDelete.getStudentNumber());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS,
                Messages.format(studentToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentStudentNumber_success() {
        StudentNumber studentNumber = new StudentNumber("A0000000A");
        DeleteCommand deleteCommand = new DeleteCommand(studentNumber);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER);
    }

    @Test
    public void equals() {
        Student first = TypicalStudents.getTypicalPersons().get(INDEX_FIRST_PERSON.getZeroBased());
        Student second = TypicalStudents.getTypicalPersons().get(INDEX_SECOND_PERSON.getZeroBased());
        DeleteCommand deleteFirstCommand = new DeleteCommand(first.getStudentNumber());
        DeleteCommand deleteSecondCommand = new DeleteCommand(second.getStudentNumber());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(first.getStudentNumber());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Student target = TypicalStudents.getTypicalPersons().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(target.getStudentNumber());
        String expected = DeleteCommand.class.getCanonicalName() + "{targetStudentNumber="
                + target.getStudentNumber() + "}";
        assertEquals(expected, deleteCommand.toString());
    }
}
