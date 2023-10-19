package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class ScheduleCommandTest {
    private static final String APPOINTMENT_DESCRIPTION_STUB = "Review Insurance, 01-01-2023 20:00";
    private static final Appointment APPOINTMENT_STUB = Appointment.parseAppointmentDescription(
            APPOINTMENT_DESCRIPTION_STUB);
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
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
    }
}
