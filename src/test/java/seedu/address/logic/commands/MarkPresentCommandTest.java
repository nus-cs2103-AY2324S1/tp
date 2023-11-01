package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.NONEXISTENT_STUDENT_NUMBER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.ClassDetails;
import seedu.address.model.student.Student;
import seedu.address.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkPresentCommand.
 */
public class MarkPresentCommandTest {

    private final Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validStudentNumber_success() throws IllegalValueException, CommandException {
        Student studentToMark = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        Index i = Index.fromOneBased(ClassDetails.DEFAULT_COUNT);
        model.setSelectedStudent(studentToMark);

        MarkPresentCommand markPresentCommand = new MarkPresentCommand(i, studentToMark.getStudentNumber());

        String expectedMessage = MarkPresentCommand.MESSAGE_MARK_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Student markedStudent = studentToMark.copy();
        markedStudent.markPresent(i);
        expectedModel.setStudent(studentToMark, markedStudent);
        expectedModel.commitAddressBook();

        assertCommandSuccess(markPresentCommand, model, expectedMessage, expectedModel, commandHistory);
        assertEquals(studentToMark, model.getSelectedStudent().get(0));
    }

    @Test
    public void execute_invalidTutorialIndex_throwsCommandException() {
        Student studentToMark = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        Index i = Index.fromZeroBased(ClassDetails.DEFAULT_COUNT + 1);

        MarkPresentCommand markPresentCommand = new MarkPresentCommand(i, studentToMark.getStudentNumber());

        assertCommandFailure(
                markPresentCommand, model,
                String.format(ClassDetails.MESSAGE_INVALID_TUTORIAL_SESSION_NUMBER, ClassDetails.DEFAULT_COUNT),
                commandHistory);
    }

    @Test
    public void execute_nonexistentStudentNumber_throwsCommandException() {
        MarkPresentCommand markPresentCommand = new MarkPresentCommand(Index.fromOneBased(1),
                NONEXISTENT_STUDENT_NUMBER);

        assertCommandFailure(markPresentCommand, model, Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER, commandHistory);
    }

    @Test
    public void equals() {
        Student firstStudent = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student secondStudent = TypicalStudents.getTypicalStudents().get(INDEX_SECOND_STUDENT.getZeroBased());
        MarkPresentCommand markFirstStudentForFirstTutorial = new MarkPresentCommand(Index.fromOneBased(1),
                firstStudent.getStudentNumber());
        MarkPresentCommand markFirstStudentForSecondTutorial = new MarkPresentCommand(Index.fromOneBased(2),
                firstStudent.getStudentNumber());
        MarkPresentCommand markSecondStudentForFirstTutorial = new MarkPresentCommand(Index.fromOneBased(1),
                secondStudent.getStudentNumber());
        MarkPresentCommand markSecondStudentForSecondTutorial = new MarkPresentCommand(Index.fromOneBased(2),
                secondStudent.getStudentNumber());

        // same object -> returns true
        assertTrue(markFirstStudentForFirstTutorial.equals(markFirstStudentForFirstTutorial));

        // null -> returns false
        assertFalse(markFirstStudentForFirstTutorial.equals(null));

        // different types -> returns false
        assertFalse(markSecondStudentForFirstTutorial.equals(1));

        // same student and different tutorial index -> returns false
        assertFalse(markFirstStudentForFirstTutorial.equals(markFirstStudentForSecondTutorial));

        // different student and same tutorial index -> returns false
        assertFalse(markFirstStudentForFirstTutorial.equals(markSecondStudentForFirstTutorial));

        // different student and different tutorial index -> returns false
        assertFalse(markFirstStudentForFirstTutorial.equals(markSecondStudentForSecondTutorial));
    }

}
