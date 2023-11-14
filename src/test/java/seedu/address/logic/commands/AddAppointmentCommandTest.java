package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {

        Person personToAddAppointment = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment validAppointment = new AppointmentBuilder().withNric(personToAddAppointment.getNric().toString())
                .build();

        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, validAppointment);

        UniqueAppointmentList newAppointmentList = new UniqueAppointmentList();
        newAppointmentList.setAppointments(personToAddAppointment.getAppointments());
        newAppointmentList.add(validAppointment);

        Person editedPerson = new PersonBuilder(personToAddAppointment).withAppointments(newAppointmentList).build();

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS,
                Messages.format(validAppointment, editedPerson));

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.resetAppointmentList();

        assertCommandSuccess(addAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder().build();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(outOfBoundIndex, validAppointment);

        assertCommandFailure(addAppointmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToAddAppointment = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment validAppointment = new AppointmentBuilder().withNric(personToAddAppointment.getNric().toString())
                .build();

        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, validAppointment);

        UniqueAppointmentList newAppointmentList = new UniqueAppointmentList();
        newAppointmentList.setAppointments(personToAddAppointment.getAppointments());
        newAppointmentList.add(validAppointment);

        Person editedPerson = new PersonBuilder(personToAddAppointment).withAppointments(newAppointmentList).build();

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS,
                Messages.format(validAppointment, editedPerson));

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.resetAppointmentList();

        assertCommandSuccess(addAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Appointment validAppointment = new AppointmentBuilder().build();
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(outOfBoundIndex, validAppointment);

        assertCommandFailure(addAppointmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicateAppointmentUnfilteredList_failure() {
        Appointment appointment = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased())
                .getAppointments().asUnmodifiableObservableList().get(0);
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, appointment);

        assertCommandFailure(addAppointmentCommand, model, AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void equals() {
        Appointment eyeExam = new AppointmentBuilder().withName("Eye Exam").build();
        Appointment earExam = new AppointmentBuilder().withName("Ear Exam").build();

        final AddAppointmentCommand standardCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, eyeExam);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddAppointmentCommand(INDEX_SECOND_PERSON, eyeExam)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddAppointmentCommand(INDEX_FIRST_PERSON, earExam)));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        Appointment appointment = new AppointmentBuilder().build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(targetIndex, appointment);
        String expected = new ToStringBuilder(addAppointmentCommand)
                .add("toAdd", appointment)
                .toString();
        assertEquals(expected, addAppointmentCommand.toString());
    }
}
