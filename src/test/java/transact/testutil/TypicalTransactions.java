package transact.testutil;

import static transact.testutil.TypicalPersons.ALICE;

import transact.model.TransactionBook;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;

/**
 * A utility class containing a list of {@code Transaction} objects to be used
 * in tests.
 */
public class TypicalTransactions {
    public static final Transaction APPLES = new Transaction(new TransactionId(), ALICE, new Description("Apples"),
            new Amount(1.50));
    public static final Transaction BANANAS = new Transaction(new TransactionId(), new Description("Bananas"),
            new Amount(2.50));
    public static final Transaction CARROTS = new Transaction(new TransactionId(), new Description("Carrots"),
            new Amount(3.50));
    public static final Transaction DATES = new Transaction(new TransactionId(), new Description("Dates"),
            new Amount(4.50));
    public static final Transaction EGGS = new Transaction(new TransactionId(), new Description("Eggs"),
            new Amount(5.50));
    public static final Transaction FISH = new Transaction(new TransactionId(), new Description("Fish"),
            new Amount(6.50));

    /**
     * Returns a {@code TransactionBook} with all the typical transactions.
     */
    public static TransactionBook getTypicalTransactionBook() {
        TransactionBook tb = new TransactionBook();
        for (Transaction transaction : getTypicalTransactions()) {
            tb.addTransaction(transaction);
        }
        return tb;
    }

    public static Transaction[] getTypicalTransactions() {
        return new Transaction[] { APPLES, BANANAS, CARROTS, DATES, EGGS, FISH };
    }
}
