package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventTime;
import seedu.address.model.person.ContactID;

public class ListEventCommandTest {
    private static final Event VALID_EVENT_0 = new Event("Have a meeting", "02:00", "04:00",
            "COM1", "Discuss project");
    private static final Event VALID_EVENT_1 = new Event("Midterm Exam", "04:01", "06:00",
            "MPSH1", "Seat number is xxx.");
    private static final Event VALID_EVENT_2 = new Event("Another Midterm Exam", "06:01", "08:00",
            "MPSH2", "Seat number is xxx.");

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_correctCommandFullList_success() {
        model.findPersonByUserFriendlyId(ContactID.fromInt(1)).addEvent(VALID_EVENT_0);
        model.findPersonByUserFriendlyId(ContactID.fromInt(2)).addEvent(VALID_EVENT_1);
        model.findPersonByUserFriendlyId(ContactID.fromInt(2)).addEvent(VALID_EVENT_2);
        assertCommandSuccess(() -> new ListEventCommand(null,
                null, true).execute(model));
    }

    @Test
    public void execute_correctCommandFiltered_success() {
        model.addEvent(VALID_EVENT_0, model.findPersonByUserFriendlyId(ContactID.fromInt(1)));
        model.addEvent(VALID_EVENT_1, model.findPersonByUserFriendlyId(ContactID.fromInt(2)));
        model.addEvent(VALID_EVENT_2, model.findPersonByUserFriendlyId(ContactID.fromInt(2)));
        assertCommandSuccess(() -> new ListEventCommand(EventTime.fromString("03:00"),
                EventTime.fromString("05:00"), true).execute(model));
    }

    @Test
    public void execute_correctCommandFilteredDescending_success() {
        model.addEvent(VALID_EVENT_0, model.findPersonByUserFriendlyId(ContactID.fromInt(1)));
        model.addEvent(VALID_EVENT_1, model.findPersonByUserFriendlyId(ContactID.fromInt(2)));
        model.addEvent(VALID_EVENT_2, model.findPersonByUserFriendlyId(ContactID.fromInt(2)));
        assertCommandSuccess(() -> new ListEventCommand(EventTime.fromString("03:00"),
                EventTime.fromString("05:00"), false).execute(model));
    }

    private void assertCommandSuccess(ThrowingSupplier<CommandResult> function) {
        try {
            function.get();
        } catch (Throwable e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }
}
