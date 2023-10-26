package seedu.lovebook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_SEQUENCE;
import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.lovebook.testutil.TypicalDatePrefs.getTypicalDatePrefs;
import static seedu.lovebook.testutil.TypicalPersons.getTypicalLoveBook;

import org.junit.jupiter.api.Test;

import seedu.lovebook.model.Model;
import seedu.lovebook.model.ModelManager;
import seedu.lovebook.model.UserPrefs;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());
    private Model expectedModel = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());
    @Test
    public void equals() {
        String sequence = "ascending";
        SortCommand sortFirstCommand = new SortCommand(PREFIX_NAME, sequence);
        SortCommand sortSecondCommand = new SortCommand(PREFIX_AGE, sequence);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(PREFIX_NAME, sequence);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different date -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

    @Test
    public void correctPrefixWrongSequence() {
        String sequence = "lolol";
        String expectedMessage = String.format(MESSAGE_INVALID_SEQUENCE);
        SortCommand command = new SortCommand(PREFIX_NAME, sequence);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void correctPrefixCorrectSequence() {
        String sequence = "increasing";
        String expectedMessage = "Sorted!";
        SortCommand command = new SortCommand(PREFIX_NAME, sequence);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void wrongPrefixCorrectSequence_throw() {
        String sequence = "increasing";
        String expectedMessage = String.format(MESSAGE_INVALID_PREFIX);
        SortCommand command = new SortCommand(PREFIX_GENDER, sequence);
        assertCommandFailure(command, model, expectedMessage);
    }
}
