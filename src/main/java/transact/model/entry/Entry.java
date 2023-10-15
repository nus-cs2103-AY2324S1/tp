package transact.model.entry;

/**
 * Represents an entry that can be stored in the address/transaction book.
 */
public interface Entry {
    /**
     * Returns true if both entries have the same identity. The exact way of
     * checking identity is defined in the implementation itself, but is
     * likely to be a weaker form of equality than {@code equals(Object)}.
     *
     * Returns true if both objects have the same identity.
     *
     * @param otherEntry The other entry to compare with.
     */
    boolean isSameEntry(Entry otherEntry);
}
