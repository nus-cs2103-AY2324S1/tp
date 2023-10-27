package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_A_NOTE_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_A_PERSON_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_B_NOTE_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_B_PERSON_ID;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;
import seedu.address.model.person.ContactID;

public class DeleteNoteCommandTest {
    private static final Note VALID_NOTE_0 = new Note("Meeting Topics",
            "The topic is about the framework design of the project");

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_correctCommand_success() throws CommandException {
        ContactID contactId = ContactID.fromInt(1);
        model.findPersonByUserFriendlyId(contactId).addNote(VALID_NOTE_0);
        assertCommandSuccessWithFeedback(() -> new DeleteNoteCommand(contactId.getId(), 1)
                .execute(model), DeleteNoteCommand.MESSAGE_SUCCESS + "1");
    }

    @Test
    public void execute_personNotFound_fails() throws CommandException {
        int personId = 999;
        assertCommandFailWithFeedback(() -> new DeleteNoteCommand(personId, 1)
                .execute(model), DeleteNoteCommand.MESSAGE_PERSON_NOT_FOUND + personId);
    }

    @Test
    public void execute_noteNotFound_fails() throws CommandException {
        ContactID contactId = ContactID.fromInt(1);
        int invalidNoteId = 99999;
        model.findPersonByUserFriendlyId(contactId).addNote(VALID_NOTE_0);
        assertCommandFailWithFeedback(() -> new DeleteNoteCommand(contactId.getId(), invalidNoteId)
                .execute(model), DeleteNoteCommand.MESSAGE_NOTE_NOT_FOUND + invalidNoteId);
    }

    private void assertCommandSuccessWithFeedback(ThrowingSupplier<CommandResult> function, String result) {
        try {
            assertEquals(function.get(), new CommandResult(result));
        } catch (Throwable e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    private void assertCommandFailWithFeedback(ThrowingSupplier<CommandResult> function, String errResult) {
        try {
            function.get();
        } catch (Throwable e) {
            if (!(e instanceof CommandException)) {
                throw new AssertionError("Execution of command failed but not due to CommandException.");
            }
            assertEquals(e.getMessage(), errResult);
            return;
        }
        throw new AssertionError("Execution of command should fail.");
    }

    @Test
    public void equals() {
        DeleteNoteCommand deleteNoteACommand = new DeleteNoteCommand(Integer.parseInt(VALID_NOTE_A_PERSON_ID),
                Integer.parseInt(VALID_NOTE_A_NOTE_ID));
        DeleteNoteCommand deleteNoteBCommand = new DeleteNoteCommand(Integer.parseInt(VALID_NOTE_B_PERSON_ID),
                Integer.parseInt(VALID_NOTE_B_NOTE_ID));

        // same object -> returns true
        assertTrue(deleteNoteACommand.equals(deleteNoteACommand));

        // same values -> returns true
        DeleteNoteCommand deleteNoteACommandCopy = new DeleteNoteCommand(Integer.parseInt(VALID_NOTE_A_PERSON_ID),
                Integer.parseInt(VALID_NOTE_A_NOTE_ID));
        assertTrue(deleteNoteACommand.equals(deleteNoteACommandCopy));

        // different types -> returns false
        assertFalse(deleteNoteACommand.equals(1));

        // null -> returns false
        assertFalse(deleteNoteACommand.equals(null));

        // different person -> returns false
        assertFalse(deleteNoteACommand.equals(deleteNoteBCommand));
    }
}
