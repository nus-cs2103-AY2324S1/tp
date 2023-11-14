package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAllCommand.
 */

public class ListAllCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAllCommand(), model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyList_showsEmptyList() {
        Model emptyModel = new ModelManager();
        assertCommandSuccess(new ListAllCommand(), emptyModel, ListAllCommand.EMPTY_LIST_MESSAGE, emptyModel);
    }

    @Test
    public void execute_noExceptionThrown() {
        assertCommandSuccess(new ListAllCommand(), model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void toStringTest() {
        ListAllCommand listAllCommand = new ListAllCommand();
        assertEquals(listAllCommand.toString(), "ListAllCommand");
    }

    @Test
    public void equals() {
        ListAllCommand listAllCommand = new ListAllCommand();

        // same object -> returns true
        assertTrue(listAllCommand.equals(listAllCommand));

        // all list all command objects are the same -> returns true
        assertTrue(listAllCommand.equals(new ListAllCommand()));

        // null -> returns false
        assertFalse(listAllCommand.equals(null));

        // different types -> returns false
        Object differentObject = new Object();
        assertFalse(listAllCommand.equals(differentObject));
    }

}
