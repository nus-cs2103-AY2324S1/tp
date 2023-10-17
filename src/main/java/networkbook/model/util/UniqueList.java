package networkbook.model.util;

import static java.util.Objects.requireNonNull;
import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import networkbook.model.person.exceptions.DuplicateException;
import networkbook.model.person.exceptions.ItemNotFoundException;

/**
 * Represents a list of items that need to be unique.
 * @param <T> The class representing the item.
 */
public class UniqueList<T extends Identifiable<T>> implements Iterable<T> {
    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks whether the list contains the element.
     * This makes use of {@code T::equals}.
     * @param toCheck The element to check.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    /**
     * Adds a new element to the list.
     * The element must not already exist in the list.
     * @param toAdd The element to add.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateException();
        }
        internalList.add(toAdd);
    }

    /**
     * Adds all elements from the specified list to this list.
     * If there are items in the specified list that are already in this list, they are simply ignored.
     */
    public void addAllFromList(UniqueList<T> listToAddFrom) {
        requireNonNull(listToAddFrom);
        listToAddFrom.internalList.forEach(toAdd -> {
            if (!contains(toAdd)) {
                internalList.add(toAdd);
            }
        });
    }

    /**
     * Replaces the element {@code target} in the list with {@code edited}.
     * {@code target} must exist in the list.
     * @param target The target to replace.
     * @param edited The new value of the
     */
    public void setItem(T target, T edited) {
        requireAllNonNull(target, edited);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ItemNotFoundException();
        }

        if (!target.isSame(edited) && contains(edited)) {
            throw new DuplicateException();
        }

        internalList.set(index, edited);
    }

    /**
     * Removes the equivalent element from the list.
     * The element must exist in the list.
     * @param toRemove The element to remove.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Checks if this list is empty.
     */
    public boolean isEmpty() {
        return this.internalList.size() == 0;
    }

    /**
     * Replaces the contents of this list with {@code items}.
     * {@code items} must not contain duplicate items.
     * @param items Items to replace.
     * @return This list itself.
     */
    public UniqueList<T> setItems(List<T> items) {
        requireAllNonNull(items);
        if (!itemsAreUnique(items)) {
            throw new DuplicateException();
        }
        internalList.setAll(items);
        return this;
    }

    public UniqueList<T> setItems(UniqueList<T> items) {
        requireNonNull(items);
        internalList.setAll(items.internalList);
        return this;
    }

    private boolean itemsAreUnique(List<T> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).isSame(items.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public Stream<T> stream() {
        return this.internalList.stream();
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    /**
     * Creates a copy of this list.
     * This copy can be used to avoid modifying this list.
     */
    public UniqueList<T> copy() {
        UniqueList<T> newUniqueList = new UniqueList<>();
        newUniqueList.addAllFromList(this);
        return newUniqueList;
    }

    @Override
    public boolean equals(Object another) {
        if (another == this) {
            return true;
        }

        if (!(another instanceof UniqueList<?>)) {
            return false;
        }

        UniqueList<?> anotherList = (UniqueList<?>) another;
        return internalList.equals(anotherList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }
}
