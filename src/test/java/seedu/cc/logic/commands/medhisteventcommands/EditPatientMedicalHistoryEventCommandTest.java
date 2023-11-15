package seedu.cc.logic.commands.medhisteventcommands;

import static seedu.cc.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_CANCER;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_MEDHISTEVENT;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.Messages;
import seedu.cc.logic.commands.medhisteventcommands.EditMedicalHistoryEventCommand.EditMedicalHistoryEventDescriptor;
import seedu.cc.model.ClinicBook;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;
import seedu.cc.testutil.EditMedicalHistoryEventDescriptorBuilder;
import seedu.cc.testutil.MedicalHistoryEventBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditPatientMedicalHistoryEventCommandTest {

    private final Model model = new ModelManager(getTypicalClinicBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        MedicalHistoryEvent editedMedicalHistoryEvent = new MedicalHistoryEventBuilder().build();
        EditMedicalHistoryEventDescriptor descriptor =
                new EditMedicalHistoryEventDescriptorBuilder(editedMedicalHistoryEvent).build();
        EditMedicalHistoryEventCommand editCommand = new EditMedicalHistoryEventCommand(INDEX_FIRST_MEDHISTEVENT,
                INDEX_FIRST_PATIENT, descriptor);

        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listMedicalHistoryEvents(patient);

        String expectedMessage = String.format(EditMedicalHistoryEventCommand.MESSAGE_EDIT_EVENT_SUCCESS,
                Messages.format(editedMedicalHistoryEvent, patient));

        Model expectedModel = new ModelManager(new ClinicBook(model.getClinicBook()), new UserPrefs());
        expectedModel.listMedicalHistoryEvents(patient);
        expectedModel.setMedicalHistoryEvent(patient, model.getFilteredMedicalHistoryEventList().get(0),
                editedMedicalHistoryEvent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }



    @Test
    public void execute_invalidMedicalHistoryEventIndexUnfilteredList_failure() {
        Patient patient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.listMedicalHistoryEvents(patient);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMedicalHistoryEventList().size() + 1);


        EditMedicalHistoryEventDescriptor descriptor = new EditMedicalHistoryEventDescriptorBuilder()
                .withMedicalCondition(VALID_MEDICAL_CONDITION_CANCER).build();
        EditMedicalHistoryEventCommand editCommand = new EditMedicalHistoryEventCommand(outOfBoundIndex,
                INDEX_FIRST_PATIENT, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MEDICAL_HISTORY_EVENT_DISPLAYED_INDEX);
    }
}

