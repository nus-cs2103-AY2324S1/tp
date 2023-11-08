package seedu.address.logic.commands;


import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lessons.Task;

class DeleteTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());
    @Test
    public void testShownLesson() throws CommandException {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(1);
        model.resetAllShowFields();
        assertThrows(CommandException.class, () -> deleteTaskCommand.execute(model));
        model.addTask(new Task("Test"), 0);
        model.showLesson(model.getFilteredScheduleList().get(0));
        deleteTaskCommand.execute(model);
    }

    @Test
    public void testValidIndex() throws CommandException {
        model.showLesson(model.getFilteredScheduleList().get(0));
        int size = model.getFilteredScheduleList().get(0).getTaskList().size();
        for (int i = size; i > 0; i--) {
            DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(i);
            deleteTaskCommand.execute(model);
        }
    }

    @Test
    public void test_invalid_index() throws CommandException {
        model.showLesson(model.getFilteredScheduleList().get(0));
        int size = model.getFilteredScheduleList().get(0).getTaskList().size();
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(size + 1);
        assertThrows(CommandException.class, () -> deleteTaskCommand.execute(model));
        DeleteTaskCommand deleteTaskCommand2 = new DeleteTaskCommand(0);
        assertThrows(CommandException.class, () -> deleteTaskCommand2.execute(model));
        DeleteTaskCommand deleteTaskCommand3 = new DeleteTaskCommand(-1);
        assertThrows(CommandException.class, () -> deleteTaskCommand3.execute(model));
    }

}
