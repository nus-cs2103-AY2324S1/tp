package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

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

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " " + model.getState();
        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, 1);
        String expectedMessage = ListCommand.MESSAGE_SUCCESS + " " + model.getState();
        assertCommandSuccess(new ListCommand(), model, expectedMessage, expectedModel);
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
