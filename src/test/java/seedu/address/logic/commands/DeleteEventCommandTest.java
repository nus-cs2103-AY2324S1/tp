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
import seedu.address.model.event.EventID;
import seedu.address.model.person.ContactID;

public class DeleteEventCommandTest {
    private static final Event VALID_EVENT_0 = new Event("Career Fair",
            "12:00", "14:00",
            "COM1 Level2", "Go to booth #01-01 first");

    private static final Event VALID_EVENT_1 = new Event("Career Fair (Second Day)",
            "2023-11-02 12:00", "2023-11-02 14:00",
            "COM1 Level2", "Go to booth #02-01 first");

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_correctCommand_success() {
        ContactID contactId = ContactID.fromInt(1);
        EventID eventID = EventID.fromInt(1);
        model.addEvent(VALID_EVENT_0, model.findPersonByUserFriendlyId(contactId));
        assertCommandSuccessWithFeedback(() -> new DeleteEventCommand(contactId, eventID)
                .execute(model), DeleteEventCommand.MESSAGE_SUCCESS + eventID + ". " + VALID_EVENT_0.getName());
    }

    @Test
    public void execute_contactNotFound_fails() {
        ContactID contactId = ContactID.fromInt(999);
        EventID eventId = EventID.fromInt(1);
        assertCommandFailWithFeedback(() -> new DeleteEventCommand(contactId, eventId)
                .execute(model), DeleteEventCommand.MESSAGE_PERSON_NOT_FOUND + contactId.getId());
    }

    @Test
    public void execute_eventNotFound_fails() {
        ContactID contactId = ContactID.fromInt(1);
        EventID invalidEventId = EventID.fromInt(99999);
        model.addEvent(VALID_EVENT_1, model.findPersonByUserFriendlyId(contactId));
        assertCommandFailWithFeedback(() -> new DeleteEventCommand(contactId, invalidEventId)
                .execute(model), DeleteEventCommand.MESSAGE_EVENT_NOT_FOUND + invalidEventId);
    }


    private void assertCommandSuccessWithFeedback(ThrowingSupplier<CommandResult> function, String result) {
        CommandResult actualResult = null;
        try {
            actualResult = function.get();
        } catch (Throwable e) {
            throw new AssertionError("Execution of command should not fail, but caught: " + e);
        }
        assertEquals(new CommandResult(result), actualResult);
    }

    private void assertCommandFailWithFeedback(ThrowingSupplier<CommandResult> function, String errResult) {
        try {
            function.get();
        } catch (Throwable e) {
            if (!(e instanceof CommandException)) {
                throw new AssertionError("Execution of command failed but not due to CommandException. " + e);
            }
            assertEquals(e.getMessage(), errResult);
            return;
        }
        throw new AssertionError("Execution of command should fail.");
    }
}
