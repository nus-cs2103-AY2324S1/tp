package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.ContactID;

public class ListEventCommandTest {
    private static final Event VALID_EVENT_0 = new Event("Have a meeting", "02:00", "04:00",
            "COM1", "Discuss project");
    private static final Event VALID_EVENT_1 = new Event("Midterm Exam", "02:00", "04:00",
            "MPSH1", "Seat number is xxx.");
    private static final Event VALID_EVENT_2 = new Event("Another Midterm Exam", "04:00", "06:00",
            "MPSH2", "Seat number is xxx.");

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_correctCommand_success() {
        model.findPersonByUserFriendlyId(ContactID.fromInt(1)).addEvent(VALID_EVENT_0);
        model.findPersonByUserFriendlyId(ContactID.fromInt(2)).addEvent(VALID_EVENT_1);
        model.findPersonByUserFriendlyId(ContactID.fromInt(2)).addEvent(VALID_EVENT_2);
        assertCommandSuccess(() -> new ListEventCommand().execute(model));
    }

    private void assertCommandSuccess(ThrowingSupplier<CommandResult> function) {
        try {
            function.get();
        } catch (Throwable e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }
}
