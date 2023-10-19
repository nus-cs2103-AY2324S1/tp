package seedu.address.model.musician;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.musician.exceptions.DuplicateMusicianException;
import seedu.address.model.musician.exceptions.MusicianNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A musician is considered unique by comparing using {@code Musician#isSameMusician(Musician)}.
 * As such, adding and updating of persons uses Musician#isSameMusician(Musician) for equality
 * so as to ensure that the musician being added or updated is unique in terms of identity in the UniqueMusicianList.
 * However, the removal of a musician uses Musician#equals(Object) so as to ensure that
 * the musician with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Musician#isSameMusician(Musician)
 */
public class UniqueMusicianList implements Iterable<Musician> {

    private final ObservableList<Musician> internalList = FXCollections.observableArrayList();
    private final ObservableList<Musician> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent musician as the given argument.
     */
    public boolean contains(Musician toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMusician);
    }

    /**
     * Adds a musician to the list.
     * The musician must not already exist in the list.
     */
    public void add(Musician toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMusicianException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the musician {@code target} in the list with {@code editedMusician}.
     * {@code target} must exist in the list.
     * The musician identity of {@code editedMusician} must not be the same as another existing musician in the list.
     */
    public void setMusician(Musician target, Musician editedMusician) {
        requireAllNonNull(target, editedMusician);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MusicianNotFoundException();
        }

        if (!target.isSameMusician(editedMusician) && contains(editedMusician)) {
            throw new DuplicateMusicianException();
        }

        internalList.set(index, editedMusician);
    }

    /**
     * Removes the equivalent musician from the list.
     * The musician must exist in the list.
     */
    public void remove(Musician toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MusicianNotFoundException();
        }
    }
    public void setMusicians(UniqueMusicianList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }
    /**
     * Replaces the contents of this list with {@code musicians}.
     * {@code musicians} must not contain duplicate musicians.
     */
    public void setMusicians(List<Musician> musicians) {
        requireAllNonNull(musicians);
        if (!musiciansAreUnique(musicians)) {
            throw new DuplicateMusicianException();
        }

        internalList.setAll(musicians);
    }
    public Musician getMusician(int index) {
        return internalList.get(index);
    }
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Musician> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Musician> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueMusicianList)) {
            return false;
        }

        UniqueMusicianList otherUniqueMusicianList = (UniqueMusicianList) other;
        return internalList.equals(otherUniqueMusicianList.internalList);
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
     * Returns true if {@code musicians} contains only unique musicians.
     */
    private boolean musiciansAreUnique(List<Musician> musicians) {
        for (int i = 0; i < musicians.size() - 1; i++) {
            for (int j = i + 1; j < musicians.size(); j++) {
                if (musicians.get(i).isSameMusician(musicians.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
