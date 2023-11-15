package seedu.address.model.stall;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.stall.exceptions.DuplicateStallException;
import seedu.address.model.stall.exceptions.StallNotFoundException;
import seedu.address.model.util.StallLocationComparator;
import seedu.address.model.util.StallPriceComparator;
import seedu.address.model.util.StallRatingComparator;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A stall is considered unique by comparing using {@code Stall#isSamePerson(Stall)}. As such, adding and updating of
 * persons uses Stall#isSamePerson(Stall) for equality to ensure that the stall being added or updated is
 * unique in terms of identity in the UniqueStallList. However, the removal of a stall uses Stall#equals(Object) so
 * as to ensure that the stall with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Stall#isSameStall(Stall)
 */
public class UniqueStallList implements Iterable<Stall> {

    private final ObservableList<Stall> internalList = FXCollections.observableArrayList();
    private final ObservableList<Stall> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent stall as the given argument.
     */
    public boolean contains(Stall toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStall);
    }

    /**
     * Adds a stall to the list.
     * The stall must not already exist in the list.
     */
    public void add(Stall toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStallException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the stall {@code target} in the list with {@code editedStall}.
     * {@code target} must exist in the list.
     * The stall identity of {@code editedStall} must not be the same as another existing stall in the list.
     */
    public void setStall(Stall target, Stall editedStall) {
        requireAllNonNull(target, editedStall);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StallNotFoundException();
        }

        if (!target.isSameStall(editedStall) && contains(editedStall)) {
            throw new DuplicateStallException();
        }

        internalList.set(index, editedStall);
    }

    /**
     * Sorts the stalls in the list by rating.
     */
    public void sortByRating() {
        Collections.sort(internalList, new StallRatingComparator());
    }

    public void sortByLocation() {
        Collections.sort(internalList, new StallLocationComparator());
    }

    public void sortByPrice() {
        Collections.sort(internalList, new StallPriceComparator());
    }

    /**
     * Removes the equivalent stall from the list.
     * The stall must exist in the list.
     */
    public void remove(Stall toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StallNotFoundException();
        }
    }

    public void setStalls(UniqueStallList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code stalls}.
     * {@code stalls} must not contain duplicate stalls.
     */
    public void setStalls(List<Stall> stalls) {
        requireAllNonNull(stalls);
        if (!stallsAreUnique(stalls)) {
            throw new DuplicateStallException();
        }

        internalList.setAll(stalls);
    }

    public void sortStallRating() {
        Collections.sort(internalList, new StallRatingComparator());
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Stall> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Stall> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueStallList)) {
            return false;
        }

        UniqueStallList otherUniqueStallList = (UniqueStallList) other;
        return internalList.equals(otherUniqueStallList.internalList);
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
     * Returns true if {@code stalls} contains only unique stalls.
     */
    private boolean stallsAreUnique(List<Stall> stalls) {
        for (int i = 0; i < stalls.size() - 1; i++) {
            for (int j = i + 1; j < stalls.size(); j++) {
                if (stalls.get(i).isSameStall(stalls.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


}
