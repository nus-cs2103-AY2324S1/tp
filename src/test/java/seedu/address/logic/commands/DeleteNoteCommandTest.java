package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;

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
        int personId = 1;
        model.findPersonByUserFriendlyId(personId).addNote(VALID_NOTE_0);
        assertCommandSuccessWithFeedback(() -> new DeleteNoteCommand(personId, 1)
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
        int personId = 1;
        int invalidNoteId = 99999;
        model.findPersonByUserFriendlyId(personId).addNote(VALID_NOTE_0);
        assertCommandFailWithFeedback(() -> new DeleteNoteCommand(personId, invalidNoteId)
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
}
