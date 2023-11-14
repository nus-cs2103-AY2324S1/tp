package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.attendance.AttendanceType;


class AttendanceCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_reportByName_validName() throws CommandException {
        NameContainsKeywordsPredicate predicate = preparePredicate("Alice");
        AttendanceCommand attendanceCommand = new AttendanceCommand(predicate);

        Person employeeToReport = model.getFilteredPersonList().get(0);
        int[] attendanceValues = employeeToReport
                .getAttendanceStorage()
                .getAttendanceReport(employeeToReport.getJoinDate(),
                        employeeToReport.getAnnualLeave().getTotalLeaveTaken());

        String expectedMessage = String.format(AttendanceCommand.MESSAGE_REPORT_ATTENDANCE,
                employeeToReport.getName(),
                attendanceValues[0],
                attendanceValues[1],
                attendanceValues[2]);


        assertCommandSuccess(attendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_reportByIndex_validIndex() throws CommandException {
        Index index = Index.fromOneBased(1);
        AttendanceCommand attendanceCommand = new AttendanceCommand(index);

        Person employeeToReport = model.getFilteredPersonList().get(0);
        int[] attendanceValues = employeeToReport
                .getAttendanceStorage()
                .getAttendanceReport(employeeToReport.getJoinDate(),
                        employeeToReport.getAnnualLeave().getTotalLeaveTaken());

        String expectedMessage = String.format(AttendanceCommand.MESSAGE_REPORT_ATTENDANCE,
                employeeToReport.getName(),
                attendanceValues[0],
                attendanceValues[1],
                attendanceValues[2]);


        assertCommandSuccess(attendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equalsForIndex() {
        AttendanceCommand attendanceFirstCommand = new AttendanceCommand(INDEX_FIRST_PERSON);
        AttendanceCommand attendanceSecondCommand = new AttendanceCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(attendanceFirstCommand.equals(attendanceFirstCommand));

        // same values -> returns true
        AttendanceCommand attendanceFirstCommandCopy = new AttendanceCommand(INDEX_FIRST_PERSON);
        assertTrue(attendanceFirstCommand.equals(attendanceFirstCommandCopy));

        // different types -> returns false
        assertFalse(attendanceSecondCommand.equals(1));

        // null -> returns false
        assertFalse(attendanceFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(attendanceFirstCommand.equals(attendanceSecondCommand));
    }

    @Test
    public void equalsForName() {
        Person firstPersonToReport = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPersonToReport = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        NameContainsKeywordsPredicate firstPersonName =
                new NameContainsKeywordsPredicate(Arrays.asList(
                        new String[] {firstPersonToReport.getName().toString()}));
        NameContainsKeywordsPredicate secondPersonName =
                new NameContainsKeywordsPredicate(Arrays.asList(
                        new String[] {secondPersonToReport.getName().toString()}));
        AttendanceCommand attendanceFirstCommand = new AttendanceCommand(INDEX_FIRST_PERSON);
        AttendanceCommand attendanceSecondCommand = new AttendanceCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(attendanceFirstCommand.equals(attendanceFirstCommand));

        // same values -> returns true
        AttendanceCommand attendanceFirstCommandCopy = new AttendanceCommand(INDEX_FIRST_PERSON);
        assertTrue(attendanceFirstCommand.equals(attendanceFirstCommandCopy));

        // different types -> returns false
        assertFalse(attendanceSecondCommand.equals(1));

        // null -> returns false
        assertFalse(attendanceFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(attendanceFirstCommand.equals(attendanceSecondCommand));

    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        AttendanceType attendanceType = AttendanceType.PRESENT;
        MarkCommand markCommand = new MarkCommand(predicate, attendanceType);
        String expected = MarkCommand.class.getCanonicalName() + "{targetName=" + predicate + "}";
        assertEquals(expected, markCommand.toString());
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
