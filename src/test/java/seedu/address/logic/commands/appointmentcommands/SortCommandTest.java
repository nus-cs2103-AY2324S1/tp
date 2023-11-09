package seedu.address.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class SortCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_sortList_success() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.sortAppointmentList(true, "time");

        assertCommandSuccess(new SortCommand(true, "time"),
                model, String.format(SortCommand.MESSAGE_SUCCESS, "ascending", "time"), expectedModel);
    }
    @Test
    public void equals() {
        SortCommand sortFirstCommand = new SortCommand(true, "time");
        SortCommand sortSecondCommand = new SortCommand(false, "priority");

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(true, "time");
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different appointment -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }
}
