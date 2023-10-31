package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.Date;
import seedu.address.testutil.TypicalSchedules;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ShowCalendarCommandTest}.
 */
public class ShowCalendarCommandTest {

    private Model model = new ModelManager(TypicalSchedules.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validDate_success() {
        Date date = new Date(LocalDate.of(2023, 9, 15));
        ShowCalendarCommand showCalendarCommand = new ShowCalendarCommand(date);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredScheduleList(schedule -> schedule.isOnDate(date));

        assertCommandSuccess(showCalendarCommand, model, ShowCalendarCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShowCalendarCommand(null));
    }

    @Test
    public void equals() {
        Date date1 = new Date(LocalDate.of(2023, 9, 15));
        Date date2 = new Date(LocalDate.of(2023, 9, 16));

        ShowCalendarCommand showCalendarFirstCommand = new ShowCalendarCommand(date1);
        ShowCalendarCommand showCalendarSecondCommand = new ShowCalendarCommand(date2);

        // same object -> returns true
        assertTrue(showCalendarFirstCommand.equals(showCalendarFirstCommand));

        // same values -> returns true
        ShowCalendarCommand showCalendarFirstCommandCopy = new ShowCalendarCommand(date1);
        assertTrue(showCalendarFirstCommand.equals(showCalendarFirstCommandCopy));

        // different types -> returns false
        assertFalse(showCalendarFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showCalendarFirstCommand.equals(null));

        // different tutor -> returns false
        assertFalse(showCalendarFirstCommand.equals(showCalendarSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Date date = new Date(LocalDate.of(2023, 9, 15));
        ShowCalendarCommand showCalendarCommand = new ShowCalendarCommand(date);
        String expected = ShowCalendarCommand.class.getCanonicalName() + "{date=" + date + "}";
        assertEquals(expected, showCalendarCommand.toString());
    }
}
