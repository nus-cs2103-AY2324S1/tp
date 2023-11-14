package seedu.address.logic.commands.finance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFinances.getTypicalFinancesBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.contacts.ClearContactsCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.EventsBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.finance.Finance;

public class DeleteFinanceCommandTest {
    private final Model model = new ModelManager(new AddressBook(), new EventsBook(), getTypicalFinancesBook(),
            new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Finance financeToDelete = model.getFilteredFinanceList().get(INDEX_FIRST.getZeroBased());
        DeleteFinanceCommand deleteFinanceCommand = new DeleteFinanceCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteFinanceCommand.MESSAGE_DELETE_FINANCE_SUCCESS,
                Messages.formatFinance(financeToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventsBook(),
                model.getFinancesBook(), new UserPrefs());
        expectedModel.deleteFinance(financeToDelete);

        assertCommandSuccess(deleteFinanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFinanceList().size() + 1);
        DeleteFinanceCommand deleteFinanceCommand = new DeleteFinanceCommand(outOfBoundIndex);

        assertCommandFailure(deleteFinanceCommand, model, Messages.MESSAGE_INVALID_FINANCE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteFinanceCommand standardCommand = new DeleteFinanceCommand(INDEX_FIRST);

        // same index -> true
        DeleteFinanceCommand deleteFirstFinanceCommand = new DeleteFinanceCommand(INDEX_FIRST);
        assertTrue(standardCommand.equals(deleteFirstFinanceCommand));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearContactsCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteFinanceCommand(INDEX_SECOND)));
    }
}
