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
    private final TransactionType type;
    private final String description;
    private final BigDecimal amount;
    private final String date;

    @JsonCreator
    public JsonAdaptedTransaction(@JsonProperty("transactionId") Integer transactionId,
            @JsonProperty("TransactionType") TransactionType type,
            @JsonProperty("person") JsonAdaptedPerson person,
            @JsonProperty("description") String description,
            @JsonProperty("amount") BigDecimal amount, @JsonProperty("date") String date) {
        this.transactionId = transactionId;
        this.type = type;
        this.person = person;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public JsonAdaptedTransaction(Transaction source) {
        transactionId = source.getTransactionId().getValue();
        if (source.hasPersonInfo()) {
            person = new JsonAdaptedPerson(source.getPerson());
        } else {
            person = null;
        }
        description = source.getDescription().getValue();
        type = source.getTransactionType();
        amount = source.getAmount().getValue();
        date = source.getDate().getValue();
    }

    public Transaction toModelType() throws IllegalValueException {
        final TransactionId modelTransactionId = new TransactionId(transactionId);
        final Amount modelAmount = new Amount(amount);
        final Date modelDate = new Date(date);
        final Description modelDescription = new Description(description);
        final TransactionType modelType = type;
        if (person != null) {
            final Person modelPerson = person.toModelType();
            return new Transaction(modelTransactionId, modelType, modelDescription, modelAmount,
                    modelDate, modelPerson);
        } else {
            return new Transaction(modelTransactionId, modelType, modelDescription, modelAmount,
                    modelDate);
        }
    }
}
