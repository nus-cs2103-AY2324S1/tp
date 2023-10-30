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
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.ClassDetails;
import seedu.address.model.student.Student;
import seedu.address.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkAbsentCommand.
 */
public class MarkAbsentCommandTest {

    private Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validStudentNumber_success() {
        Student studentToMark = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        Index i = Index.fromOneBased(ClassDetails.DEFAULT_COUNT);
        model.setSelectedStudent(studentToMark);

        MarkAbsentCommand markAbsentCommand = new MarkAbsentCommand(i, studentToMark.getStudentNumber());

        String expectedMessage = MarkAbsentCommand.MESSAGE_MARK_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setStudent(studentToMark, studentToMark.markAbsent(i));

        assertCommandSuccess(markAbsentCommand, model, expectedMessage, expectedModel);
        assertEquals(studentToMark, model.getSelectedStudent().get(0));
    }

    @Test
    public void execute_invalidTutorialIndex_throwsCommandException() {
        Student studentToMark = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        Index i = Index.fromOneBased(ClassDetails.DEFAULT_COUNT + 1);

        MarkAbsentCommand markAbsentCommand = new MarkAbsentCommand(i, studentToMark.getStudentNumber());

        assertCommandFailure(markAbsentCommand, model, Messages.MESSAGE_INVALID_TUTORIAL_INDEX);
    }

    @Test
    public void execute_nonexistentStudentNumber_throwsCommandException() {
        MarkAbsentCommand markAbsentCommand = new MarkAbsentCommand(Index.fromOneBased(1),
                NONEXISTENT_STUDENT_NUMBER);

        assertCommandFailure(markAbsentCommand, model, Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER);
    }

    @Test
    public void equals() {
        Student firstStudent = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student secondStudent = TypicalStudents.getTypicalStudents().get(INDEX_SECOND_STUDENT.getZeroBased());
        MarkAbsentCommand markFirstStudentForFirstTutorial = new MarkAbsentCommand(Index.fromOneBased(1),
                firstStudent.getStudentNumber());
        MarkAbsentCommand markFirstStudentForSecondTutorial = new MarkAbsentCommand(Index.fromOneBased(2),
                firstStudent.getStudentNumber());
        MarkAbsentCommand markSecondStudentForFirstTutorial = new MarkAbsentCommand(Index.fromOneBased(1),
                secondStudent.getStudentNumber());
        MarkAbsentCommand markSecondStudentForSecondTutorial = new MarkAbsentCommand(Index.fromOneBased(2),
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
