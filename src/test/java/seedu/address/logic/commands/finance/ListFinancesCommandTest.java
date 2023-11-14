package seedu.address.logic.commands.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFinances.getTypicalCommissionOnlyBook;
import static seedu.address.testutil.TypicalFinances.getTypicalExpenseOnlyBook;
import static seedu.address.testutil.TypicalFinances.getTypicalFinancesBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contacts.ClearContactsCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.EventsBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.finance.FinanceListType;

public class ListFinancesCommandTest {
    private Model model;
    private Model expectedAllModel;
    private Model expectedExpenseOnlyModel;
    private Model expectedCommissionOnlyModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new EventsBook(), getTypicalFinancesBook(), new UserPrefs());
        expectedAllModel = new ModelManager(new AddressBook(), new EventsBook(), getTypicalFinancesBook(),
                new UserPrefs());
        expectedExpenseOnlyModel = new ModelManager(new AddressBook(), new EventsBook(), getTypicalExpenseOnlyBook(),
                new UserPrefs());
        expectedCommissionOnlyModel = new ModelManager(new AddressBook(), new EventsBook(),
                getTypicalCommissionOnlyBook(), new UserPrefs());
    }

    @Test
    public void execute_listShowsAll_listAllShowsAllList() {
        assertCommandSuccess(new ListFinancesCommand(FinanceListType.ALL),
                model, ListFinancesCommand.MESSAGE_SUCCESS_FINANCES, expectedAllModel);
        assertEquals(model.getFilteredFinanceList(), expectedAllModel.getFilteredFinanceList());
    }

    @Test
    public void execute_listShowsAll_listExpenseShowsExpenseOnlyList() {
        assertCommandSuccess(new ListFinancesCommand(FinanceListType.EXPENSE),
                model, ListFinancesCommand.MESSAGE_SUCCESS_EXPENSES, expectedExpenseOnlyModel);
        assertEquals(model.getFilteredFinanceList(), expectedExpenseOnlyModel.getFilteredFinanceList());
    }

    @Test
    public void execute_listShowsAll_listCommissionShowsCommissionOnlyList() {
        assertCommandSuccess(new ListFinancesCommand(FinanceListType.COMMISSION),
                model, ListFinancesCommand.MESSAGE_SUCCESS_COMMISSIONS, expectedCommissionOnlyModel);
        assertEquals(model.getFilteredFinanceList(), expectedCommissionOnlyModel.getFilteredFinanceList());
    }

    @Test
    public void execute_listShowsExpenseOrCommissionOnly_listAllShowsEverything() {
        assertCommandSuccess(new ListFinancesCommand(FinanceListType.EXPENSE),
                model, ListFinancesCommand.MESSAGE_SUCCESS_EXPENSES, expectedExpenseOnlyModel);
        assertCommandSuccess(new ListFinancesCommand(FinanceListType.ALL),
                model, ListFinancesCommand.MESSAGE_SUCCESS_FINANCES, expectedExpenseOnlyModel);
        assertEquals(model.getFilteredFinanceList(), expectedAllModel.getFilteredFinanceList());

        assertCommandSuccess(new ListFinancesCommand(FinanceListType.COMMISSION),
                model, ListFinancesCommand.MESSAGE_SUCCESS_COMMISSIONS, expectedCommissionOnlyModel);
        assertCommandSuccess(new ListFinancesCommand(FinanceListType.COMMISSION),
                model, ListFinancesCommand.MESSAGE_SUCCESS_COMMISSIONS, expectedCommissionOnlyModel);
        assertEquals(model.getFilteredFinanceList(), expectedCommissionOnlyModel.getFilteredFinanceList());

    }

    @Test
    public void equals() {
        final ListFinancesCommand standardCommand = new ListFinancesCommand(FinanceListType.ALL);

        // same list type -> returns true
        ListFinancesCommand listAllFinanceCommand = new ListFinancesCommand(FinanceListType.ALL);
        assertTrue(standardCommand.equals(listAllFinanceCommand));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearContactsCommand()));

        // different list type -> returns false
        assertFalse(standardCommand.equals(new ListFinancesCommand(FinanceListType.EXPENSE)));
    }

}
