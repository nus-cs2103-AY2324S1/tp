package transact.model.transaction.info;
import transact.commons.util.StringUtil;

import static java.util.Objects.requireNonNull;
import static transact.commons.util.AppUtil.checkArgument;

/**
 * Represents a unique transaction ID in the system.
 * Guarantees: immutable; is valid as declared in {@link #isValidTransactionId(String)}
 */
public class TransactionId {

    public static final String MESSAGE_CONSTRAINTS = "Transaction ID should be an 8-character alphanumeric string";
    public static final String VALIDATION_REGEX = "[A-Za-z0-9]{8}";
    public final String value;
    public final int transactionIdLength = 8;

    /**
     * Constructs a {@code TransactionId}.
     *
     */
    public TransactionId() {
        value = StringUtil.generateRandomString(transactionIdLength);
        requireNonNull(value);
        checkArgument(isValidTransactionId(value), MESSAGE_CONSTRAINTS);
    }

    public TransactionId(String id) {
        requireNonNull(id);
        checkArgument(isValidTransactionId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if a given string is a valid transaction ID.
     */
    public static boolean isValidTransactionId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TransactionId)) {
            return false;
        }

        TransactionId otherId = (TransactionId) other;
        return value.equals(otherId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
