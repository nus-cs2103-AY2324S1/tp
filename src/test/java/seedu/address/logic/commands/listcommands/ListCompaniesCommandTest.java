package seedu.address.logic.commands.listcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
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
        CommandTestUtil.assertRegularCommandSuccess(
                new ListCompaniesCommand(), model, ListCompaniesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showCompanyAtIndex(model, INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY);
        CommandTestUtil.assertRegularCommandSuccess(
                new ListCompaniesCommand(), model, ListCompaniesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals_sameCommand_returnsTrue() {
        // same object
        ListCompaniesCommand listCompaniesCommand = new ListCompaniesCommand();
        assertTrue(listCompaniesCommand.equals(listCompaniesCommand));

        // different object
        assertTrue(listCompaniesCommand.equals(new ListCompaniesCommand()));
    }

    @Test
    public void equals_differentCommand_returnsFalse() {
        // null object
        ListCompaniesCommand listCompaniesCommand = new ListCompaniesCommand();
        assertFalse(listCompaniesCommand.equals(null));

        // different command
        assertFalse(listCompaniesCommand.equals(new ListPersonsCommand()));
    }
}
