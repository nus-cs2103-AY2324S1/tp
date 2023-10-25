package seedu.address.logic.commands.appointmentcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.testutil.TypicalAppointments.TODAYAPPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class TodayCommandTest {

    @Test
    public void execute_listIsFiltered_showsAppointmentsOnTodayOnly() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addAppointment(TODAYAPPOINTMENT);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addAppointment(TODAYAPPOINTMENT);
        showAppointmentAtIndex(expectedModel, Index.fromZeroBased(4));
        assertCommandSuccess(new TodayCommand(), model, TodayCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
