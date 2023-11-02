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
import seedu.address.model.tag.PriorityTag;
import seedu.address.testutil.AppointmentBuilder;

public class TriageCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexValidPriorityUnfilteredList_success() {
        Appointment appointmentToTriage = model.getFilteredAppointmentList()
                .get(INDEX_FIRST_APPOINTMENT.getZeroBased());
        Appointment triagedAppointment = new AppointmentBuilder(appointmentToTriage).withPriorityTag("low").build();

        TriageCommand triageCommand = new TriageCommand(INDEX_FIRST_APPOINTMENT, triagedAppointment.getPriorityTag());
        String expectedMessage = String.format(TriageCommand.MESSAGE_SUCCESS,
                Messages.format(triagedAppointment));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        expectedModel.setAppointment(appointmentToTriage, triagedAppointment);

        assertCommandSuccess(triageCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        PriorityTag newPriorityTag = new PriorityTag("low");
        TriageCommand triageCommand = new TriageCommand(outOfBoundIndex, newPriorityTag);

        assertCommandFailure(triageCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexValidPriorityFilteredList_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getUserHistoryManager());
        showAppointmentAtIndex(model, INDEX_SECOND_APPOINTMENT);

        Appointment appointmentToTriage = model.getFilteredAppointmentList()
                .get(INDEX_FIRST_APPOINTMENT.getZeroBased());
        Appointment triagedAppointment = new AppointmentBuilder(appointmentToTriage).withPriorityTag("medium").build();

        TriageCommand triageCommand = new TriageCommand(INDEX_FIRST_APPOINTMENT, triagedAppointment.getPriorityTag());
        String expectedMessage = String.format(TriageCommand.MESSAGE_SUCCESS,
                Messages.format(triagedAppointment));
        expectedModel.setAppointment(appointmentToTriage, triagedAppointment);

        assertCommandSuccess(triageCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);
        Index outOfBoundIndex = Index.fromOneBased(2);
        PriorityTag newPriorityTag = new PriorityTag("medium");
        TriageCommand triageCommand = new TriageCommand(outOfBoundIndex, newPriorityTag);

        assertCommandFailure(triageCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }
    @Test
    public void equals() {
        Appointment appointment1 = model.getFilteredAppointmentList()
                .get(INDEX_FIRST_APPOINTMENT.getZeroBased());
        Appointment appointment2 = model.getFilteredAppointmentList()
                .get(INDEX_SECOND_APPOINTMENT.getZeroBased());

        PriorityTag priorityTag1 = appointment1.getPriorityTag();
        PriorityTag priorityTag2 = appointment2.getPriorityTag();

        TriageCommand triageFirstCommand = new TriageCommand(INDEX_FIRST_APPOINTMENT, priorityTag1);
        TriageCommand triageSecondCommand = new TriageCommand(INDEX_SECOND_APPOINTMENT, priorityTag2);

        // same object -> returns true
        assertTrue(triageFirstCommand.equals(triageFirstCommand));

        // same values -> returns true
        TriageCommand triageFirstCommandCopy = new TriageCommand(INDEX_FIRST_APPOINTMENT, priorityTag1);
        assertTrue(triageFirstCommand.equals(triageFirstCommandCopy));

        // different types -> returns false
        assertFalse(triageFirstCommand.equals(1));

        // null -> returns false
        assertFalse(triageFirstCommand.equals(null));

        // different appointment -> returns false
        assertFalse(triageFirstCommand.equals(triageSecondCommand));
    }
}
