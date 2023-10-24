package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyWellNus;
import seedu.address.model.WellNus;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.ModelStub;

public class ScheduleCommandTest {

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScheduleCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ScheduleCommandTest.ModelStubAcceptingAppointmentAdded modelStub =
                new ScheduleCommandTest.ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder().build();

        CommandResult commandResult = new ScheduleCommand(validAppointment).execute(modelStub);

        assertEquals(String.format(ScheduleCommand.MESSAGE_SUCCESS, Messages.format(validAppointment)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder().build();
        ScheduleCommand scheduleCommand = new ScheduleCommand(validAppointment);
        ModelStub modelStub = new ScheduleCommandTest.ModelStubWithAppointment(validAppointment);
        assertThrows(CommandException.class, ScheduleCommand.MESSAGE_DUPLICATE_APPOINTMENT, () ->
                scheduleCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment alice = new AppointmentBuilder().withName("Alice").build();
        Appointment bob = new AppointmentBuilder().withName("Bob").build();
        ScheduleCommand scheduleAliceCommand = new ScheduleCommand(alice);
        ScheduleCommand scheduleBobCommand = new ScheduleCommand(bob);

        // same object -> returns true
        assertTrue(scheduleAliceCommand.equals(scheduleAliceCommand));

        // same values -> returns true
        ScheduleCommand scheduleAliceCommandCopy = new ScheduleCommand(alice);
        assertTrue(scheduleAliceCommand.equals(scheduleAliceCommandCopy));

        // different types -> returns false
        assertFalse(scheduleAliceCommand.equals(1));

        // null -> returns false
        assertFalse(scheduleAliceCommand.equals(null));

        // different student -> returns false
        assertFalse(scheduleAliceCommand.equals(scheduleBobCommand));
    }

    @Test
    public void toStringMethod() {
        Appointment alice = new AppointmentBuilder().withName("John Doe").withDateTime("2023-12-31 16:30:00")
                .withDescription("First Session").build();
        ScheduleCommand scheduleCommand = new ScheduleCommand(alice);
        String expected = ScheduleCommand.class.getCanonicalName() + "{toAdd=" + alice + "}";
        assertEquals(expected, scheduleCommand.toString());
    }

    /**
     * A Model stub that contains a single appointment.
     */
    private class ModelStubWithAppointment extends ModelStub {
        private final Appointment appointment;

        ModelStubWithAppointment(Appointment appointment) {
            requireNonNull(appointment);
            this.appointment = appointment;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointment(appointment);
        }
    }

    /**
     * A Model stub that always accept the appointment being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public ReadOnlyWellNus getAddressBook() {
            return new WellNus();
        }
    }
}
