package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.util.SampleDataUtil.getSampleAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAppointmentCommand}.
 */
public class DeleteAppointmentCommandTest {

    private Model model = new ModelManager(getSampleAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Appointment AppointmentToDelete = model.getFilteredAppointmentList().get(0);
        int AppointmentToDeleteIndex = 1;
        DeleteAppointmentCommand DeleteAppointmentCommand = new DeleteAppointmentCommand(AppointmentToDeleteIndex);

        String expectedMessage = String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                Messages.format(AppointmentToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteAppointment(AppointmentToDelete);

        assertCommandSuccess(DeleteAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        DeleteAppointmentCommand DeleteAppointmentCommand = new DeleteAppointmentCommand(4);
        DeleteAppointmentCommand DeleteAppointmentCommand2 = new DeleteAppointmentCommand(-1);

        assertCommandFailure(DeleteAppointmentCommand, model, Messages.MESSAGE_APPOINTMENT_NOT_FOUND);
        assertCommandFailure(DeleteAppointmentCommand2, model, Messages.MESSAGE_APPOINTMENT_NOT_FOUND);
    }

    @Test
    public void equals() {
        DeleteAppointmentCommand deleteFirstCommand = new DeleteAppointmentCommand(1);
        DeleteAppointmentCommand deleteSecondCommand = new DeleteAppointmentCommand(2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAppointmentCommand deleteFirstCommandCopy = new DeleteAppointmentCommand(1);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different Appointment -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        DeleteAppointmentCommand DeleteAppointmentCommand = new DeleteAppointmentCommand(1);
        String expected = DeleteAppointmentCommand.class.getCanonicalName() + "{targetIc=" + 1 + "}";
        assertEquals(expected, DeleteAppointmentCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAppointment(Model model) {
        model.updateFilteredAppointmentList(p -> false);

        assertTrue(model.getFilteredAppointmentList().isEmpty());
    }
}

