package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteCommand.DeletePersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Name defaultName = new Name(PersonBuilder.DEFAULT_NAME);

    private Id defaultId = new Id(PersonBuilder.DEFAULT_ID);

    private DeletePersonDescriptor defaultDescriptor = new DeletePersonDescriptor();

    @Test
    public void execute_validNameUnfilteredList_success() {
        Person personToDelete = new PersonBuilder().withName("Amy Bee").build();
        model.addPerson(personToDelete);
        Name name = personToDelete.getName();
        DeleteCommand deleteCommand = new DeleteCommand(null, name, defaultDescriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIdUnfilteredList_success() {
        Person personToDelete = new PersonBuilder().withId("S1234567E").build();
        model.addPerson(personToDelete);
        Id id = personToDelete.getId();
        DeleteCommand deleteCommand = new DeleteCommand(id, null, defaultDescriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        Name invalidName = new Name("Does Not Exist");
        DeleteCommand command = new DeleteCommand(null, invalidName, new DeletePersonDescriptor());

        assertThrows(CommandException.class, DeleteCommand.MESSAGE_PATIENT_NOT_FOUND, () -> command.execute(model));
    }

    @Test
    public void execute_invalidId_throwsCommandException() {
        Id invalidId = new Id("S000000X");
        DeleteCommand command = new DeleteCommand(invalidId, null, new DeletePersonDescriptor());

        assertThrows(CommandException.class, DeleteCommand.MESSAGE_PATIENT_NOT_FOUND, () -> command.execute(model));
    }

    @Test
    public void execute_deleteAppointmentField_success() throws CommandException {
        Person firstPerson = model.getFilteredPersonList().get(0);

        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        descriptor.setDeleteAppointment();

        DeleteCommand command = new DeleteCommand(firstPerson.getId(), null, descriptor);
        command.execute(model);

        Person editedPerson = model.getFilteredPersonList().get(0);
        assertTrue(editedPerson.getAppointment().isEmpty());
    }

    @Test
    public void execute_deleteMedicalHistoryField_success() throws CommandException {
        Person firstPerson = model.getFilteredPersonList().get(0);

        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        descriptor.setDeleteMedicalHistory();
        descriptor.setMedicalHistory(new HashSet<>());

        DeleteCommand deleteCommand = new DeleteCommand(firstPerson.getId(), null, descriptor);
        deleteCommand.execute(model);

        Person editedPerson = model.getFilteredPersonList().get(0);
        assertTrue(editedPerson.getMedicalHistories().isEmpty());

    }

    @Test
    public void execute_deleteSpecificMedicalHistoryField_success() throws CommandException {
        Person secondPerson = model.getFilteredPersonList().get(1);
        MedicalHistory medicalHistory1 = new MedicalHistory("diabetes");
        MedicalHistory medicalHistory2 = new MedicalHistory("high-risk");
        assertTrue(secondPerson.getMedicalHistories().contains(medicalHistory1));

        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        Set<MedicalHistory> medicalHistories = new HashSet<>();
        medicalHistories.add(medicalHistory1);
        descriptor.setDeleteMedicalHistory();
        descriptor.setMedicalHistory(medicalHistories);

        DeleteCommand deleteCommand = new DeleteCommand(secondPerson.getId(), null, descriptor);
        deleteCommand.execute(model);

        Person editedPerson = model.getFilteredPersonList().get(1);
        assertTrue(editedPerson.getMedicalHistories().contains(medicalHistory2));
        assertFalse(editedPerson.getMedicalHistories().contains(medicalHistory1));

    }

    @Test
    public void execute_patientNoAppointment() throws CommandException {
        Person personToDelete = new PersonBuilder().withoutAppointment().build();
        model.addPerson(personToDelete);
        Name name = personToDelete.getName();
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        descriptor.setDeleteAppointment();
        DeleteCommand deleteCommand = new DeleteCommand(null, name, descriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_NO_APPOINTMENT_TO_DELETE,
                Messages.format(personToDelete));

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_patientNoMedicalHistory() throws CommandException {
        Person personToDelete = new PersonBuilder().build();
        model.addPerson(personToDelete);
        Name name = personToDelete.getName();
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        descriptor.setDeleteMedicalHistory();
        DeleteCommand deleteCommand = new DeleteCommand(null, name, descriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_NO_MEDICAL_HISTORY_TO_DELETE,
                Messages.format(personToDelete));

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_patientNoMedicalHistorySpecified() throws CommandException {
        Person secondPerson = model.getFilteredPersonList().get(1);
        MedicalHistory medicalHistory1 = new MedicalHistory("ABC disease");
        assertFalse(secondPerson.getMedicalHistories().contains(medicalHistory1));

        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        Set<MedicalHistory> medicalHistories = new HashSet<>();
        medicalHistories.add(medicalHistory1);
        descriptor.setDeleteMedicalHistory();
        descriptor.setMedicalHistory(medicalHistories);

        DeleteCommand deleteCommand = new DeleteCommand(secondPerson.getId(), null, descriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_INVALID_MEDICAL_HISTORY,
                Messages.format(secondPerson));

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void deletePersonDescriptor_setterMethods() {
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();

        descriptor.setDeleteAppointment();
        descriptor.setDeleteMedicalHistory();

        assertTrue(descriptor.shouldDeleteAppointment());
        assertTrue(descriptor.shouldDeleteMedicalHistory());
    }

    @Test
    public void deletePersonDescriptor_isAllFalse() {
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();

        assertTrue(descriptor.isAllFalse());

        descriptor.setDeleteAppointment();

        assertFalse(descriptor.isAllFalse());
    }

    @Test
    public void deletePersonDescriptor_getMedicalHistories() {
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();

        Set<MedicalHistory> medicalHistories = new HashSet<>();
        medicalHistories.add(new MedicalHistory("diabetes"));
        medicalHistories.add(new MedicalHistory("high-risk"));
        Set<MedicalHistory> medicalHistoriesCopy = new HashSet<>(medicalHistories);

        descriptor.setMedicalHistory(medicalHistories);

        assertEquals(medicalHistoriesCopy, descriptor.getMedicalHistories());
    }

    @Test
    public void equalsDeleteCommand() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(defaultId, null, defaultDescriptor);
        DeleteCommand deleteSecondCommand = new DeleteCommand(null, defaultName, defaultDescriptor);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(defaultId, null, defaultDescriptor);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // different name -> returns false
        DeleteCommand deleteThirdCommand = new DeleteCommand(null, new Name("Bob"), defaultDescriptor);
        assertFalse(deleteSecondCommand.equals(deleteThirdCommand));
    }

    @Test
    public void equalsDeletePersonDescriptor() {
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();

        // same object -> returns true
        assertTrue(descriptor.equals(descriptor));

        // same values -> returns true
        DeletePersonDescriptor descriptorCopy = new DeletePersonDescriptor();
        assertTrue(descriptor.equals(descriptorCopy));

        // different types -> returns false
        assertFalse(descriptor.equals(1));

        // null -> returns false
        assertFalse(descriptor.equals(null));

        // different person -> returns false
        DeletePersonDescriptor descriptorDifferent = new DeletePersonDescriptor();
        descriptorDifferent.setDeleteAppointment();
        assertFalse(descriptor.equals(descriptorDifferent));
    }

    @Test
    public void toStringMethod() {
        DeleteCommand deleteCommand = new DeleteCommand(defaultId, defaultName, defaultDescriptor);
        String expected = DeleteCommand.class.getCanonicalName() + "{id=" + defaultId + ", " + "name=" + defaultName
                + ", "
                + "deletePersonDescriptor=" + defaultDescriptor + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    @Test
    public void undoDeletePerson_success() throws CommandException {
        Person personToDelete = new PersonBuilder().withName("Amy Bee").build();
        model.addPerson(personToDelete);

        DeleteCommand deletePersonCommand = new DeleteCommand(null, personToDelete.getName(), defaultDescriptor);
        deletePersonCommand.execute(model);

        // Undo the deletion of the person
        CommandResult undoResult = deletePersonCommand.undo(model);

        assertTrue(model.hasPerson(personToDelete));
        assertEquals(String.format(DeleteCommand.MESSAGE_UNDO_DELETE_PATIENT_SUCCESS,
                Messages.format(personToDelete)), undoResult.getFeedbackToUser());
    }

    @Test
    public void undoDeleteFields() throws CommandException {
        Person firstPerson = model.getFilteredPersonList().get(0);

        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        descriptor.setDeleteAppointment();

        DeleteCommand deleteFieldsCommand = new DeleteCommand(firstPerson.getId(), null, descriptor);
        deleteFieldsCommand.execute(model);

        Person editedPerson = model.getFilteredPersonList().get(0);
        assertTrue(editedPerson.getAppointment().isEmpty());

        CommandResult undoResult = deleteFieldsCommand.undo(model);

        Person unDonePerson = model.getFilteredPersonList().get(0);
        assertFalse(unDonePerson.getAppointment().isEmpty());

        assertEquals(String.format(DeleteCommand.MESSAGE_UNDO_DELETE_FIELD_SUCCESS,
                Messages.format(editedPerson)), undoResult.getFeedbackToUser());
    }
}
