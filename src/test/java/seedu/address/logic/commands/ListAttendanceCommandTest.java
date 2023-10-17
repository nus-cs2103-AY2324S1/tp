package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListAttendanceCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AbsentFromTutorialNumPredicate;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.ContainsCourseTutorialPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAttendanceCommand.
 */
public class ListAttendanceCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        Tag firstTag = new Tag("CS2030S");
        Tag secondTag = new Tag("CS2040S");
        Index firstIndex = Index.fromOneBased(1);
        Index secondIndex = Index.fromOneBased(2);

        ListAttendanceCommand listAttendanceFirstCommand = new ListAttendanceCommand(firstTag, firstIndex,
                new ContainsCourseTutorialPredicate(firstTag),
                new AbsentFromTutorialNumPredicate(firstIndex, firstTag));
        ListAttendanceCommand listAttendanceSecondCommand = new ListAttendanceCommand(secondTag, secondIndex,
                new ContainsCourseTutorialPredicate(secondTag),
                new AbsentFromTutorialNumPredicate(secondIndex, firstTag));

        // same object -> returns true
        assertTrue(listAttendanceFirstCommand.equals(listAttendanceFirstCommand));

        // same values -> returns true
        ListAttendanceCommand listAttendanceFirstCommandCopy = new ListAttendanceCommand(firstTag, firstIndex,
                new ContainsCourseTutorialPredicate(firstTag),
                new AbsentFromTutorialNumPredicate(firstIndex, firstTag));
        assertTrue(listAttendanceFirstCommand.equals(listAttendanceFirstCommandCopy));

        // different types -> returns false
        assertFalse(listAttendanceFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listAttendanceFirstCommand.equals(null));

        // different values -> returns false
        assertFalse(listAttendanceFirstCommand.equals(listAttendanceSecondCommand));
    }
    @Test
    public void execute_listAttendance_success() {
        ALICE.addAttendance(new Attendance(LocalDate.now(), false, "CS2040S"));

        Tag tag = new Tag("CS2040S");
        Index index = Index.fromOneBased(1);
        ListAttendanceCommand command = new ListAttendanceCommand(tag, index,
                new ContainsCourseTutorialPredicate(tag), new AbsentFromTutorialNumPredicate(index, tag));

        String expectedSummary = String.format(Messages.MESSAGE_ATTENDANCE_SUMMARY_WITH_TAG, 0, 1, 1, tag.getTagName());

        expectedModel.updateFilteredPersonList(new AbsentFromTutorialNumPredicate(index, tag));
        CommandResult expectedCommandResult = new CommandResult(expectedSummary + MESSAGE_SUCCESS);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }
}
