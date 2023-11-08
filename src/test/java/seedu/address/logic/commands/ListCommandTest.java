package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.state.State;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalScheduleList());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
    }

    // List command gives same lists in model
    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " " + model.getState();
        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
    }

    // List command resets previously filtered list to show everything
    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " " + model.getState();
        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
    }

    /* Test cases for list command changing model state */

    // Test list command changing from initial state (schedule) to student state
    @Test
    public void execute_listInitialToStudent() {
        expectedModel.setState(State.STUDENT);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " " + expectedModel.getState();
        assertCommandSuccess(new ListCommand(State.STUDENT), model, expectedMessage, expectedModel);
    }

    // Test list command changing from initial state (schedule) to task state
    @Test
    public void execute_listInitialToTask() {
        expectedModel.setState(State.TASK);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " " + expectedModel.getState();
        assertCommandSuccess(new ListCommand(State.TASK), model, expectedMessage, expectedModel);
    }

    // Test list command changing from initial state (schedule) to schedule state
    @Test
    public void execute_listInitialToSchedule() {
        expectedModel.setState(State.SCHEDULE);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " " + expectedModel.getState();
        assertCommandSuccess(new ListCommand(State.SCHEDULE), model, expectedMessage, expectedModel);
    }

    // Test list command changing from intermediate state to another state
    @Test
    public void execute_listStudentToTask() {
        model.setState(State.STUDENT);
        expectedModel.setState(State.STUDENT);
        assertEquals(model, expectedModel);
        expectedModel.setState(State.TASK);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " " + expectedModel.getState();
        assertCommandSuccess(new ListCommand(State.TASK), model, expectedMessage, expectedModel);
    }

    // Test list command with display field parameters
    @Test
    public void execute_listInitialToStudentParams() {
        expectedModel.setState(State.STUDENT);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " " + expectedModel.getState();
        assertCommandSuccess(new ListCommand(State.STUDENT, new String[]{"email", "phone"}), model, expectedMessage, expectedModel);
    }

    // Test list command from intermediate state to another state with display field parameters
    @Test
    public void execute_listTaskToStudentParams() {
        model.setState(State.TASK);
        expectedModel.setState(State.STUDENT);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " " + expectedModel.getState();
        assertCommandSuccess(new ListCommand(State.STUDENT, new String[]{"email", "phone"}), model, expectedMessage, expectedModel);
    }

    @Test
    public void sameCommand() {
        ListCommand command = new ListCommand();
        assertEquals(command, command);
    }

    @Test
    public void nullCommand() {
        ListCommand command = new ListCommand();
        assertFalse(command.equals(null));
    }
}
