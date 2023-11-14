package seedu.classmanager.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classmanager.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.classmanager.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.classmanager.commons.core.index.Index;
import seedu.classmanager.logic.CommandHistory;
import seedu.classmanager.logic.commands.exceptions.CommandException;
import seedu.classmanager.model.Model;
import seedu.classmanager.model.ModelManager;
import seedu.classmanager.model.UserPrefs;
import seedu.classmanager.model.student.ClassDetails;
import seedu.classmanager.model.student.Student;
import seedu.classmanager.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkPresentAllCommand.
 */
public class MarkPresentAllCommandTest {

    private final Model model = new ModelManager(TypicalStudents.getTypicalClassManager(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_success() throws CommandException {
        Index i = Index.fromOneBased(ClassDetails.getTutorialCount());
        Student selectedStudent = TypicalStudents.getTypicalStudents().get(0);
        model.setSelectedStudent(selectedStudent);

        MarkPresentAllCommand markPresentAllCommand = new MarkPresentAllCommand(i);

        String expectedMessage = MarkPresentAllCommand.MESSAGE_MARK_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getClassManager(), new UserPrefs());
        for (Student s : TypicalStudents.getTypicalStudents()) {
            Student markedStudent = s.copy();
            markedStudent.markPresent(i);
            expectedModel.setStudent(s, markedStudent);
        }
        expectedModel.commitClassManager();

        assertCommandSuccess(markPresentAllCommand, model, expectedMessage, expectedModel, commandHistory);
        assertEquals(selectedStudent, model.getSelectedStudent());
    }

    @Test
    public void execute_invalidTutorialIndex_throwsCommandException() {
        Index i = Index.fromZeroBased(ClassDetails.getTutorialCount() + 1);

        MarkPresentAllCommand markPresentAllCommand = new MarkPresentAllCommand(i);

        assertCommandFailure(
                markPresentAllCommand, model,
                String.format(ClassDetails.MESSAGE_INVALID_TUTORIAL_INDEX, ClassDetails.getTutorialCount()),
                commandHistory);
    }

    @Test
    public void equals() {
        MarkPresentAllCommand markForFirstTutorial = new MarkPresentAllCommand(Index.fromOneBased(1));
        MarkPresentAllCommand markForSecondTutorial = new MarkPresentAllCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(markForFirstTutorial.equals(markForFirstTutorial));

        // null -> returns false
        assertFalse(markForFirstTutorial.equals(null));

        // different types -> returns false
        assertFalse(markForFirstTutorial.equals(1));

        // different tutorial index -> returns false
        assertFalse(markForFirstTutorial.equals(markForSecondTutorial));
    }

}
