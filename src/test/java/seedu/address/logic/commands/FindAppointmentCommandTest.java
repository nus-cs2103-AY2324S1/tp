package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_APPOINTMENTS_FOUND_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.util.SampleDataUtil.APPOINTMENT_1;
import static seedu.address.model.util.SampleDataUtil.APPOINTMENT_2;
import static seedu.address.model.util.SampleDataUtil.getSampleAddressBook;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentIcPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindAppointmentCommand}.
 */
public class FindAppointmentCommandTest {
    private Model model = new ModelManager(getSampleAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getSampleAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullField_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindAppointmentCommand(null));
    }

    @Test
    public void equals() {
        AppointmentIcPredicate icPredicate1 = new AppointmentIcPredicate("T1234567Z");
        AppointmentIcPredicate icPredicate2 = new AppointmentIcPredicate("T7654321Z");

        FindAppointmentCommand findFirstCommand = new FindAppointmentCommand(icPredicate1);
        FindAppointmentCommand findSecondCommand = new FindAppointmentCommand(icPredicate2);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAppointmentCommand findFirstCommandCopy = new FindAppointmentCommand(icPredicate1);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }


    @Test
    public void execute_findPatientNric_appointmentFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_FOUND_OVERVIEW, 2);
        Predicate<Appointment> predicate = preparePredicate("S1111111Z");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_1, APPOINTMENT_2),
                model.getFilteredAppointmentList());
    }

    @Test
    public void execute_findDoctorNric_appointmentFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_FOUND_OVERVIEW, 1);
        Predicate<Appointment> predicate = preparePredicate("S8811111Z");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_1),
                model.getFilteredAppointmentList());
    }


    @Test
    public void toStringMethod() {
        AppointmentIcPredicate predicate = new AppointmentIcPredicate("keyword");
        FindAppointmentCommand FindAppointmentCommand = new FindAppointmentCommand(predicate);
        String expected = FindAppointmentCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, FindAppointmentCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code Predicate<Person>}.
     */
    private Predicate<Appointment> preparePredicate(String userInput) throws ParseException {
        return new AppointmentIcPredicate(userInput);
    }
}
