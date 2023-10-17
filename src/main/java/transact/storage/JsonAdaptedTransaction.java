package transact.storage;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import transact.commons.exceptions.IllegalValueException;
import transact.model.person.Person;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;
import transact.model.transaction.info.TransactionType;

/**
 * Jackson-friendly version of {@link Transaction}.
 */
class JsonAdaptedTransaction {

    private final Integer transactionId;
    private final JsonAdaptedPerson person;
    private final String description;
    private final BigDecimal amount;

    @JsonCreator
    public JsonAdaptedTransaction(@JsonProperty("transactionId") Integer transactionId,
            @JsonProperty("person") JsonAdaptedPerson person,
            @JsonProperty("description") String description,
            @JsonProperty("amount") BigDecimal amount) {
        this.transactionId = transactionId;
        this.person = person;
        this.description = description;
        this.amount = amount;
    }

    public JsonAdaptedTransaction(Transaction source) {
        transactionId = source.getTransactionId().getValue();
        if (source.hasPersonInfo()) {
            person = new JsonAdaptedPerson(source.getPerson());
        } else {
            person = null;
        }
        description = source.getDescription().getValue();
        amount = source.getAmount().getValue();
    }

    public Transaction toModelType() throws IllegalValueException {
        final TransactionId modelTransactionId = new TransactionId(transactionId);

        if (person != null) {
            final Person modelPerson = person.toModelType();
            final Description modelDescription = new Description(description);
            final Amount modelAmount = new Amount(amount);
            return new Transaction(modelTransactionId, TransactionType.REVENUE, modelDescription, modelAmount,
                    new Date(), modelPerson);
        } else {
            final Description modelDescription = new Description(description);
            final Amount modelAmount = new Amount(amount);
            return new Transaction(modelTransactionId, TransactionType.EXPENSE, modelDescription, modelAmount,
                    new Date(), null);
        }
    }
}
