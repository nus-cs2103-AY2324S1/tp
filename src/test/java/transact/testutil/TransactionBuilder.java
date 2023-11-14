package transact.testutil;

import static transact.testutil.TypicalPersons.ALICE;

import transact.model.person.Person;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;
import transact.model.transaction.info.TransactionType;

/**
 * A utility class to help with building Transaction objects.
 */
public class TransactionBuilder {

    public static final Integer DEFAULT_ID = 0;
    public static final String DEFAULT_TYPE = "Expense";
    public static final String DEFAULT_DESC = "Shibas";
    public static final float DEFAULT_AMOUNT = 10.34f;
    public static final String DEFAULT_DATE = "11/11/23";
    public static final Person DEFAULT_PERSON = ALICE;

    private TransactionId id;
    private TransactionType transactionType;
    private Description description;
    private Amount amount;
    private Date date;
    private Integer personId;

    /**
     * Creates a {@code TransactionBuilder} with the default details.
     */
    public TransactionBuilder() {
        id = new TransactionId(DEFAULT_ID);
        transactionType = TransactionType.getType(DEFAULT_TYPE);
        description = new Description(DEFAULT_DESC);
        amount = new Amount(DEFAULT_AMOUNT);
        date = new Date(DEFAULT_DATE);
        personId = DEFAULT_PERSON.getPersonId().getValue();
    }

    /**
     * Initializes the TransactionBuilder with the data of
     * {@code transactionToCopy}.
     */
    public TransactionBuilder(Transaction transactionToCopy) {
        id = transactionToCopy.getTransactionId();
        transactionType = transactionToCopy.getTransactionType();
        description = transactionToCopy.getDescription();
        amount = transactionToCopy.getAmount();
        date = new Date(DEFAULT_DATE);
        personId = transactionToCopy.getPersonId();
    }

    /**
     * Sets the {@code transactionType} of the {@code Transaction} that we are
     * building.
     */
    public TransactionBuilder withType(String transactionType) {
        this.transactionType = TransactionType.getType(transactionType);
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withId(Integer id) {
        this.id = new TransactionId(id);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withDescription(String desc) {
        this.description = new Description(desc);
        return this;
    }

    /** Sets the {@code Amount} of the {@code Transaction} that we are building. */
    public TransactionBuilder withAmount(float amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /** Sets the {@code Date} of the {@code Transaction} that we are building. */
    public TransactionBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code personId} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withPersonId(Integer personId) {
        this.personId = personId;
        return this;
    }

    public Transaction build() {
        return new Transaction(id, transactionType, description, amount, date, personId);
    }
}
