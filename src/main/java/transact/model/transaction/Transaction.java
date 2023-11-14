package transact.model.transaction;

import java.util.Objects;

import transact.commons.util.ToStringBuilder;
import transact.model.entry.Entry;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;
import transact.model.transaction.info.TransactionType;

/**
 * Represents a Transaction in the address book.
 */
public class Transaction implements Entry {

    public static final String MESSAGE_CONSTRAINTS = "This Transaction does not have a linked staff";

    private final TransactionId transactionId;
    private final TransactionType transactionType;
    private final Description description;
    private final Amount amount;
    private final Date date;
    private final Integer personId;

    /**
     * Creates a new Transaction.
     *
     * @param transactionType
     *            The transaction type of the transaction.
     * @param description
     *            The description of the transaction.
     * @param amount
     *            The amount of the transaction.
     * @param date
     *            The date of the transaction.
     * @param personId
     *            The ID of the person associated with the transaction.
     */
    public Transaction(TransactionType transactionType, Description description, Amount amount, Date date,
            Integer personId) {
        this(new TransactionId(), transactionType, description, amount, date, personId);
    }

    /**
     * Creates a new Transaction.
     *
     * @param transactionId
     *            The unique transaction ID.
     * @param transactionType
     *            The transaction type of the transaction.
     * @param description
     *            The description of the transaction.
     * @param amount
     *            The amount of the transaction.
     * @param date
     *            The date of the transaction.
     */
    public Transaction(TransactionId transactionId, TransactionType transactionType, Description description,
            Amount amount, Date date) {
        this(transactionId, transactionType, description, amount, date, -1);
    }

    /**
     * Creates a new Transaction.
     *
     * @param transactionType
     *            The transaction type of the transaction.
     * @param description
     *            The description of the transaction.
     * @param amount
     *            The amount of the transaction.
     * @param date
     *            The date of the transaction.
     */
    public Transaction(TransactionType transactionType, Description description,
            Amount amount, Date date) {
        this(new TransactionId(), transactionType, description, amount, date);
    }

    /**
     * Creates a new Transaction.
     *
     * @param transactionId
     *            The unique transaction ID.
     * @param transactionType
     *            The transaction type of the transaction.
     * @param description
     *            The description of the transaction.
     * @param amount
     *            The amount of the transaction.
     * @param date
     *            The date of the transaction.
     * @param personId
     *            The ID of the person associated with the transaction.
     */
    public Transaction(TransactionId transactionId, TransactionType transactionType, Description description,
            Amount amount, Date date, Integer personId) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.personId = personId;
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public Boolean hasPersonInfo() {
        return personId >= 0;
    }

    public Integer getPersonId() {
        return personId;
    }

    public Description getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
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
                && Objects.equals(transactionType, that.transactionType)
                && Objects.equals(description, that.description)
                && Objects.equals(amount, that.amount)
                && Objects.equals(date, that.date)
                && Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, transactionType, description, amount, personId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("transactionId", transactionId)
                .add("type", transactionType)
                .add("description", description)
                .add("amount", amount)
                .add("date", date)
                .add("personId", personId)
                .toString();
    }

    @Override
    public boolean isSameEntry(Entry otherEntry) {
        if (otherEntry == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(otherEntry instanceof Transaction)) {
            return false;
        }

        Transaction otherTransaction = (Transaction) otherEntry;
        return Objects.equals(transactionId, otherTransaction.transactionId);
    }
}
