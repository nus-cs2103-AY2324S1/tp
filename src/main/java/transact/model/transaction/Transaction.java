package transact.model.transaction;

import static transact.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import transact.commons.util.ToStringBuilder;
import transact.model.person.Person;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;

/**
 * Represents a Transaction in the address book.
 */
public class Transaction {

    public static final String MESSAGE_CONSTRAINTS = "This Transaction does not contain information about people involved";

    private final TransactionId transactionId;
    private final Person person;
    private final Description description;
    private final Amount amount;

    /**
     * Creates a new Transaction.
     *
     * @param transactionId
     *            The unique transaction ID.
     * @param person
     *            The person associated with the transaction.
     * @param description
     *            The description of the transaction.
     * @param amount
     *            The amount of the transaction.
     */
    public Transaction(TransactionId transactionId, Person person, Description description, Amount amount) {
        this.transactionId = transactionId;
        this.person = person;
        this.description = description;
        this.amount = amount;
    }

    /**
     * Creates a new Transaction.
     *
     * @param transactionId
     *            The unique transaction ID.
     * @param description
     *            The description of the transaction.
     * @param amount
     *            The amount of the transaction.
     */
    public Transaction(TransactionId transactionId, Description description, Amount amount) {
        this.transactionId = transactionId;
        this.description = description;
        this.amount = amount;
        this.person = null;
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public Boolean hasPersonInfo() {
        return this.person != null;
    }

    public Person getPerson() {
        checkArgument(hasPersonInfo(), MESSAGE_CONSTRAINTS);
        return person;
    }

    public Description getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Transaction)) {
            return false;
        }

        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId)
                && Objects.equals(person, that.person)
                && Objects.equals(description, that.description)
                && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, person, description, amount);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("transactionId", transactionId)
                .add("person", person)
                .add("description", description)
                .add("amount", amount)
                .toString();
    }
}
