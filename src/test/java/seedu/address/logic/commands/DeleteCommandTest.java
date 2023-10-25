package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.NONEXISTENT_STUDENT_NUMBER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validStudentNumber_success() {
        Student studentToDelete = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(studentToDelete.getStudentNumber());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS,
                Messages.format(studentToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        expectedModel.commitAddressBook();

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, commandHistory);
    }

    @Test
    public void execute_nonexistentStudentNumber_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(NONEXISTENT_STUDENT_NUMBER);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER, commandHistory);
    }

    @Test
    public void executeUndoRedo_validStudentNumber_success() throws Exception {
        Student studentToDelete = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(studentToDelete.getStudentNumber());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        expectedModel.commitAddressBook();

        // delete -> first student deleted
        deleteCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered student list to show all students
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel, commandHistory);

        // redo -> same first student deleted again
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel, commandHistory);
    }

    @Test
    public void executeUndoRedo_nonexistentStudentNumber_failure() {
        DeleteCommand deleteCommand = new DeleteCommand(NONEXISTENT_STUDENT_NUMBER);

        // execution failed -> address book state not added into model
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER, commandHistory);

        // single address book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_FAILURE, commandHistory);
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_FAILURE, commandHistory);
    }

    /**
     * 1. Deletes a {@code Student} from a filtered list.
     * 2. Undo the deletion.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously deleted student in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the deletion. This ensures {@code RedoCommand} deletes the student object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_samePersonDeleted() throws Exception {
        Student studentToDelete = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(studentToDelete.getStudentNumber());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        showStudentAtIndex(model, INDEX_SECOND_STUDENT);
        studentToDelete = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        expectedModel.deleteStudent(studentToDelete);
        expectedModel.commitAddressBook();

        // delete -> deletes second student in unfiltered person list / first student in filtered person list
        deleteCommand.execute(model, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered student list to show all students
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel, commandHistory);

        assertNotEquals(studentToDelete, TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased()));
        // redo -> deletes same second student in unfiltered person list
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_SUCCESS, expectedModel, commandHistory);
    }

    @Test
    public void equals() {
        Student first = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student second = TypicalStudents.getTypicalStudents().get(INDEX_SECOND_STUDENT.getZeroBased());
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
        Student target = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(target.getStudentNumber());
        String expected = DeleteCommand.class.getCanonicalName() + "{targetStudentNumber="
                + target.getStudentNumber() + "}";
        assertEquals(expected, deleteCommand.toString());
    }
}
