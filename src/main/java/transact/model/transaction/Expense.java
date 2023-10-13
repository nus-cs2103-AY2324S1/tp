package transact.model.transaction;

import transact.model.person.Person;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;

/**
 * Expense class
 */
public class Expense extends Transaction {

    /**
     * Creates a new Expense transaction.
     *
     * @param transactionId
     *            The unique transaction ID.
     * @param description
     *            The description of the expense.
     * @param amount
     *            The amount of the expense.
     */
    public Expense(TransactionId transactionId, Description description, Amount amount) {
        super(transactionId, description, amount);
    }

    public Expense(TransactionId transactionId, Person person, Description description, Amount amount) {
        super(transactionId, person, description, amount);
    }
}
