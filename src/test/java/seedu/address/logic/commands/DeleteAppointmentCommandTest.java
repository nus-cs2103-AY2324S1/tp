package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.Person;
import seedu.address.model.shared.Nric;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAppointmentCommand}.
 */
public class DeleteAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Appointment appointmentToDelete = model.getAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Nric patientNric = appointmentToDelete.getNric();
        Person personWithAppointment = model.getFilteredPersonList().stream()
                .filter(person -> person.getNric().equals(patientNric))
                .findFirst()
                .orElse(null);
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                Messages.format(appointmentToDelete, personWithAppointment));

        UniqueAppointmentList newAppointmentList = new UniqueAppointmentList();
        newAppointmentList.setAppointments(personWithAppointment.getAppointments());
        newAppointmentList.remove(appointmentToDelete);

        Person editedPerson = new PersonBuilder(personWithAppointment).withAppointments(newAppointmentList).build();

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personWithAppointment, editedPerson);
        expectedModel.resetAppointmentList();

        assertCommandSuccess(deleteAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAppointmentList().size() + 1);
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(deleteAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidNric_throwsCommandException() {
        Appointment appointmentToDelete = model.getAppointmentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Nric patientNric = appointmentToDelete.getNric();
        Person personWithAppointment = model.getFilteredPersonList().stream()
                .filter(person -> person.getNric().equals(patientNric))
                .findFirst()
                .orElse(null);
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);

        Person newPerson = new PersonBuilder(personWithAppointment).withNric("S9999999A").build();
        model.setPerson(personWithAppointment, newPerson);

        assertCommandFailure(deleteAppointmentCommand, model, DeleteAppointmentCommand.MESSAGE_INVALID_NRIC);
    }

    @Test
    public void equals() {
        DeleteAppointmentCommand deleteFirstCommand = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);
        DeleteAppointmentCommand deleteSecondCommand = new DeleteAppointmentCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAppointmentCommand deleteFirstCommandCopy = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(targetIndex);
        String expected = DeleteAppointmentCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteAppointmentCommand.toString());
    }
}
