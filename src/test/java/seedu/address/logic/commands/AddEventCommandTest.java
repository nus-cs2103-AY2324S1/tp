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

public class AddEventCommandTest {

    private final Event VALID_EVENT_0 = new Event("Have a meeting", "2:00", "4:00","COM1",
            "Discuss project");
    private final Event VALID_EVENT_SAME_NAME_0 = new Event("Have a meeting", "5:00", "7:00",
            "COM1", "Discuss project again");

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_correctCommand_success() throws CommandException {
        String personName = "Benson Meier";
        assertCommandSuccessWithFeedback(() -> new AddEventCommand(personName, VALID_EVENT_0)
                .execute(model), AddEventCommand.MESSAGE_SUCCESS + VALID_EVENT_0.getName());
    }

    @Test
    public void execute_duplicateEvent_fails() throws CommandException {
        String personName = "George Best";
        assertCommandSuccessWithFeedback(() -> new AddEventCommand(personName, VALID_EVENT_0)
                .execute(model), AddEventCommand.MESSAGE_SUCCESS + VALID_EVENT_0.getName());
        assertCommandFailWithFeedback(() -> new AddEventCommand(personName, VALID_EVENT_SAME_NAME_0)
                .execute(model), AddEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_personNotExist_fails() throws CommandException {
        String unknownPersonName = "unknown person";
        assertCommandFailWithFeedback(() -> new AddEventCommand(unknownPersonName, VALID_EVENT_SAME_NAME_0)
                .execute(model), AddEventCommand.MESSAGE_PERSON_NOT_FOUNT + unknownPersonName);
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
            if ( !(e instanceof CommandException)) {
                throw new AssertionError("Execution of command failed but not due to CommandException.");
            }
            assertEquals(e.getMessage(), errResult);
            return;
        }
        throw new AssertionError("Execution of command should fail.");
    }
}
