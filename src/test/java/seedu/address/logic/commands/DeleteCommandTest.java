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

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_NAME, () -> command.execute(model));
    }

    @Test
    public void execute_invalidNric_throwsCommandException() {
        Nric invalidNric = new Nric("S000000X");
        DeleteCommand command = new DeleteCommand(invalidNric, null, new DeletePersonDescriptor());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_NRIC, () -> command.execute(model));
    }

    @Test
    public void execute_deleteFields_success() throws CommandException {
        Person firstPerson = model.getFilteredPersonList().get(0);

        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();
        descriptor.setAppointment();

        DeleteCommand command = new DeleteCommand(firstPerson.getNric(), null, descriptor);
        command.execute(model);

        Person editedPerson = model.getFilteredPersonList().get(0);
        assertTrue(editedPerson.getAppointment().get().value.equals(""));
    }

    @Test
    public void deletePersonDescriptor_setterMethods() {
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();

        descriptor.setAppointment();
        descriptor.setAddress();
        descriptor.setEmail();
        descriptor.setMedicalHistory();
        descriptor.setPhone();
        descriptor.setTags();

        assertTrue(descriptor.getAppointment());
        assertTrue(descriptor.getAddress());
        assertTrue(descriptor.getEmail());
        assertTrue(descriptor.getMedicalHistory());
        assertTrue(descriptor.getPhone());
        assertTrue(descriptor.getTags());
    }

    @Test
    public void deletePersonDescriptor_isAllFalse() {

        DeletePersonDescriptor descriptor = new DeletePersonDescriptor();

        assertTrue(descriptor.isAllFalse());

        descriptor.setAppointment();

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
        descriptorDifferent.setAddress();
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
}
