package transact.storage;

import static transact.model.transaction.info.TransactionId.isValidTransactionId;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import transact.commons.exceptions.IllegalValueException;
import transact.model.transaction.info.TransactionId;

/**
 * Jackson-friendly version of {@link TransactionId}.
 */
class JsonAdaptedTransactionId {

    private final String id;

    @JsonCreator
    public JsonAdaptedTransactionId(@JsonProperty("id") String id) {
        this.id = id;
    }

    public JsonAdaptedTransactionId(TransactionId source) {
        this.id = source.getValue();
    }

    @JsonValue
    public String getId() {
        return id;
    }

    public TransactionId toModelType() throws IllegalValueException {
        if (!isValidTransactionId(id)) {
            throw new IllegalValueException(TransactionId.MESSAGE_CONSTRAINTS);
        }
        return new TransactionId(id);
    }
}
