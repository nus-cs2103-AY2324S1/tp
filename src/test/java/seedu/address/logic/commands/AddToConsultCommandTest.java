package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddToConsultCommand.createUpdatedConsultation;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalConsultations.getTypicalConsultationListBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONSULTATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONSULTATION;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddToConsultCommand.AddToConsultationDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.GradedTestListBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SessionListBook;
import seedu.address.model.TaskListBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.consultation.Consultation;
import seedu.address.testutil.AddToConsultationDescriptorBuilder;
import seedu.address.testutil.TypicalConsultations;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddToConsultCommand.
 */
public class AddToConsultCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskListBook(),
            new SessionListBook(), getTypicalConsultationListBook(), new GradedTestListBook());

    @Test
    public void execute_addToConsultation_success() {
        Index targetIndex = INDEX_FIRST_CONSULTATION;
        Index toAddIndex = INDEX_SECOND_CONSULTATION;
        List<Consultation> lastShownList = model.getFilteredConsultationList();
        Consultation targetConsultation = lastShownList.get(targetIndex.getZeroBased());
        Consultation consultationToAddStudent = lastShownList.get(toAddIndex.getZeroBased());
        AddToConsultationDescriptor descriptor = new AddToConsultationDescriptorBuilder(consultationToAddStudent)
                .build();
        AddToConsultCommand command = new AddToConsultCommand(targetIndex, descriptor);
        Consultation updatedConsultation = createUpdatedConsultation(model, targetConsultation, descriptor);

        String expectedMessage = String.format(AddToConsultCommand.MESSAGE_SUCCESS, targetIndex.getOneBased(),
                Messages.format(updatedConsultation));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new TaskListBook(), new SessionListBook(),
                getTypicalConsultationListBook(), new GradedTestListBook());
        expectedModel.setConsultation(model.getFilteredConsultationList().get(0), updatedConsultation);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateConsultation_failure() {
        Consultation first = model.getFilteredConsultationList().get(INDEX_FIRST_CONSULTATION.getZeroBased());
        AddToConsultationDescriptor descriptor = new AddToConsultationDescriptorBuilder(first).build();
        AddToConsultCommand command = new AddToConsultCommand(INDEX_FIRST_CONSULTATION, descriptor);

        assertCommandFailure(command, model, AddToConsultCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_personNotFound_failure() {
        AddToConsultationDescriptor descriptor = new AddToConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION_UNKNOWN_PERSON).build();
        AddToConsultCommand command = new AddToConsultCommand(INDEX_FIRST_CONSULTATION, descriptor);
        assertCommandFailure(command, model, AddToConsultCommand.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredConsultationList().size() + 1);
        AddToConsultationDescriptor descriptor = new AddToConsultationDescriptorBuilder().build();
        AddToConsultCommand command = new AddToConsultCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_CONSULTATION_DISPLAYED_INDEX);
    }

    @Test
    public void getCommandType() {
        AddToConsultationDescriptor descriptor = new AddToConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION_UNKNOWN_PERSON).build();
        AddToConsultCommand command = new AddToConsultCommand(INDEX_FIRST_CONSULTATION, descriptor);

        assertEquals(command.getCommandType(), CommandType.ADD_TO_CONSULT);
    }

    @Test
    public void equals() {
        final AddToConsultationDescriptor descriptor = new AddToConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION1).build();
        final AddToConsultationDescriptor otherDescriptor = new AddToConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION2).build();
        final AddToConsultCommand standardCommand = new AddToConsultCommand(INDEX_FIRST_CONSULTATION, descriptor);

        // same values -> returns true
        AddToConsultationDescriptor copyDescriptor = new AddToConsultationDescriptor(descriptor);
        AddToConsultCommand commandWithSameValues = new AddToConsultCommand(INDEX_FIRST_CONSULTATION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddToConsultCommand(INDEX_SECOND_CONSULTATION, descriptor)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddToConsultCommand(INDEX_FIRST_CONSULTATION, otherDescriptor)));

    }
}
