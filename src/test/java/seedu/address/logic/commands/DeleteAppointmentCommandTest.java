package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointments.Appointment;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.ui.CalendarWindow;

public class DeleteAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_nullModel_throwsNullPointerException() {
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(1L);

        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        deleteAppointmentCommand.setCalendarWindow(mockCalendarWindow);

        assertThrows(NullPointerException.class, () -> deleteAppointmentCommand.execute(null));
    }

    @Test
    void execute_validId_success() {
        Appointment appointment = new AppointmentBuilder().build();
        model.addAppointment(appointment);

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(appointment.getId());

        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        deleteAppointmentCommand.setCalendarWindow(mockCalendarWindow);

        assertDoesNotThrow(() -> deleteAppointmentCommand.execute(model));
    }

    @Test
    void execute_invalidId_throwsCommandException() {
        Model model = new ModelManager();
        long invalidId = 99999L;
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(invalidId);

        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        deleteAppointmentCommand.setCalendarWindow(mockCalendarWindow);

        assertThrows(CommandException.class, () -> deleteAppointmentCommand.execute(model));
    }

    @Test
    void execute_negativeId_throwsCommandException() {
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(-1L);

        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        deleteAppointmentCommand.setCalendarWindow(mockCalendarWindow);

        assertThrows(CommandException.class, () -> deleteAppointmentCommand.execute(new ModelManager()));
    }

    @Test
    void execute_zeroId_throwsCommandException() {
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(0L);

        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        deleteAppointmentCommand.setCalendarWindow(mockCalendarWindow);

        assertThrows(CommandException.class, () -> deleteAppointmentCommand.execute(new ModelManager()));
    }

    @Test
    void execute_multipleDeletions_success() throws CommandException {
        Appointment appointmentOne = new AppointmentBuilder()
                .withAppointmentTime("2023-10-19T16:00", "PT10H30M")
                .withId("1")
                .build();
        Appointment appointmentTwo = new AppointmentBuilder()
                .withAppointmentTime("2023-10-19T16:00", "PT10H30M")
                .withId("2")
                .build();
        model.addAppointment(appointmentOne);
        model.addAppointment(appointmentTwo);

        DeleteAppointmentCommand deleteFirstAppointment = new DeleteAppointmentCommand(appointmentOne.getId());

        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        deleteFirstAppointment.setCalendarWindow(mockCalendarWindow);

        deleteFirstAppointment.execute(model);

        assertNull(model.getAppointmentById(appointmentOne.getId()));
        assertNotNull(model.getAppointmentById(appointmentTwo.getId()));
    }

    @Test
    void equals() {
        long firstId = 1L;
        long secondId = 2L;
        DeleteAppointmentCommand deleteFirstCommand = new DeleteAppointmentCommand(firstId);
        CalendarWindow mockCalendarWindow = mock(CalendarWindow.class);
        deleteFirstCommand.setCalendarWindow(mockCalendarWindow);

        DeleteAppointmentCommand deleteSecondCommand = new DeleteAppointmentCommand(secondId);
        deleteSecondCommand.setCalendarWindow(mockCalendarWindow);

        assertEquals(deleteFirstCommand, deleteFirstCommand);

        DeleteAppointmentCommand deleteFirstCommandCopy = new DeleteAppointmentCommand(firstId);
        deleteFirstCommandCopy.setCalendarWindow(mockCalendarWindow);

        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        assertNotEquals(deleteFirstCommand, 1);

        assertNotEquals(deleteFirstCommand, null);

        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }
}
