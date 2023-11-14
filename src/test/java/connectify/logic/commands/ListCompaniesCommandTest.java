package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
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
 * Contains integration tests (interaction with the Model) and unit tests for ListCompaniesCommand.
 */

public class ListCompaniesCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCompaniesCommand(), model, ListCompaniesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);
        assertCommandSuccess(new ListCompaniesCommand(), model, ListCompaniesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsEmpty_showsEmptyList() {
        model = new ModelManager();
        expectedModel = new ModelManager();
        assertCommandSuccess(new ListCompaniesCommand(), model, ListCompaniesCommand.EMPTY_LIST_MESSAGE, expectedModel);
    }

    @Test
    public void toStringTest() {
        ListCompaniesCommand listCompaniesCommand = new ListCompaniesCommand();
        assertEquals(listCompaniesCommand.toString(), "ListCompaniesCommand");
    }

    @Test
    public void equals() {
        Command listCompaniesCommand = new ListCompaniesCommand();

        // same object -> returns true
        assertTrue(listCompaniesCommand.equals(listCompaniesCommand));

        // all listCompaniesCommand -> returns true
        assertTrue(listCompaniesCommand.equals(new ListCompaniesCommand()));

        // null -> returns false
        assertFalse(listCompaniesCommand.equals(null));


    }


}
