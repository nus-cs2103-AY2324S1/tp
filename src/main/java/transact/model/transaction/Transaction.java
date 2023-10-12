package transact.model.transaction;

import transact.model.entry.Entry;

/**
 * Represents a Transaction in the address book.
 */
public class Transaction implements Entry {
    @Override
    public boolean isSameEntry(Entry otherEntry) {
        // TODO Use ID to compare
        return false;
    }
}
