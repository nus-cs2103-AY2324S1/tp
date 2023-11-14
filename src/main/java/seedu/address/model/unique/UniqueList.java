package seedu.address.model.unique;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.unique.exceptions.DuplicateElementException;
import seedu.address.model.unique.exceptions.ElementNotFoundException;


/**
 * A generic unique list
 * @param <T> must implement Unique Interface
 */
public class UniqueList<T extends Unique<T>> implements Iterable<T> {
    protected final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent object as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isDuplicate);
    }

    /**
     * Adds a object to the list.
     * The object must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateElementException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the object {@code target} in the list with {@code editedObject}.
     * {@code target} must exist in the list.
     * The object identity of {@code editedObject} must not be the same as another existing object in the list.
     */
    public void set(T target, T editedObject) {
        requireAllNonNull(target, editedObject);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ElementNotFoundException();
        }

        if (!target.isDuplicate(editedObject) && contains(editedObject)) {
            throw new DuplicateElementException();
        }

        internalList.set(index, editedObject);
    }

    /**
     * Removes the equivalent object from the list.
     * The object must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ElementNotFoundException();
        }
    }

    public void setList(UniqueList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code objects}.
     * {@code objects} must not contain duplicate objects.
     */
    public void setList(List<T> list) {
        requireAllNonNull(list);
        if (!objectsAreUnique(list)) {
            throw new DuplicateElementException();
        }

        internalList.setAll(list);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueList<?>)) {
            return false;
        }

        UniqueList<?> otherUniqueList = (UniqueList<?>) other;
        return internalList.equals(otherUniqueList.internalList);
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
    private boolean objectsAreUnique(List<T> objects) {
        for (int i = 0; i < objects.size() - 1; i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                if (objects.get(i).isDuplicate(objects.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sorts internalList by given comparator
     */
    public void sort(Comparator<T> comparator) {
        internalList.sort(comparator);
    }

}
