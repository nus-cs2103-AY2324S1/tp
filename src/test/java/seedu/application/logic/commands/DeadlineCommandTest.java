package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DEADLINE_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DEADLINE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.logic.commands.DeadlineCommand.MESSAGE_ARGUMENTS;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.application.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeadlineCommand.
 */
public class DeadlineCommandTest {
    private Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void execute() {
        final String deadline = "Some deadline";

        assertCommandFailure(new DeadlineCommand(INDEX_FIRST_JOB, deadline), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_JOB.getOneBased(), deadline));
    }

    @Test
    public void equals() {
        final DeadlineCommand standardCommand = new DeadlineCommand(INDEX_FIRST_JOB, VALID_DEADLINE_CHEF);

        // same values -> returns true
        DeadlineCommand commandWithSameValues = new DeadlineCommand(INDEX_FIRST_JOB, VALID_DEADLINE_CHEF);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeadlineCommand(INDEX_SECOND_JOB, VALID_DEADLINE_CHEF)));

        // different deadline -> returns false
        assertFalse(standardCommand.equals(new DeadlineCommand(INDEX_FIRST_JOB, VALID_DEADLINE_CLEANER)));
    }
}
