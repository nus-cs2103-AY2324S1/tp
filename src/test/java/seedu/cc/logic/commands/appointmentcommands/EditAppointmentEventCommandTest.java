package seedu.cc.logic.commands.appointmentcommands;

import static seedu.cc.logic.commands.CommandTestUtil.VALID_PRESCRIPTIONS;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_MEDHISTEVENT;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.model.ClinicBook;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.patient.Patient;
import seedu.cc.testutil.AppointmentEventBuilder;
import seedu.cc.testutil.EditAppointmentEventDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditAppointmentEventCommandTest {

    private final Model model = new ModelManager(getTypicalClinicBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        AppointmentEvent editedAppointmentEvent = new AppointmentEventBuilder().build();
        EditAppointmentEventCommand.EditAppointmentEventDescriptor descriptor =
                new EditAppointmentEventDescriptorBuilder(editedAppointmentEvent).build();
        EditAppointmentEventCommand editCommand = new EditAppointmentEventCommand(INDEX_FIRST_MEDHISTEVENT,
                INDEX_FIRST_PATIENT, descriptor);

        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listAppointmentsEventForPatient(patient);

        String expectedMessage = String.format(EditAppointmentEventCommand.MESSAGE_EDIT_EVENT_SUCCESS,
                Messages.format(editedAppointmentEvent, patient));

        Model expectedModel = new ModelManager(new ClinicBook(model.getClinicBook()), new UserPrefs());
        expectedModel.listAppointmentsEventForPatient(patient);
        expectedModel.setAppointmentEventForPatient(patient, model.getFilteredAppointmentList().get(0),
                editedAppointmentEvent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAppointmentEventIndexUnfilteredList_failure() {
        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listAppointmentsEventForPatient(patient);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);


        EditAppointmentEventCommand.EditAppointmentEventDescriptor descriptor =
                new EditAppointmentEventDescriptorBuilder().withPrescription(VALID_PRESCRIPTIONS).build();
        EditAppointmentEventCommand editCommand = new EditAppointmentEventCommand(outOfBoundIndex,
                INDEX_FIRST_PATIENT, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_EVENT_DISPLAYED_INDEX);
    }
}

