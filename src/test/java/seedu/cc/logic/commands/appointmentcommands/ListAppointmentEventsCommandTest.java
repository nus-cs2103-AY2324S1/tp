package seedu.cc.logic.commands.appointmentcommands;

import static seedu.cc.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.patient.Patient;

public class ListAppointmentEventsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClinicBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getClinicBook(), new UserPrefs());
    }

    @Test
    public void execute_showsEverything() {
        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listAppointmentsEventForPatient(patient);

        String expectedMessage = String.format(ListAppointmentEventsCommand.MESSAGE_SUCCESS,
                Messages.format(patient));
        assertCommandSuccess(new ListAppointmentEventsCommand(INDEX_FIRST_PATIENT), model,
                expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        ListAppointmentEventsCommand listAppointmentEventsCommand =
                new ListAppointmentEventsCommand(outOfBoundIndex);

        assertCommandFailure(listAppointmentEventsCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }
}
