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
        int personId = 1;
        assertCommandSuccessWithFeedback(() -> new AddNoteCommand(personId, VALID_NOTE_0)
                .execute(model), AddNoteCommand.MESSAGE_SUCCESS + VALID_NOTE_0.getTitle());
    }

    @Test
    public void execute_personNotExist_fails() throws CommandException {
        int personId = 99999;
        assertCommandFailWithFeedback(() -> new AddNoteCommand(personId, VALID_NOTE_SAME_TITLE_0)
                .execute(model), AddNoteCommand.MESSAGE_PERSON_NOT_FOUND + personId);
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
