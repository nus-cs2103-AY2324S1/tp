package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_ONE;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_TWO;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointments.Appointment;

public class FilterAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FilterAppointmentCommand firstCommand = new FilterAppointmentCommand("dentist", 1);
        FilterAppointmentCommand secondCommand = new FilterAppointmentCommand("patient", 1);
        FilterAppointmentCommand thirdCommand = new FilterAppointmentCommand("dentist", 2);
        FilterAppointmentCommand fourthCommand = new FilterAppointmentCommand("patient", 2);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        FilterAppointmentCommand firstCommandCopy = new FilterAppointmentCommand("dentist", 1);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different id_type but same id -> returns false
        assertFalse(firstCommand.equals(secondCommand));

        // different different dentist id -> returns false
        assertFalse(firstCommand.equals(thirdCommand));

        // different different patient id -> returns false
        assertFalse(secondCommand.equals(fourthCommand));
    }

    @Test
    public void execute_noDentistId_noAppointmentFound() {
        String expectedMessage = "No appointments with dentist whose dentist ID is 4 found.";
        long id = 4;
        FilterAppointmentCommand command = new FilterAppointmentCommand("dentist", id);
        Predicate<Appointment> predicate = appointment -> appointment.getDentistId() == id;
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_noPatientId_noAppointmentFound() {
        String expectedMessage = "No appointments with patient whose patient ID is 4 found.";
        long id = 4;
        FilterAppointmentCommand command = new FilterAppointmentCommand("patient", id);
        Predicate<Appointment> predicate = appointment -> appointment.getPatientId() == id;
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_haveDentistId_appointmentsFound() {
        String expectedMessage = "Appointments with dentist whose dentist ID is 3 listed.";
        long id = 3;
        FilterAppointmentCommand command = new FilterAppointmentCommand("dentist", id);
        Predicate<Appointment> predicate = appointment -> appointment.getDentistId() == id;
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_TWO), model.getFilteredAppointmentList());
    }
    @Test
    public void execute_havePatientId_appointmentsFound() {
        String expectedMessage = "Appointments with patient whose patient ID is 2 listed.";
        long id = 2;
        FilterAppointmentCommand command = new FilterAppointmentCommand("patient", id);
        Predicate<Appointment> predicate = appointment -> appointment.getPatientId() == id;
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_ONE, APPOINTMENT_TWO), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_invalidDentistId_throwsCommandException() {
        FilterAppointmentCommand command = new FilterAppointmentCommand("dentist", -2);

        assertThrows(CommandException.class, () -> command.execute(new ModelManager()));
    }

    @Test
    public void execute_invalidInputs() {
        FilterAppointmentCommand command = new FilterAppointmentCommand("doctor", 1);
        assertThrows(CommandException.class, () -> command.execute(new ModelManager()));
    }

}
