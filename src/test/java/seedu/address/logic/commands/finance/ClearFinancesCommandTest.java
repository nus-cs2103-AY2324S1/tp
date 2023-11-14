package seedu.address.logic.commands.finance;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFinances.getTypicalFinancesBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.EventsBook;
import seedu.address.model.FinancesBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



public class ClearFinancesCommandTest {

    @Test
    public void execute_emptyFinanceBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearFinancesCommand(), model, ClearFinancesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFinanceBook_success() {
        Model model = new ModelManager(new AddressBook(), new EventsBook(), getTypicalFinancesBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(), new EventsBook(), getTypicalFinancesBook(),
                new UserPrefs());
        expectedModel.setFinancesBook(new FinancesBook());

        assertCommandSuccess(new ClearFinancesCommand(), model, ClearFinancesCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
