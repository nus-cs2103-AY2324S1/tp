package transact.model;

import static java.util.Objects.requireNonNull;
import static transact.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import transact.model.entry.Entry;
import transact.model.entry.exceptions.DuplicateEntryException;
import transact.model.entry.exceptions.EntryNotFoundException;

/**
 * A list of entries that enforces uniqueness between its elements and does not
 * allow nulls.
 * An entry is considered unique by comparing using
 * {@code Entry#isSameEntry(Entry)}. As such, adding and updating of
 * entries uses Entry#isSameEntry(Entry) for equality so as to ensure that
 * the entry being added or updated is
 * unique in terms of identity in the UniqueEntryList. However, the removal of
 * an entry uses Entry#equals(Object) so
 * as to ensure that the entry with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Entry#isSameEntry(Entry)
 */
public class UniqueEntryList<E extends Entry> implements Iterable<E> {

    private final ObservableList<E> internalList = FXCollections.observableArrayList();
    private final ObservableList<E> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent entry as the given argument.
     */
    public boolean contains(E toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEntry);
    }

    /**
     * Adds an entry to the list.
     * The entry must not already exist in the list.
     */
    public void add(E toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEntryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the entry {@code target} in the list with {@code editedEntry}.
     * {@code target} must exist in the list.
     * The identity of {@code editedEntry} must not be the same as another
     * existing entry in the list.
     */
    public void setPerson(E target, E editedEntry) {
        requireAllNonNull(target, editedEntry);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EntryNotFoundException();
        }

        if (!target.isSameEntry(editedEntry) && contains(editedEntry)) {
            throw new DuplicateEntryException();
        }

        internalList.set(index, editedEntry);
    }

    /**
     * Removes the equivalent entry from the list.
     * The entry must exist in the list.
     */
    public void remove(E toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    public void setEntries(UniqueEntryList<? extends E> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code entries}.
     * {@code entries} must not contain duplicate entries.
     */
    public void setEntries(List<? extends E> entries) {
        requireAllNonNull(entries);
        if (!entriesAreUnique(entries)) {
            throw new DuplicateEntryException();
        }

        internalList.setAll(entries);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<E> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<E> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueEntryList<?>)) {
            return false;
        }

        UniqueEntryList<?> otherUniquePersonList = (UniqueEntryList<?>) other;
        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code entries} contains only unique entries.
     */
    private boolean entriesAreUnique(List<? extends E> entries) {
        for (int i = 0; i < entries.size() - 1; i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                if (entries.get(i).isSameEntry(entries.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
