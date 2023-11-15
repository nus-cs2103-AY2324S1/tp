package seedu.address.model.displayable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.displayable.exceptions.DisplayableNotFoundException;
import seedu.address.model.displayable.exceptions.DuplicateException;

/**
 * A list of displayables that enforces uniqueness between its elements and does not allow nulls.
 * A displayable is considered unique by comparing using {@code Displayable#isSameDisplayable(Displayable)}.
 * As such, adding and updating of displayables uses Displayable#isSameDisplayable(Displayable) for equality to
 * ensure that the displayable being added or updated is unique in terms of identity in the UniqueDisplayableList.
 * However, the removal of a displayable uses Displayable#equals(Object)
 * to ensure that the displayable with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Displayable#isSameDisplayable(Displayable)
 */
public class UniqueDisplayableList<T extends Displayable> implements Iterable<T> {

    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent displayable to the given argument.
     */
    public boolean contains(Displayable toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDisplayable);
    }
    /**
     * Returns true if the list contains a similar displayable to the given argument.
     */
    public boolean containsSimilar(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSimilarDisplayable);
    }

    /**
     * Adds a displayable to the list.
     * The displayable must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the displayable {@code target} in the list with {@code editedDisplayable}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedDisplayable} must not be the same as another existing person in the list.
     */
    public void setDisplayable(T target, T editedDisplayable) {
        requireAllNonNull(target, editedDisplayable);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DisplayableNotFoundException();
        }

        if (!target.isSameDisplayable(editedDisplayable) && contains(editedDisplayable)) {
            throw new DuplicateException();
        }

        internalList.set(index, editedDisplayable);
    }

    /**
     * Removes the equivalent displayable from the list.
     * The displayable must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DisplayableNotFoundException();
        }
    }

    public void setDisplayables(UniqueDisplayableList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code displayables}.
     * {@code displayables} must not contain duplicate displayables.
     */
    public void setDisplayables(List<? extends T> displayables) {
        requireAllNonNull(displayables);
        if (!displayablesAreUnique(displayables)) {
            throw new DuplicateException();
        }

        internalList.setAll(displayables);
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
        if (!(other instanceof UniqueDisplayableList)) {
            return false;
        }

        UniqueDisplayableList<?> otherUniqueDisplayableList = (UniqueDisplayableList<?>) other;
        return internalList.equals(otherUniqueDisplayableList.internalList);
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
     * Returns true if {@code displayables} contains only unique displayables.
     */
    private boolean displayablesAreUnique(List<? extends Displayable> displayables) {
        for (int i = 0; i < displayables.size() - 1; i++) {
            for (int j = i + 1; j < displayables.size(); j++) {
                if (displayables.get(i).isSameDisplayable(displayables.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
