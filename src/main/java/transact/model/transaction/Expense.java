package transact.model.transaction;

import transact.model.person.Person;
import transact.model.transaction.info.*;

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
        super(transactionId, Type.I, description, amount, new Date());
    }

    public Expense(TransactionId transactionId, Person person, Description description, Amount amount) {
        super(transactionId, Type.I, description, amount, new Date(), person);
    }
}
