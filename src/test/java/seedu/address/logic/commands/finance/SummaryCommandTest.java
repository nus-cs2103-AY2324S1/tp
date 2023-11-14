package seedu.address.logic.commands.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalFinances.getTypicalFinancesBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EventsBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.finance.ClientNameExactMatchPredicate;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Expense;
import seedu.address.testutil.CommissionBuilder;
import seedu.address.testutil.ExpenseBuilder;
public class SummaryCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPersonsBook(), new EventsBook(), getTypicalFinancesBook(), new UserPrefs());
    }

    @Test
    public void testExecute_summarySuccessful() throws CommandException {
        // Populate the model with finances for Alice Pauline
        Commission commission = new CommissionBuilder().withPerson("Alice Pauline").withAmount("200").build();
        Expense expense = new ExpenseBuilder().withPerson("Alice Pauline").withAmount("100").build();
        model.addCommission(commission);
        model.addExpense(expense);
        SummaryCommand summaryCommand = new SummaryCommand(new ClientNameExactMatchPredicate("Alice Pauline"));
        CommandResult commandResult = summaryCommand.execute(model);
        String expectedSuccessMessage = "You have earned $100 from Alice Pauline\n"
                                + "There were 1 commissions for a total of $200.\n"
                                + "There were 1 expenses for a total of $100.";
        assertEquals(expectedSuccessMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void testExecute_clientNotFound() {
        SummaryCommand summaryCommand = new SummaryCommand(new ClientNameExactMatchPredicate("Alice"));
        assertThrows(CommandException.class, () -> summaryCommand.execute(model));
    }
}

