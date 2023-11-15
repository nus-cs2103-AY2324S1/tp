package seedu.cc.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT_EVENT;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_MEDHISTEVENT;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.cc.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.patient.Patient;

class DeleteAppointmentEventCommandTest {

    private final Model model = new ModelManager(getTypicalClinicBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listAppointmentsEventForPatient(patient);
        AppointmentEvent eventToDelete = model.getFilteredAppointmentList().get(INDEX_FIRST_APPOINTMENT_EVENT
                .getZeroBased());
        DeleteAppointmentEventCommand deleteCommand = new DeleteAppointmentEventCommand(INDEX_FIRST_APPOINTMENT_EVENT,
                INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(DeleteAppointmentEventCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                Messages.format(eventToDelete, patient));

        ModelManager expectedModel = new ModelManager(model.getClinicBook(), new UserPrefs());
        Patient expectedPatient = expectedModel.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        expectedModel.listAppointmentsEventForPatient(expectedPatient);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DeleteAppointmentEventCommand deleteCommand = new DeleteAppointmentEventCommand(INDEX_FIRST_MEDHISTEVENT,
                outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_throwsCommandException() {
        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listAppointmentsEventForPatient(patient);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        DeleteAppointmentEventCommand deleteCommand = new DeleteAppointmentEventCommand(outOfBoundIndex,
                INDEX_FIRST_PATIENT);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteAppointmentEventCommand deleteFirstCommand = new DeleteAppointmentEventCommand(INDEX_FIRST_PATIENT,
                INDEX_FIRST_MEDHISTEVENT);
        DeleteAppointmentEventCommand deleteSecondCommand =
                new DeleteAppointmentEventCommand(INDEX_SECOND_PATIENT, INDEX_FIRST_MEDHISTEVENT);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteAppointmentEventCommand deleteFirstCommandCopy =
                new DeleteAppointmentEventCommand(INDEX_FIRST_PATIENT, INDEX_FIRST_MEDHISTEVENT);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different person -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    @Test
    public void toStringMethod() {
        Index eventIndex = Index.fromOneBased(1);
        Index patientIndex = Index.fromOneBased(1);
        DeleteAppointmentEventCommand deleteCommand = new DeleteAppointmentEventCommand(eventIndex, patientIndex);
        String expected = DeleteAppointmentEventCommand.class.getCanonicalName() + "{eventIndex=" + eventIndex
                + ", patientIndex=" + patientIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }
}
