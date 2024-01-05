package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RemoveFromConsultCommand.createUpdatedConsultation;
import static seedu.address.testutil.TypicalConsultations.getTypicalConsultationListBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONSULTATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONSULTATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_CONSULTATION;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.RemoveFromConsultCommand.RemoveFromConsultationDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.GradedTestListBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SessionListBook;
import seedu.address.model.TaskListBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.consultation.Consultation;
import seedu.address.testutil.RemoveFromConsultationDescriptorBuilder;
import seedu.address.testutil.TypicalConsultations;


/**
 * Contains integration tests (interaction with the Model) and unit tests for RemoveFromConsultCommand.
 */
public class RemoveFromConsultCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskListBook(),
            new SessionListBook(), getTypicalConsultationListBook(), new GradedTestListBook());

    @Test
    public void execute_removeFromConsultation_success() {
        Index targetIndex = INDEX_THIRD_CONSULTATION;
        List<Consultation> lastShownList = model.getFilteredConsultationList();
        Consultation targetConsultation = lastShownList.get(targetIndex.getZeroBased());
        Consultation consultationToRemoveStudent = TypicalConsultations.CONSULTATION_WITH_STUDENTS_TO_REMOVE;
        RemoveFromConsultationDescriptor descriptor = new RemoveFromConsultationDescriptorBuilder(
                consultationToRemoveStudent).build();
        RemoveFromConsultCommand command = new RemoveFromConsultCommand(targetIndex, descriptor);
        Consultation updatedConsultation = createUpdatedConsultation(model, targetConsultation, descriptor);

        String expectedMessage = String.format(RemoveFromConsultCommand.MESSAGE_SUCCESS, targetIndex.getOneBased(),
                Messages.format(updatedConsultation));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new TaskListBook(), new SessionListBook(), getTypicalConsultationListBook(), new GradedTestListBook());

        expectedModel.setConsultation(model.getFilteredConsultationList().get(targetIndex.getZeroBased()),
                updatedConsultation);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_personNotFoundInAddressBook_failure() {
        RemoveFromConsultationDescriptor descriptor = new RemoveFromConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION_UNKNOWN_PERSON).build();
        RemoveFromConsultCommand command = new RemoveFromConsultCommand(INDEX_FIRST_CONSULTATION, descriptor);

        assertCommandFailure(command, model, RemoveFromConsultCommand.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_personNotFoundInConsultation_failure() {
        // Consultation index without Fiona
        Index targetIndex = INDEX_SECOND_CONSULTATION;
        List<Consultation> lastShownList = model.getFilteredConsultationList();
        // Consultation with Fiona
        RemoveFromConsultationDescriptor descriptor = new RemoveFromConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION1).build();
        // Consultation without Fiona
        Consultation targetConsultation = lastShownList.get(targetIndex.getZeroBased());
        RemoveFromConsultCommand command = new RemoveFromConsultCommand(targetIndex, descriptor);

        assertCommandFailure(command, model, RemoveFromConsultCommand.MESSAGE_NOT_FOUND_IN_CONSULT);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredConsultationList().size() + 1);
        RemoveFromConsultationDescriptor descriptor = new RemoveFromConsultationDescriptorBuilder().build();
        RemoveFromConsultCommand command = new RemoveFromConsultCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_CONSULTATION_DISPLAYED_INDEX);
    }

    @Test
    public void getCommandType() {
        Consultation consultationToRemoveStudent = TypicalConsultations.CONSULTATION_WITH_STUDENTS_TO_REMOVE;
        RemoveFromConsultationDescriptor descriptor = new RemoveFromConsultationDescriptorBuilder(
                consultationToRemoveStudent).build();
        RemoveFromConsultCommand command = new RemoveFromConsultCommand(INDEX_FIRST_CONSULTATION, descriptor);

        assertEquals(command.getCommandType(), CommandType.REMOVE_FROM_CONSULT);
    }

    @Test
    public void equals() {
        final RemoveFromConsultationDescriptor descriptor = new RemoveFromConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION1).build();
        final RemoveFromConsultationDescriptor otherDescriptor = new RemoveFromConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION2).build();
        final RemoveFromConsultCommand standardCommand = new RemoveFromConsultCommand(INDEX_FIRST_CONSULTATION,
                descriptor);

        // same values -> returns true
        RemoveFromConsultationDescriptor copyDescriptor = new RemoveFromConsultationDescriptor(descriptor);
        RemoveFromConsultCommand commandWithSameValues = new RemoveFromConsultCommand(INDEX_FIRST_CONSULTATION,
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemoveFromConsultCommand(INDEX_SECOND_CONSULTATION, descriptor)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new RemoveFromConsultCommand(INDEX_FIRST_CONSULTATION, otherDescriptor)));

    }
}
