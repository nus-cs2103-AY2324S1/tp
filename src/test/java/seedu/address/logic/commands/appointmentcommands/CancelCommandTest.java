package seedu.address.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;

public class CancelCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Appointment appointmentToCancel = model.getFilteredAppointmentList()
                .get(INDEX_FIRST_APPOINTMENT.getZeroBased());

        CancelCommand cancelCommand = new CancelCommand(INDEX_FIRST_APPOINTMENT);

        String expectedMessage = String.format(CancelCommand.MESSAGE_CANCEL_APPOINTMENT_SUCCESS,
                Messages.format(appointmentToCancel));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteAppointment(appointmentToCancel);

        assertCommandSuccess(cancelCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        CancelCommand cancelCommand = new CancelCommand(outOfBoundIndex);

        assertCommandFailure(cancelCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);

        Appointment appointmentToCancel = model.getFilteredAppointmentList()
                .get(INDEX_FIRST_APPOINTMENT.getZeroBased());

        CancelCommand cancelCommand = new CancelCommand(INDEX_FIRST_APPOINTMENT);

        String expectedMessage = String.format(CancelCommand.MESSAGE_CANCEL_APPOINTMENT_SUCCESS,
                Messages.format(appointmentToCancel));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteAppointment(appointmentToCancel);
        showNoAppointment(expectedModel);

        assertCommandSuccess(cancelCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);

        Index outOfBoundIndex = INDEX_SECOND_APPOINTMENT;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getAppointmentList().size());

        CancelCommand cancelCommand = new CancelCommand(outOfBoundIndex);

        assertCommandFailure(cancelCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CancelCommand cancelFirstCommand = new CancelCommand(INDEX_FIRST_APPOINTMENT);
        CancelCommand cancelSecondCommand = new CancelCommand(INDEX_SECOND_APPOINTMENT);

        // same object -> returns true
        assertTrue(cancelFirstCommand.equals(cancelFirstCommand));

        // same values -> returns true
        CancelCommand cancelFirstCommandCopy = new CancelCommand(INDEX_FIRST_APPOINTMENT);
        assertTrue(cancelFirstCommand.equals(cancelFirstCommandCopy));

        // different types -> returns false
        assertFalse(cancelFirstCommand.equals(1));

        // null -> returns false
        assertFalse(cancelFirstCommand.equals(null));

        // different appointment -> returns false
        assertFalse(cancelFirstCommand.equals(cancelSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no appointment.
     */
    private void showNoAppointment(Model model) {
        model.updateFilteredAppointmentList(p -> false);

        assertTrue(model.getFilteredAppointmentList().isEmpty());
    }
}
