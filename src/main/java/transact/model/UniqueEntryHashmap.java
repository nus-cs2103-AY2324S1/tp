package transact.model;

import static java.util.Objects.requireNonNull;
import static transact.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Map;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import transact.model.entry.Entry;
import transact.model.entry.exceptions.DuplicateEntryException;
import transact.model.entry.exceptions.EntryNotFoundException;

/**
 * UniqueEntryHashmap class
 */
public class UniqueEntryHashmap<K, V extends Entry> {

    private final ObservableMap<K, V> internalMap = FXCollections.observableMap(new TreeMap<>());
    private final ObservableMap<K, V> internalUnmodifiableMap = FXCollections
            .unmodifiableObservableMap(internalMap);

    /**
     * Returns true if the map contains an equivalent entry as the given argument.
     */
    public boolean contains(V toCheck) {
        requireNonNull(toCheck);
        return internalMap.values().stream().anyMatch(toCheck::isSameEntry);
    }

    /**
     * Returns true if the map contains the key as the given argument.
     */
    public boolean contains(K key) {
        requireNonNull(key);
        return internalMap.containsKey(key);
    }

    /**
     * Adds an entry to the list.
     * The entry must not already exist in the list.
     */
    public void add(K key, V toAdd) {
        requireAllNonNull(key, toAdd);
        if (contains(toAdd) || contains(key)) {
            throw new DuplicateEntryException();
        }
        internalMap.put(key, toAdd);
    }

    /**
     * Replaces the entry {@code target} in the list with {@code editedEntry}.
     * {@code target} must exist in the list.
     * The identity of {@code editedEntry} must not be the same as another
     * existing entry in the list.
     */
    public void setEntry(K target, V editedEntry) {
        requireAllNonNull(target, editedEntry);
        internalMap.put(target, editedEntry);
    }

    /**
     * Removes the equivalent entry from the list.
     * The entry must exist in the list.
     */
    public V remove(K keyToRemove) {
        requireNonNull(keyToRemove);
        V v = internalMap.remove(keyToRemove);
        if (v == null) {
            throw new EntryNotFoundException();
        }
        return v;
    }

    public void setEntries(UniqueEntryHashmap<? extends K, ? extends V> replacement) {
        requireNonNull(replacement);
        internalMap.clear();
        internalMap.putAll(replacement.internalMap);
    }

    /**
     * Replaces the contents of this list with {@code entries}.
     * {@code entries} must not contain duplicate entries.
     */
    public void setEntries(Map<? extends K, ? extends V> entries) {
        requireAllNonNull(entries);

        internalMap.clear();
        internalMap.putAll(entries);
    }

    /**
     * Returns the backing map as an unmodifiable {@code ObservableMap}.
     */
    public ObservableMap<K, V> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueEntryHashmap<?, ?>)) {
            return false;
        }

        UniqueEntryHashmap<?, ?> otherUniqueEntryMap = (UniqueEntryHashmap<?, ?>) other;
        return internalMap.equals(otherUniqueEntryMap.internalMap);
    }

    @Override
    public int hashCode() {
        return internalMap.hashCode();
    }

    @Override
    public String toString() {
        return internalMap.toString();
    }
}
