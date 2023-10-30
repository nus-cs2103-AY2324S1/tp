package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RandomCommand.
 */
public class RandomCommandTest {

    private Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_success() {
        Student studentToSelect = TypicalStudents.getTypicalStudents().get(INDEX_FIRST_STUDENT.getZeroBased());
        model.updateFilteredStudentList(s -> s.equals(studentToSelect));

        RandomCommand randomCommand = new RandomCommand(Index.fromOneBased(1));

        String expectedMessage = RandomCommand.MESSAGE_RANDOM_SUCCESS
                + studentToSelect.getName() + " " + studentToSelect.getStudentNumber() + "\n";

        assertCommandSuccess(randomCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        int invalid = TypicalStudents.getTypicalStudents().size() + 1;

        RandomCommand randomCommand = new RandomCommand(Index.fromOneBased(invalid));

        assertCommandFailure(randomCommand, model, RandomCommand.MESSAGE_TOO_MUCH_TO_BE_SELECTED);
    }

    @Test
    public void equals() {
        RandomCommand randomOne = new RandomCommand(Index.fromOneBased(1));
        RandomCommand randomTwo = new RandomCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(randomOne.equals(randomOne));

        // null -> returns false
        assertFalse(randomOne.equals(null));

        // different types -> returns false
        assertFalse(randomOne.equals(1));

        // different number to be selected
        assertFalse(randomOne.equals(randomTwo));
    }

}
