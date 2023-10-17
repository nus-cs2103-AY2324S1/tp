package transact.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javafx.collections.ObservableList;
import transact.commons.exceptions.IllegalValueException;
import transact.model.ReadOnlyTransactionBook;
import transact.model.TransactionBook;
import transact.model.transaction.Transaction;

/**
 * An Immutable TransactionBook that is serializable to JSON format.
 */
@JsonRootName(value = "transactionBook")
public class JsonSerializableTransactionBook {

    public static final String MESSAGE_DUPLICATE_TRANSACTION = "Transaction log contains duplicate transactions.";

    private final List<JsonAdaptedTransaction> transactions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTransactionBook} with the given
     * transactions.
     */
    @JsonCreator
    public JsonSerializableTransactionBook(
            @JsonProperty("transactions") ObservableList<JsonAdaptedTransaction> transactions) {
        this.transactions.addAll(transactions);
    }

    /**
     * Converts a given {@code TransactionBook} into this class for Jackson use.
     *
     * @param source
     *            future changes to this will not affect the created
     *            {@code JsonSerializableTransactionBook}.
     */
    public JsonSerializableTransactionBook(ReadOnlyTransactionBook source) {
        transactions.addAll(source.getTransactionMap().values().stream()
                .map(JsonAdaptedTransaction::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this transaction log into the model's {@code TransactionBook}
     * object.
     *
     * @throws IllegalValueException
     *             if there were any data constraints violated.
     */
    public TransactionBook toModelType() throws IllegalValueException {
        TransactionBook transactionBook = new TransactionBook();
        for (JsonAdaptedTransaction jsonAdaptedTransaction : transactions) {
            Transaction transaction = jsonAdaptedTransaction.toModelType();
            if (transactionBook.hasTransaction(transaction.getTransactionId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRANSACTION);
            }
            transactionBook.addTransaction(transaction);
        }
        return transactionBook;
    }
}
