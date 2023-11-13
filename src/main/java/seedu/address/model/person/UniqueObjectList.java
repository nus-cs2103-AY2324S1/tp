package seedu.address.model.person;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of objects that enforces uniqueness between its elements and does not allow nulls.
 * Classes that inherit from this should have their own specifications to accomplish this.
 *
 * Supports a minimal set of list operations.
 */
public abstract class UniqueObjectList<T> implements Iterable<T> {

    protected final ObservableList<T> internalList = FXCollections.observableArrayList();
    protected final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public abstract boolean contains(T toCheck);

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public abstract void add(T toAdd);

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public abstract void setObject(T target, T editedObject);

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public abstract void remove(T toRemove);

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public abstract void setObjects(List<T> objects);

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public abstract ObservableList<T> asUnmodifiableObservableList();

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueObjectList)) {
            return false;
        }

        UniqueObjectList<?> otherUniqueObjectList = (UniqueObjectList<?>) other;
        return internalList.equals(otherUniqueObjectList.internalList);
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
     * Returns true if {@code persons} contains only unique persons.
     */
    protected abstract boolean objectsAreUnique(List<T> objects);
}

