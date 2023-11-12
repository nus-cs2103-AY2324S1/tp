package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

class ScheduleCommandTest {
    private static final String APPOINTMENT_DESCRIPTION_STUB = "Review Insurance, 01-01-2023 20:00";
    private static final Appointment APPOINTMENT_STUB = Appointment.parseAppointmentDescription(
            APPOINTMENT_DESCRIPTION_STUB);
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullParams_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScheduleCommand(null, APPOINTMENT_STUB));
        assertThrows(NullPointerException.class, () -> new ScheduleCommand(INDEX_FIRST_PERSON, null));
    }
    @Test
    public void execute_scheduleAccepted_scheduleSuccessful() {
        Person personWithoutSchedule = model.getFilteredPersonList().get(0);

        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, APPOINTMENT_STUB);
        Person personWithSchedule = new PersonBuilder(personWithoutSchedule)
                .withAppointment(APPOINTMENT_DESCRIPTION_STUB).build();

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_SCHEDULE_SUCCESS,
                Messages.format(personWithSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), personWithSchedule);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);

        //checks if the person is associated with appointment
        assertTrue(personWithSchedule.getAppointment().equals(APPOINTMENT_STUB));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, APPOINTMENT_STUB);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, APPOINTMENT_STUB);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final ScheduleCommand standardCommand = new ScheduleCommand(INDEX_FIRST_PERSON, APPOINTMENT_STUB);

        // same values -> returns true
        ScheduleCommand commandWithSameValues = new ScheduleCommand(INDEX_FIRST_PERSON, APPOINTMENT_STUB);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ScheduleCommand(INDEX_SECOND_PERSON, APPOINTMENT_STUB)));

        // different appointment -> returns false
        ScheduleCommand commandWithDiffDateTime = new ScheduleCommand(INDEX_FIRST_PERSON,
                new Appointment("Review Insurance",
                        LocalDateTime.of(2023, 01, 01, 20, 30)));

        ScheduleCommand commandWithDiffValue = new ScheduleCommand(INDEX_FIRST_PERSON,
                new Appointment("Buy Insurance",
                        LocalDateTime.of(2023, 01, 01, 20, 0)));

        assertFalse(standardCommand.equals(commandWithDiffDateTime));
        assertFalse(standardCommand.equals(commandWithDiffValue));
    }
}
