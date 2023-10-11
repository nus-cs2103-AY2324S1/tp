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

public class ListEventCommandTest {
    private static final Event VALID_EVENT_0 = new Event("Have a meeting", "2:00", "4:00",
            "COM1", "Discuss project");
    private static final Event VALID_EVENT_1 = new Event("Midterm Exam", "2:00", "4:00",
            "MPSH1", "Seat number is xxx.");
    private static final Event VALID_EVENT_2 = new Event("Another Midterm Exam", "4:00", "6:00",
            "MPSH2", "Seat number is xxx.");

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_correctCommand_success() throws CommandException {
        model.findPersonByName("Benson Meier").addEvent(VALID_EVENT_0);
        model.findPersonByName("George Best").addEvent(VALID_EVENT_1);
        model.findPersonByName("George Best").addEvent(VALID_EVENT_2);
        assertCommandSuccessWithFeedback(() -> new ListEventCommand()
                .execute(model), ListEventCommand.MESSAGE
                + "[Benson Meier] " + VALID_EVENT_0.getUiText() + "\n"
                + "[George Best] " + VALID_EVENT_1.getUiText() + "\n"
                + "[George Best] " + VALID_EVENT_2.getUiText() + "\n"
        );
    }

    private void assertCommandSuccessWithFeedback(ThrowingSupplier<CommandResult> function, String result) {
        try {
            assertEquals(function.get(), new CommandResult(result));
        } catch (Throwable e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }
}
