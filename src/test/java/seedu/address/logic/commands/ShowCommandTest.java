package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;
import seedu.address.model.person.Person;
import seedu.address.model.state.State;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ShowCommand}.
 */
public class ShowCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());

    @Test
    public void execute_validIndexUnfilteredStudentList_success() {
        model.setState(State.STUDENT);
        Person personToShow = model.getFilteredPersonList().get(0);
        ShowCommand showCommand = new ShowCommand(1);
        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_PERSON_SUCCESS,
                Messages.format(personToShow));
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getScheduleList());
        expectedModel.setState(State.STUDENT);
        expectedModel.showPerson(personToShow);

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredLessonList_success() {
        model.setState(State.SCHEDULE);
        Lesson lessonToShow = model.getFilteredScheduleList().get(0);
        ShowCommand showCommand = new ShowCommand(1);
        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_LESSON_SUCCESS,
                Messages.formatLesson(lessonToShow));
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getScheduleList());
        expectedModel.showLesson(lessonToShow);

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFullTaskListList_success() {
        model.setState(State.TASK);
        Task taskToShow = model.getFullTaskList().get(0);
        ShowCommand showCommand = new ShowCommand(1);
        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_TASK_SUCCESS,
                Messages.formatTask(taskToShow));
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getScheduleList());
        expectedModel.showTask(taskToShow);
        expectedModel.setState(State.TASK);
        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexUnfilteredPersonList_throwsCommandException() {
        model.setState(State.STUDENT);
        int outOfBoundIndex = model.getFilteredPersonList().size() + 1;
        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexUnfilteredLessonList_throwsCommandException() {
        model.setState(State.SCHEDULE);
        int outOfBoundIndex = model.getFilteredScheduleList().size() + 1;
        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFullTaskList_throwsCommandException() {
        model.setState(State.TASK);
        int outOfBoundIndex = model.getFullTaskList().size() + 1;
        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ShowCommand showFirstCommand = new ShowCommand(1);
        ShowCommand showSecondCommand = new ShowCommand(2);
        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowCommand deleteFirstCommandCopy = new ShowCommand(1);
        assertTrue(showFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }

    @Test
    public void toStringMethod() {
        ShowCommand showCommand = new ShowCommand(1);
        String expected = ShowCommand.class.getCanonicalName() + "{targetIndex=" + "1" + "}";
        assertEquals(expected, showCommand.toString());
    }
}
