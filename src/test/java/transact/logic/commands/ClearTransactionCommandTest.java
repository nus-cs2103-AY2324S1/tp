package transact.logic.commands;

import static transact.logic.commands.CommandTestUtil.assertCommandSuccess;
import static transact.testutil.TypicalPersons.getTypicalAddressBook;
import static transact.testutil.TypicalTransactions.getTypicalTransactionBook;

import org.junit.jupiter.api.Test;

import transact.model.Model;
import transact.model.ModelManager;
import transact.model.TransactionBook;
import transact.model.UserPrefs;


public class ClearTransactionCommandTest {

    @Test
    public void execute_emptyTransactionBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearTransactionCommand(), model, ClearTransactionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTransactionBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTransactionBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTransactionBook(), new UserPrefs());
        expectedModel.setTransactionBook(new TransactionBook());

        assertCommandSuccess(new ClearTransactionCommand(), model, ClearTransactionCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
