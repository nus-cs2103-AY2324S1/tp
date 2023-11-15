package seedu.cc.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT_EVENT;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.appointment.Prescription;
import seedu.cc.model.patient.Patient;
import seedu.cc.testutil.EditAppointmentEventDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddPrescriptionCommand.
 */
public class AddPrescriptionCommandTest {

    private final Model model = new ModelManager(getTypicalClinicBook(), new UserPrefs());


    @Test
    public void execute_validIndexUnfilteredList_success() {
        // Assuming INDEX_FIRST_PATIENT is a valid index in the typical clinic book
        Patient selectedPatient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());


        AppointmentEvent firstAppointmentEvent = selectedPatient.getAppointmentList().getAppointmentList().get(0);

        Set<Prescription> newPrescriptions = new HashSet<>();
        newPrescriptions.add(new Prescription("NewMedicine"));

        EditAppointmentEventCommand.EditAppointmentEventDescriptor descriptor =
                new EditAppointmentEventDescriptorBuilder().withPrescriptions(newPrescriptions).build();

        AddPrescriptionCommand addPrescriptionCommand = new AddPrescriptionCommand(
                Index.fromZeroBased(0),
                INDEX_FIRST_PATIENT,
                descriptor);

        String expectedMessage = String.format(AddPrescriptionCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                firstAppointmentEvent);

        try {
            assertEquals(expectedMessage, addPrescriptionCommand.execute(model).getFeedbackToUser());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void execute_invalidPatientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundPatientIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        AddPrescriptionCommand addPrescriptionCommand = new AddPrescriptionCommand(INDEX_FIRST_APPOINTMENT_EVENT,
                outOfBoundPatientIndex, new EditAppointmentEventDescriptorBuilder().build());

        assertCommandFailure(addPrescriptionCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidAppointmentIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundAppointmentIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        AddPrescriptionCommand addPrescriptionCommand = new AddPrescriptionCommand(outOfBoundAppointmentIndex,
                INDEX_FIRST_PATIENT, new EditAppointmentEventDescriptorBuilder().build());

        assertCommandFailure(addPrescriptionCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_EVENT_DISPLAYED_INDEX);
    }
}
