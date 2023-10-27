package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

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
 * Contains integration tests (interaction with the Model) and unit tests for MarkAllCommand.
 */
public class MarkAllCommandTest {

    private Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_success() {
        Index i = Index.fromOneBased(ClassDetails.DEFAULT_COUNT);

        MarkAllCommand markAllCommand = new MarkAllCommand(i);

        String expectedMessage = MarkAllCommand.MESSAGE_MARK_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        for (Student s : TypicalStudents.getTypicalStudents()) {
            expectedModel.setStudent(s, s.markPresent(i));
        }

        assertCommandSuccess(markAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTutorialIndex_throwsCommandException() {
        Index i = Index.fromOneBased(ClassDetails.DEFAULT_COUNT + 1);

        MarkAllCommand markAllCommand = new MarkAllCommand(i);

        assertCommandFailure(markAllCommand, model, Messages.MESSAGE_INVALID_TUTORIAL_INDEX);
    }

    @Test
    public void equals() {
        MarkAllCommand markForFirstTutorial = new MarkAllCommand(Index.fromOneBased(1));
        MarkAllCommand markForSecondTutorial = new MarkAllCommand(Index.fromOneBased(2));

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
