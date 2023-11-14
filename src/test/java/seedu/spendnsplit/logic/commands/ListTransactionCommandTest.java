package seedu.spendnsplit.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.spendnsplit.logic.Messages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.spendnsplit.testutil.TypicalSpendNSplitBook.getTypicalSpendNSplitBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.spendnsplit.model.Model;
import seedu.spendnsplit.model.ModelManager;
import seedu.spendnsplit.model.UserPrefs;
import seedu.spendnsplit.model.person.Name;
import seedu.spendnsplit.model.transaction.TransactionContainsKeywordsAndPersonNamesPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTransactionCommand.
 */
public class ListTransactionCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSpendNSplitBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalSpendNSplitBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        TransactionContainsKeywordsAndPersonNamesPredicate firstPredicate =
                new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(),
                Collections.singletonList(new Name("first")));
        TransactionContainsKeywordsAndPersonNamesPredicate secondPredicate =
                new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(),
                Collections.singletonList(new Name("second")));

        ListTransactionCommand listFirstCommand = new ListTransactionCommand(firstPredicate);
        ListTransactionCommand listSecondCommand = new ListTransactionCommand(secondPredicate);

        // same object -> returns true
        assertEquals(listFirstCommand, listFirstCommand);

        // same values -> returns true
        ListTransactionCommand listFirstCommandCopy = new ListTransactionCommand(firstPredicate);
        assertEquals(listFirstCommand, listFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, listFirstCommand);

        // null -> returns false
        assertNotEquals(null, listFirstCommand);

        // different person name -> returns false
        assertNotEquals(listFirstCommand, listSecondCommand);
    }

    @Test
    public void execute_zeroNames_allTransactionsFound() {
        String expectedMessage = String.format(MESSAGE_TRANSACTIONS_LISTED_OVERVIEW, 5);
        TransactionContainsKeywordsAndPersonNamesPredicate predicate =
            new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(), Collections.emptyList());
        assertCommandSuccess(new ListTransactionCommand(predicate), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleNames_multipleTransactionsFound() {
        String expectedMessage = String.format(MESSAGE_TRANSACTIONS_LISTED_OVERVIEW, 5);
        TransactionContainsKeywordsAndPersonNamesPredicate predicate =
            new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(), Arrays.asList(
                    new Name("Alice Pauline"), new Name("Benson Meier")));
        expectedModel.updateFilteredTransactionList(predicate);
        assertCommandSuccess(new ListTransactionCommand(predicate), model, expectedMessage, expectedModel);
    }

    @Test
    public void toStringMethod() {
        TransactionContainsKeywordsAndPersonNamesPredicate predicate =
                new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(),
                Arrays.asList(new Name("Bob"), new Name("Carl")));
        ListTransactionCommand listTransactionCommand = new ListTransactionCommand(predicate);
        String expected = ListTransactionCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, listTransactionCommand.toString());
    }
}
