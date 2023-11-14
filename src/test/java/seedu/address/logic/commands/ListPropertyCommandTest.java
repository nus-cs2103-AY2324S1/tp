package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandPropertyTestUtil.showPropertyAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPropertyCommand.
 */
public class ListPropertyCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getPropertyBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPropertyCommand(), model, ListPropertyCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);
        assertCommandSuccess(new ListPropertyCommand(), model, ListPropertyCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
