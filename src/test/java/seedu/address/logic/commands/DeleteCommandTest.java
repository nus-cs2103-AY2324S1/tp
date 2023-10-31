package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteCommand.DeletePersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Name defaultName = new Name(PersonBuilder.DEFAULT_NAME);

    private Nric defaultNric = new Nric(PersonBuilder.DEFAULT_NRIC);

    private DeletePersonDescriptor defaultDescriptor = new DeletePersonDescriptor();

    @Test
    public void execute_validNameUnfilteredList_success() {
        Person personToDelete = new PersonBuilder().withName("Amy Bee").build();
        model.addPerson(personToDelete);
        Name name = personToDelete.getName();
        DeleteCommand deleteCommand = new DeleteCommand(null, name, defaultDescriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNricUnfilteredList_success() {
        Person personToDelete = new PersonBuilder().withNric("S1234567E").build();
        model.addPerson(personToDelete);
        Nric nric = personToDelete.getNric();
        DeleteCommand deleteCommand = new DeleteCommand(nric, null, defaultDescriptor);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        Name invalidName = new Name("Does Not Exist");
        DeleteCommand command = new DeleteCommand(null, invalidName, new DeletePersonDescriptor());

        assertThrows(CommandException.class, DeleteCommand.MESSAGE_PERSON_NOT_FOUND, () -> command.execute(model));
    }

    @Test
    public void execute_invalidNric_throwsCommandException() {
        Nric invalidNric = new Nric("S000000X");
        DeleteCommand command = new DeleteCommand(invalidNric, null, new DeletePersonDescriptor());

        assertThrows(CommandException.class, DeleteCommand.MESSAGE_PERSON_NOT_FOUND, () -> command.execute(model));
    }

    @Test
    public void execute_deleteFields_success() throws CommandException {
        Person firstPerson = model.getFilteredPersonList().get(0);

        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        descriptor.setShouldDeleteAppointment();
        descriptor.setShouldDeleteMedicalHistory();

        DeleteCommand command = new DeleteCommand(firstPerson.getNric(), null, descriptor);
        command.execute(model);

        Person editedPerson = model.getFilteredPersonList().get(0);
        assertTrue(editedPerson.getAppointment().isEmpty());
        assertTrue(editedPerson.getMedicalHistories().isEmpty());
    }

    @Test
    public void deletePersonDescriptor_setterMethods() {
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();

        descriptor.setShouldDeleteAppointment();
        descriptor.setShouldDeleteMedicalHistory();

        assertTrue(descriptor.getShouldDeleteAppointment());
        assertTrue(descriptor.getShouldDeleteMedicalHistory());
    }

    @Test
    public void deletePersonDescriptor_isAllFalse() {

        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();

        assertTrue(descriptor.isAllFalse());

        descriptor.setShouldDeleteAppointment();

        assertFalse(descriptor.isAllFalse());
    }

    @Test
    public void equalsDeleteCommand() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(defaultNric, null, defaultDescriptor);
        DeleteCommand deleteSecondCommand = new DeleteCommand(null, defaultName, defaultDescriptor);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(defaultNric, null, defaultDescriptor);
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
        descriptorDifferent.setShouldDeleteAppointment();
        assertFalse(descriptor.equals(descriptorDifferent));
    }

    @Test
    public void toStringMethod() {
        DeleteCommand deleteCommand = new DeleteCommand(defaultNric, defaultName, defaultDescriptor);
        String expected = DeleteCommand.class.getCanonicalName() + "{nric=" + defaultNric + ", " + "name=" + defaultName
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
        assertEquals(String.format(DeleteCommand.MESSAGE_UNDO_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete)), undoResult.getFeedbackToUser());
    }

    @Test
    public void undoDeleteFields() throws CommandException {
        Person firstPerson = model.getFilteredPersonList().get(0);

        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        descriptor.setShouldDeleteAppointment();

        DeleteCommand deleteFieldsCommand = new DeleteCommand(firstPerson.getNric(), null, descriptor);
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

