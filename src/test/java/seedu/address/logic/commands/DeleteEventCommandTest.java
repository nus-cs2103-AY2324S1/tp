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
import seedu.address.model.event.Event;

public class DeleteEventCommandTest {
    private static final Event VALID_EVENT_0 = new Event("Have a meeting", "02:00", "04:00",
            "COM1", "Discuss project");
    private static final Event VALID_EVENT_1 = new Event("Have a meeting again", "05:00", "07:00",
            "COM1", "Discuss project again");

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_correctCommand_success() throws CommandException {
        int personId = 1;
        model.findPersonByUserFriendlyId(personId).addEvent(VALID_EVENT_0);
        assertCommandSuccessWithFeedback(() -> new DeleteEventCommand(personId, 1)
                .execute(model), DeleteEventCommand.MESSAGE_SUCCESS + "1");
    }

    @Test
    public void execute_personNotFound_fails() throws CommandException {
        int personId = 999;
        assertCommandFailWithFeedback(() -> new DeleteEventCommand(personId, 1)
                .execute(model), DeleteEventCommand.MESSAGE_PERSON_NOT_FOUND + personId);
    }

    @Test
    public void execute_eventNotFound_fails() throws CommandException {
        int personId = 1;
        int invalidEventId = 99999;
        model.findPersonByUserFriendlyId(personId).addEvent(VALID_EVENT_0);
        assertCommandFailWithFeedback(() -> new DeleteEventCommand(personId, invalidEventId)
                .execute(model), DeleteEventCommand.MESSAGE_EVENT_NOT_FOUND + invalidEventId);
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
