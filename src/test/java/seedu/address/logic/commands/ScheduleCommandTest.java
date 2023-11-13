package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.JON_ANG;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyWellNus;
import seedu.address.model.WellNus;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.exceptions.InvalidStartEndTimeException;
import seedu.address.model.student.Student;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.ModelStub;

public class ScheduleCommandTest {

    // Null Appointment
    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScheduleCommand(null));
    }

    // Valid appointment
    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ScheduleCommandTest.ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder().build();

        CommandResult commandResult = new ScheduleCommand(validAppointment).execute(modelStub);

        assertEquals(String.format(ScheduleCommand.MESSAGE_SUCCESS, Messages.format(validAppointment)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    // Duplicate appointment
    @Test
    public void execute_duplicateAppointment_throwsCommandException() throws InvalidStartEndTimeException {
        Appointment validAppointment = new AppointmentBuilder().build();
        ScheduleCommand scheduleCommand = new ScheduleCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithAppointment(validAppointment);
        assertThrows(CommandException.class, ScheduleCommand.MESSAGE_DUPLICATE_APPOINTMENT, () ->
                scheduleCommand.execute(modelStub));
    }

    // Overlapping appointment
    @Test
    public void execute_overlappingAppointment_throwsCommandException() throws InvalidStartEndTimeException {
        Appointment validAppointment = new AppointmentBuilder().build();
        Appointment existingAppointment = new AppointmentBuilder().withStartTime("17:00").withEndTime("18:00").build();
        ScheduleCommand scheduleCommand = new ScheduleCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithAppointment(existingAppointment);
        assertThrows(CommandException.class, ScheduleCommand.MESSAGE_OVERLAPPING_APPOINTMENTS, () ->
                scheduleCommand.execute(modelStub));
    }

    @Test
    public void equals() throws InvalidStartEndTimeException {
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
    public void toStringMethod() throws InvalidStartEndTimeException {
        Appointment alice = new AppointmentBuilder().withName("John Doe").withStartTime("16:30").withEndTime("17:30")
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

        @Override
        public boolean hasOverlapsWithAppointments(Appointment appointment) {
            return this.appointment.isOverlappingAppointment(appointment);
        }
    }

    /**
     * A Model stub that always accept the appointment being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();
        final ArrayList<Student> studentsAdded = new ArrayList<>();
        public ModelStubAcceptingAppointmentAdded() {
            studentsAdded.add(JON_ANG);
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public boolean hasOverlapsWithAppointments(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isOverlappingAppointment);
        }

        @Override
        public boolean hasNoStudentForAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return studentsAdded.stream().anyMatch(student -> !student.getName().equals(appointment.getName()));
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public ReadOnlyWellNus getWellNusData() {
            return new WellNus();
        }
    }
}
