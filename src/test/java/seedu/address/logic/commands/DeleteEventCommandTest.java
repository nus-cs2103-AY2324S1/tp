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
    private static final Event VALID_EVENT_0 = new Event("Have a meeting", "2:00", "4:00",
            "COM1", "Discuss project");
    private static final Event VALID_EVENT_1 = new Event("Have a meeting again", "5:00", "7:00",
            "COM1", "Discuss project again");

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_correctCommand_success() throws CommandException {
        String personName = "Benson Meier";
        model.findPersonByName(personName).addEvent(VALID_EVENT_0);
        assertCommandSuccessWithFeedback(() -> new DeleteEventCommand(personName, VALID_EVENT_0.getName())
                .execute(model), DeleteEventCommand.MESSAGE_SUCCESS + VALID_EVENT_0.getName());
    }

    @Test
    public void execute_personNotFound_fails() throws CommandException {
        String personName = "unkonwn person";
        assertCommandFailWithFeedback(() -> new DeleteEventCommand(personName, VALID_EVENT_0.getName())
                .execute(model), DeleteEventCommand.MESSAGE_PERSON_NOT_FOUNT + personName);
    }

    @Test
    public void execute_eventNotFound_fails() throws CommandException {
        String personName = "Benson Meier";
        model.findPersonByName(personName).addEvent(VALID_EVENT_0);
        assertCommandFailWithFeedback(() -> new DeleteEventCommand(personName, VALID_EVENT_1.getName())
                .execute(model), DeleteEventCommand.MESSAGE_EVENT_NOT_FOUND + VALID_EVENT_1.getName());
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
