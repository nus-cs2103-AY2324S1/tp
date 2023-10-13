package transact.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javafx.collections.ObservableList;
import transact.commons.exceptions.IllegalValueException;
import transact.model.ReadOnlyTransactionLog;
import transact.model.TransactionLog;
import transact.model.transaction.Transaction;

/**
 * An Immutable TransactionLog that is serializable to JSON format.
 */
@JsonRootName(value = "transactionlog")
public class JsonSerializableTransactionLog {

    public static final String MESSAGE_DUPLICATE_TRANSACTION = "Transaction log contains duplicate transactions.";

    private final List<JsonAdaptedTransaction> transactions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTransactionLog} with the given
     * transactions.
     */
    @JsonCreator
    public JsonSerializableTransactionLog(
            @JsonProperty("transactions") ObservableList<JsonAdaptedTransaction> transactions) {
        this.transactions.addAll(transactions);
    }

    /**
     * Converts a given {@code TransactionLog} into this class for Jackson use.
     *
     * @param source
     *            future changes to this will not affect the created
     *            {@code JsonSerializableTransactionLog}.
     */
    public JsonSerializableTransactionLog(ReadOnlyTransactionLog source) {
        transactions.addAll(source.getTransactionList().stream()
                .map(JsonAdaptedTransaction::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this transaction log into the model's {@code TransactionLog} object.
     *
     * @throws IllegalValueException
     *             if there were any data constraints violated.
     */
    public TransactionLog toModelType() throws IllegalValueException {
        TransactionLog transactionLog = new TransactionLog();
        for (JsonAdaptedTransaction jsonAdaptedTransaction : transactions) {
            Transaction transaction = jsonAdaptedTransaction.toModelType();
            if (transactionLog.hasTransaction(transaction)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRANSACTION);
            }
            transactionLog.addTransaction(transaction);
        }
        return transactionLog;
    }
}
