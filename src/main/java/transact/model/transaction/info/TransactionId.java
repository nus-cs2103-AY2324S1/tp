package transact.model.transaction.info;

import static java.util.Objects.requireNonNull;
import static transact.commons.util.AppUtil.checkArgument;

import java.util.TreeSet;

/**
 * Represents a unique transaction ID in the system.
 * Guarantees: immutable; is valid as declared in constructors
 */
public class TransactionId implements Comparable<TransactionId> {

    public static final String MESSAGE_CONSTRAINTS = "Transaction ID should be a unique number";
    public static final String VALIDATION_REGEX = "^\\d+$";

    private static TreeSet<Integer> usedIds = new TreeSet<>();

    private final Integer value;

    /**
     * Constructs a {@code TransactionId}.
     *
     */
    public TransactionId() {
        this(usedIds.isEmpty() ? 0 : usedIds.last() + 1);
    }

    /**
     * Constructs a {@code TransactionId} with specified {@code id}
     */
    public TransactionId(Integer id) {
        requireNonNull(id);
        checkArgument(isValidTransactionId(id), MESSAGE_CONSTRAINTS);
        value = id;
        usedIds.add(value);
    }

    /**
     * Constructs a {@code TransactionId} with specified {@code id}
     */
    public TransactionId(String id) {
        this(parseTransactionId(id));
    }

    /**
     * Parse transaction ID from string.
     */
    public static Integer parseTransactionId(String test) {
        checkArgument(test.matches(VALIDATION_REGEX), MESSAGE_CONSTRAINTS);
        return Integer.parseInt(test);
    }

    /**
     * Returns true if a given integer can be a new transaction ID.
     */
    public static boolean isValidTransactionId(Integer test) {
        return !usedIds.contains(test);
    }

    /**
     * Free up {@code usedId}, allowing it to be used when transaction object is
     * deleted
     *
     * @param usedId
     *            Id that will not be used anymore
     */
    public static void freeUsedTransactionIds(Integer usedId) {
        usedIds.remove(usedId);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
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
    public int compareTo(TransactionId otherId) {
        return this.value.compareTo(otherId.getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
