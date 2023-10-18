package transact.logic.commands;

import static transact.logic.commands.CommandTestUtil.assertCommandSuccess;
import static transact.logic.commands.CommandTestUtil.showPersonAtId;
import static transact.testutil.TypicalIndexes.ID_FIRST_PERSON;
import static transact.testutil.TypicalPersons.getTypicalAddressBook;
import static transact.testutil.TypicalTransactions.getTypicalTransactionBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import transact.model.Model;
import transact.model.ModelManager;
import transact.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * ListCommand.
 */
public class ViewCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTransactionBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getTransactionBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewCommand(ViewCommand.ViewType.STAFF), model, ViewCommand.MESSAGE_SUCCESS_STAFF,
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtId(model, ID_FIRST_PERSON);
        assertCommandSuccess(new ViewCommand(ViewCommand.ViewType.STAFF), model, ViewCommand.MESSAGE_SUCCESS_STAFF,
                expectedModel);
    }
}
