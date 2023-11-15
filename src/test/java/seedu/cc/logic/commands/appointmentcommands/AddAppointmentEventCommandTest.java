package seedu.cc.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cc.testutil.Assert.assertThrows;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.patient.Patient;
import seedu.cc.testutil.AppointmentEventBuilder;

public class AddAppointmentEventCommandTest {

    private final Model model = new ModelManager(getTypicalClinicBook(), new UserPrefs());

    @Test
    public void constructor_nullEventToAdd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentEventCommand(Index.fromOneBased(1),
                null));
    }

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        AppointmentEvent validEvent = new AppointmentEventBuilder().build();
        assertThrows(NullPointerException.class, () -> new AddAppointmentEventCommand(null, validEvent));
    }

    @Test
    public void execute_medicalHistoryEventAcceptedByModel_addSuccessful() throws Exception {
        AppointmentEvent validEvent = new AppointmentEventBuilder().build();

        CommandResult commandResult = new AddAppointmentEventCommand(INDEX_FIRST_PATIENT, validEvent).execute(model);
        Patient resultPatient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());

        assertEquals(String.format(AddAppointmentEventCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                        Messages.format(validEvent, resultPatient)),
                commandResult.getFeedbackToUser());
    }

}
