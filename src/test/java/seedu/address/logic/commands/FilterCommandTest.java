package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.ALICE_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.BENNY_APPOINTMENT;
import static seedu.address.testutil.TypicalWellNus.getTypicalWellNus;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.AppointmentDateMatchesPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalWellNus(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalWellNus(), new UserPrefs());

    @Test
    public void equals() {
        AppointmentDateMatchesPredicate firstPredicate =
                new AppointmentDateMatchesPredicate(ALICE_APPOINTMENT.getDate().getDate());
        AppointmentDateMatchesPredicate secondPredicate =
                new AppointmentDateMatchesPredicate(ALICE_SECOND_APPOINTMENT.getDate().getDate());

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    // Find single appointment
    @Test
    public void execute_validDate_singleAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        AppointmentDateMatchesPredicate predicate = new AppointmentDateMatchesPredicate(ALICE_APPOINTMENT
                .getDate().getDate());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredAppointmentList(), Collections.singletonList(ALICE_APPOINTMENT));
    }

    // Find multiple appointments
    @Test
    public void execute_validDate_multipleAppointmentsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 2);
        AppointmentDateMatchesPredicate predicate = new AppointmentDateMatchesPredicate(ALICE_SECOND_APPOINTMENT
                .getDate().getDate());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredAppointmentList(), Arrays.asList(ALICE_SECOND_APPOINTMENT,
                BENNY_APPOINTMENT));
    }

    // Find no appointment
    @Test
    public void execute_noAppointmentsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        AppointmentDateMatchesPredicate predicate = new AppointmentDateMatchesPredicate("2023-10-26");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredAppointmentList(), Collections.emptyList());
    }

    @Test
    public void toStringMethod() {
        AppointmentDateMatchesPredicate predicate = new AppointmentDateMatchesPredicate("2023-10-26");
        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }
}
