package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_A_PERSON_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_B_PERSON_ID;
import static seedu.address.testutil.TypicalNotes.NOTE_A;
import static seedu.address.testutil.TypicalNotes.NOTE_B;
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

public class AddNoteCommandTest {

    private static final Note VALID_NOTE_0 = new Note("Meeting Topics",
            "The topic is about the framework design of the project");
    private static final Note VALID_NOTE_SAME_TITLE_0 = new Note("Meeting Topics",
            "Applications for SWE full-time positions will open soon");

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_correctCommand_success() throws CommandException {
        ContactID contactId = ContactID.fromInt(1);
        assertCommandSuccessWithFeedback(() -> new AddNoteCommand(contactId, VALID_NOTE_0)
                .execute(model), AddNoteCommand.MESSAGE_SUCCESS + VALID_NOTE_0.getTitle());
    }

    @Test
    public void execute_personNotExist_fails() throws CommandException {
        ContactID contactId = ContactID.fromInt(99999);
        assertCommandFailWithFeedback(() -> new AddNoteCommand(contactId, VALID_NOTE_SAME_TITLE_0)
                .execute(model), AddNoteCommand.MESSAGE_PERSON_NOT_FOUND + contactId);
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
        Note noteA = NOTE_A;
        Note noteB = NOTE_B;
        AddNoteCommand addNoteACommand = new AddNoteCommand(ContactID.fromString(VALID_NOTE_A_PERSON_ID), noteA);
        AddNoteCommand addNoteBCommand = new AddNoteCommand(ContactID.fromString(VALID_NOTE_B_PERSON_ID), noteB);

        // same object -> returns true
        assertTrue(addNoteACommand.equals(addNoteACommand));

        // same values -> returns true
        AddNoteCommand addNoteACommandCopy = new AddNoteCommand(ContactID.fromString(VALID_NOTE_A_PERSON_ID), noteA);
        assertTrue(addNoteACommand.equals(addNoteACommandCopy));

        // different types -> returns false
        assertFalse(addNoteACommand.equals(1));

        // null -> returns false
        assertFalse(addNoteACommand.equals(null));

        // different person -> returns false
        assertFalse(addNoteACommand.equals(addNoteBCommand));
    }
}
