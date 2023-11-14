package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.testutil.TypicalPersons.getRankedAddressBook;
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
 * Contains integration tests (interaction with the Model) and unit tests for RankPersonCommand.
 */
public class RankPersonCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getRankedAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new RankPersonCommand(), model, RankPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        assertCommandSuccess(new RankPersonCommand(), model, RankPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyList_showsEmptyList() {
        Model emptyModel = new ModelManager();
        assertCommandSuccess(new RankPersonCommand(), emptyModel, RankPersonCommand.EMPTY_LIST_MESSAGE, emptyModel);
    }

    @Test
    public void toStringTest() {
        RankPersonCommand rankPersonCommand = new RankPersonCommand();
        assertEquals(rankPersonCommand.toString(), "RankPersonCommand");
    }

    @Test
    public void equals() {
        Command rankPersonCommand = new RankPersonCommand();
        Command rankPersonCommand2 = new RankPersonCommand();

        // same object -> returns true
        assertTrue(rankPersonCommand.equals(rankPersonCommand));

        // same values -> returns true
        assertTrue(rankPersonCommand.equals(rankPersonCommand2));

        // null -> returns false
        assertFalse(rankPersonCommand.equals(null));

    }
}
