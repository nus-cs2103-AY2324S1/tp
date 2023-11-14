package seedu.address.logic.commands.appointmentcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AppointmentsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new AppointmentsCommand(), model, AppointmentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);
        assertCommandSuccess(new AppointmentsCommand(), model, AppointmentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        AppointmentsCommand firstAppointmentsCommand = new AppointmentsCommand();
        AppointmentsCommand secondAppointmentsCommand = new AppointmentsCommand();

        // same object -> returns true
        assertTrue(firstAppointmentsCommand.equals(firstAppointmentsCommand));

        // same type -> returns true
        assertTrue(firstAppointmentsCommand.equals(secondAppointmentsCommand));

        // different types -> returns false
        assertFalse(firstAppointmentsCommand.equals(1));

        // null -> returns false
        assertFalse(firstAppointmentsCommand.equals(null));
    }
}
