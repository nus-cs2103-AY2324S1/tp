package transact.testutil;

import static transact.testutil.TypicalPersons.ALICE;

import transact.model.person.Person;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;
import transact.model.transaction.info.Type;

/**
 * A utility class to help with building Transaction objects.
 */
public class TransactionBuilder {

    public static final String DEFAULT_ID = "AAAAAAAA";
    public static final String DEFAULT_DESC = "Shibas";
    public static final float DEFAULT_AMOUNT = 10.34f;
    public static final Person DEFAULT_PERSON = ALICE;

    private TransactionId id;
    private Description description;
    private Amount amount;
    private Person person;

    /**
     * Creates a {@code TransactionBuilder} with the default details.
     */
    public TransactionBuilder() {
        id = new TransactionId(DEFAULT_ID);
        description = new Description(DEFAULT_DESC);
        amount = new Amount(DEFAULT_AMOUNT);
        person = DEFAULT_PERSON;
    }

    /**
     * Initializes the TransactionBuilder with the data of {@code transactionToCopy}.
     */
    public TransactionBuilder(Transaction transactionToCopy) {
        id = transactionToCopy.getTransactionId();
        description = transactionToCopy.getDescription();
        amount = transactionToCopy.getAmount();
        if (transactionToCopy.hasPersonInfo()) {
            person = transactionToCopy.getPerson();
        } else {
            person = null;
        }
    }

    /**
     * Sets the {@code id} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withId(String id) {
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

    /** Sets the {@code Person} of the {@code Transaction} that we are building. */
    public TransactionBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }

    public Transaction build() {
        return new Transaction(id, Type.E, description, amount, new Date(), person);
    }
}
