package seedu.address.logic.commands.listcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListPersonsCommandTest {

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
                new ListPersonsCommand(), model, ListPersonsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY);
        CommandTestUtil.assertRegularCommandSuccess(
                new ListPersonsCommand(), model, ListPersonsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals_sameCommand_returnsTrue() {
        // same object
        ListPersonsCommand listPersonsCommand = new ListPersonsCommand();
        assertTrue(listPersonsCommand.equals(listPersonsCommand));

        // different object
        assertTrue(listPersonsCommand.equals(new ListPersonsCommand()));
    }

    @Test
    public void equals_differentCommand_returnsFalse() {
        // null object
        ListPersonsCommand listPersonsCommand = new ListPersonsCommand();
        assertFalse(listPersonsCommand.equals(null));

        // different command
        assertFalse(listPersonsCommand.equals(new ListCompaniesCommand()));
    }
}
