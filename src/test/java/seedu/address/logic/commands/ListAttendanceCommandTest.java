package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListAttendanceCommand.MESSAGE_NO_STUDENTS;
import static seedu.address.logic.commands.ListAttendanceCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookManager;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Attendance;
import seedu.address.model.predicate.AbsentFromTutorialPredicate;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.week.Week;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAttendanceCommand.
 */
public class ListAttendanceCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookManager(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());
    }

    @Test
    public void equals() {
        Optional<Tag> firstTag = Optional.of(new Tag("G10"));
        Optional<Tag> secondTag = Optional.of(new Tag("G01"));
        Week firstWeek = new Week(1);
        Week secondWeek = new Week(2);

        ListAttendanceCommand listAttendanceFirstCommand = new ListAttendanceCommand(firstTag, firstWeek,
                new ContainsTagPredicate(firstTag),
                new AbsentFromTutorialPredicate(firstWeek, firstTag));
        ListAttendanceCommand listAttendanceSecondCommand = new ListAttendanceCommand(secondTag, secondWeek,
                new ContainsTagPredicate(secondTag),
                new AbsentFromTutorialPredicate(secondWeek, firstTag));

        // same object -> returns true
        assertTrue(listAttendanceFirstCommand.equals(listAttendanceFirstCommand));

        // same values -> returns true
        ListAttendanceCommand listAttendanceFirstCommandCopy = new ListAttendanceCommand(firstTag, firstWeek,
                new ContainsTagPredicate(firstTag),
                new AbsentFromTutorialPredicate(firstWeek, firstTag));
        assertTrue(listAttendanceFirstCommand.equals(listAttendanceFirstCommandCopy));

        // different types -> returns false
        assertFalse(listAttendanceFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listAttendanceFirstCommand.equals(null));

        // different values -> returns false
        assertFalse(listAttendanceFirstCommand.equals(listAttendanceSecondCommand));
    }
    @Test
    public void execute_listAttendanceWithTag_success() {
        ALICE.addAttendance(new Attendance(new Week(2), false, "Sick"));

        Optional<Tag> tag = Optional.of(new Tag("G02"));
        Week week = new Week(2);
        ListAttendanceCommand command = new ListAttendanceCommand(tag, week,
                new ContainsTagPredicate(tag), new AbsentFromTutorialPredicate(week, tag));

        String expectedSummary = String.format(Messages.MESSAGE_ATTENDANCE_SUMMARY_WITH_TAG, 0, 1, week.getWeekNumber(),
                tag.get().getTagName());

        expectedModel.addFilter(new ContainsTagPredicate(tag));
        expectedModel.addFilter(new AbsentFromTutorialPredicate(week, tag));
        CommandResult expectedCommandResult = new CommandResult(expectedSummary + MESSAGE_SUCCESS);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listWithNoStudentsWithTag_success() {
        Optional<Tag> tag = Optional.of(new Tag("TAG1"));
        Week week = new Week(2);
        ListAttendanceCommand command = new ListAttendanceCommand(tag, week,
                new ContainsTagPredicate(tag), new AbsentFromTutorialPredicate(week, tag));

        expectedModel.addFilter(new ContainsTagPredicate(tag));
        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_NO_STUDENTS, tag.get().getTagName()));

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listWithIncompleteAttendance_success() {
        ALICE.addAttendance(new Attendance(new Week(3), true, null));
        BENSON.addAttendance(new Attendance(new Week(3), true, null));
        CARL.addAttendance(new Attendance(new Week(3), true, null));
        ELLE.addAttendance(new Attendance(new Week(3), true, null));
        FIONA.addAttendance(new Attendance(new Week(3), true, null));
        GEORGE.addAttendance(new Attendance(new Week(3), true, null));

        Optional<Tag> tag = Optional.empty();
        Week week = new Week(3);
        ListAttendanceCommand command = new ListAttendanceCommand(tag, week,
                new ContainsTagPredicate(tag), new AbsentFromTutorialPredicate(week, tag));

        String message = String.format(ListAttendanceCommand.MESSAGE_INCOMPLETE_ATTENDANCE + "\n"
                + "Students with unmarked attendance: Daniel Meier", week.getWeekNumber());

        CommandResult expectedCommandResult = new CommandResult(message);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listAttendanceNoTag_success() {
        ALICE.addAttendance(new Attendance(new Week(1), true, null));
        BENSON.addAttendance(new Attendance(new Week(1), true, null));
        CARL.addAttendance(new Attendance(new Week(1), true, null));
        DANIEL.addAttendance(new Attendance(new Week(1), false, "Late"));
        ELLE.addAttendance(new Attendance(new Week(1), true, null));
        FIONA.addAttendance(new Attendance(new Week(1), true, null));
        GEORGE.addAttendance(new Attendance(new Week(1), true, null));

        Optional<Tag> tag = Optional.empty();
        Week week = new Week(1);
        ListAttendanceCommand command = new ListAttendanceCommand(tag, week,
                new ContainsTagPredicate(tag), new AbsentFromTutorialPredicate(week, tag));

        int total = expectedModel.getFilteredPersonList().size();
        String expectedSummary = String.format(Messages.MESSAGE_ATTENDANCE_SUMMARY_NO_TAG, total - 1, total, 1);

        expectedModel.addFilter(new AbsentFromTutorialPredicate(week, tag));
        CommandResult expectedCommandResult = new CommandResult(expectedSummary + MESSAGE_SUCCESS);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }
}
