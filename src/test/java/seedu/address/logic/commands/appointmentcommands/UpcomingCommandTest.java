package seedu.address.logic.commands.appointmentcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.testutil.TypicalAppointments.UPCOMINGAPPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UpcomingCommandTest {

    @Test
    public void execute_listIsFiltered_showsAppointmentsOnTodayOnly() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addAppointment(UPCOMINGAPPOINTMENT);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addAppointment(UPCOMINGAPPOINTMENT);
        showAppointmentAtIndex(expectedModel, Index.fromZeroBased(4));
        assertCommandSuccess(new UpcomingCommand(), model, UpcomingCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
