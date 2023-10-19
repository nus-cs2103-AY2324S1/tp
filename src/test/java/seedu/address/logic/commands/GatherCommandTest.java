package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.GatherCommand.MESSAGE_NO_PERSON_FOUND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code GatherCommand}.
 */
public class GatherCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_gatherEmails_success() {
        String message = ALICE.getEmail() + " " + BENSON.getEmail() + " " + GEORGE.getEmail();
        String prompt = "Sample Financial Plan 1";
        String expectedMessage = expectedModel.gatherEmails(prompt);
        GatherCommand command = new GatherCommand(prompt);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertCommandSuccess(command, model, message, expectedModel);
    }

    @Test
    public void execute_noPersonFound() {
        String prompt = "Sample Plan 3";
        String expectedMessage = String.format(MESSAGE_NO_PERSON_FOUND + prompt);
        GatherCommand command = new GatherCommand(prompt);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void testEquals() {
        GatherCommand gatherFirstCommand = new GatherCommand("first");
        GatherCommand gatherSecondCommand = new GatherCommand("second");

        // same object -> returns true
        assertTrue(gatherFirstCommand.equals(gatherFirstCommand));

        // same values -> returns true
        GatherCommand gatherFirstCommandCopy = new GatherCommand("first");
        assertTrue(gatherFirstCommand.equals(gatherFirstCommandCopy));

        // different types -> returns false
        assertFalse(gatherFirstCommand.equals(1));

        // null -> returns false
        assertFalse(gatherFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(gatherFirstCommand.equals(gatherSecondCommand));
    }

    @Test
    void testToString() {
        String prompt = "prompt";
        GatherCommand gatherCommand = new GatherCommand(prompt);
        String expected = GatherCommand.class.getCanonicalName() + "{prompt=" + prompt + "}";
        assertEquals(expected, gatherCommand.toString());
    }
}
