package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class DeleteLessonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());

    @Test
    public void equals() {
        DeleteLessonCommand deleteFirstCommand = new DeleteLessonCommand(1);
        DeleteLessonCommand deleteSecondCommand = new DeleteLessonCommand(2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteLessonCommand deleteFirstCommandCopy = new DeleteLessonCommand(1);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
    @Test
    public void test_valid_index() throws ParseException, CommandException {
        int size = model.getFilteredScheduleList().size();
        while (size > 0) {
            DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(size);
            deleteLessonCommand.execute(model);
            size--;
        }
        assertEquals(0, model.getFilteredScheduleList().size());
    }
    @Test
    public void test_invalid_index() {
        int size = model.getFilteredScheduleList().size();
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(size + 1);
        assertThrows(CommandException.class, () -> deleteLessonCommand.execute(model));
        DeleteLessonCommand deleteLessonCommand2 = new DeleteLessonCommand(0);
        assertThrows(CommandException.class, () -> deleteLessonCommand2.execute(model));
        DeleteLessonCommand deleteLessonCommand3 = new DeleteLessonCommand(-1);
        assertThrows(CommandException.class, () -> deleteLessonCommand3.execute(model));
    }
}
