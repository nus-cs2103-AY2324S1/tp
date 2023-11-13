package seedu.address.model.person;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of objects that enforces uniqueness between its elements and does not allow nulls.
 * Classes that inherit from this should have their own specifications to accomplish this.
 * This class supports a minimal set of list operations.
 *
 * @param <T> The type of objects stored in the list.
 */
public abstract class UniqueObjectList<T> implements Iterable<T> {

    protected final ObservableList<T> internalList = FXCollections.observableArrayList();
    protected final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent object as the given argument.
     */
    public abstract boolean contains(T toCheck);

    /**
     * Adds an object to the list.
     * The object must not already exist in the list.
     */
    public abstract void add(T toAdd);

    /**
     * Replaces the object {@code target} in the list with {@code editedObject}.
     * {@code target} must exist in the list.
     * The identity of {@code editedObject} must not be the same as another existing object in the list.
     */
    public abstract void setObject(T target, T editedObject);

    /**
     * Removes the equivalent object from the list.
     * The object must exist in the list.
     */
    public abstract void remove(T toRemove);

    /**
     * Replaces the contents of this list with {@code objects}.
     * {@code objects} must not contain duplicate objectss.
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
     * Returns true if {@code objects} contains only unique objects.
     */
    protected abstract boolean objectsAreUnique(List<T> objects);
}

