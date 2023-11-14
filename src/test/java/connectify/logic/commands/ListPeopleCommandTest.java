package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.logic.commands.CommandTestUtil.showPersonAtIndex;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
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
 * Contains integration tests (interaction with the Model) and unit tests for ListPeopleCommand.
 */
public class ListPeopleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPeopleCommand(), model, ListPeopleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListPeopleCommand(), model, ListPeopleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyList_showsEmptyList() {
        Model emptyModel = new ModelManager();
        assertCommandSuccess(new ListPeopleCommand(), emptyModel, ListPeopleCommand.EMPTY_LIST_MESSAGE, emptyModel);
    }

    @Test
    public void toStringTest() {
        ListPeopleCommand listPeopleCommand = new ListPeopleCommand();
        assertEquals(listPeopleCommand.toString(), "ListPeopleCommand");
    }

    @Test
    public void equals() {
        Command listPeopleCommand = new ListPeopleCommand();
        Command listPeopleCommand2 = new ListPeopleCommand();

        // same object -> returns true
        assertTrue(listPeopleCommand.equals(listPeopleCommand));

        // same values -> returns true
        assertTrue(listPeopleCommand.equals(listPeopleCommand2));

        // null -> returns false
        assertFalse(listPeopleCommand.equals(null));

    }
}
