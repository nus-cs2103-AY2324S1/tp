package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookManager;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;
import seedu.address.model.week.Week;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests
 * for {@code ViewCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookManager(), new UserPrefs());

    @Test
    public void execute_nullTargetIndex_throwCommandException() {
        ViewCommand viewCommand = new ViewCommand(null);
        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_outOfBoundTargetIndex_throwCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ViewCommand firstInvalidViewCommand = new ViewCommand(outOfBoundIndex);
        assertCommandFailure(firstInvalidViewCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validTargetIndexWithNoAttendance_success() {
        Person amy = new PersonBuilder().build();
        model.addPerson(amy);
        Index amyIndex = Index.fromZeroBased(model.getFilteredPersonList().indexOf(amy));
        ViewCommand viewCommand = new ViewCommand(amyIndex);
        String expectedMessage = amy.getName().toString() + ViewCommand.MESSAGE_NO_ATTENDANCE_RECORD;

        assertCommandSuccess(viewCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_validTargetIndexWithPresentAttendance_success() {
        Week testWeek = new Week(1);
        Person amy = new PersonBuilder()
                .build();
        amy.addAttendance(new Attendance(testWeek, true, null));
        model.addPerson(amy);

        Index amyIndex = Index.fromZeroBased(model.getFilteredPersonList().indexOf(amy));
        ViewCommand viewCommand = new ViewCommand(amyIndex);
        String expectedMessage = String.format("%s" + ViewCommand.MESSAGE_HEADER + "Week " + "%d" + " : Present\n",
                amy.getName(), testWeek.getWeekNumber());

        assertCommandSuccess(viewCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_validTargetIndexWithAbsentAttendance_success() {
        Week testWeek = new Week(1);
        Person amy = new PersonBuilder()
                .build();
        amy.addAttendance(new Attendance(testWeek, false, "Late"));
        model.addPerson(amy);

        Index amyIndex = Index.fromZeroBased(model.getFilteredPersonList().indexOf(amy));
        ViewCommand viewCommand = new ViewCommand(amyIndex);
        Attendance attendance = amy.getAttendanceRecords().get(0);
        String expectedMessage = String.format("%s" + ViewCommand.MESSAGE_HEADER + "Week " + "%d" + " : Absent - %s\n",
                amy.getName(), testWeek.getWeekNumber(), attendance.getReason());

        assertCommandSuccess(viewCommand, model, expectedMessage, model);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_PERSON);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(viewFirstCommand, viewFirstCommand);

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_PERSON);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
