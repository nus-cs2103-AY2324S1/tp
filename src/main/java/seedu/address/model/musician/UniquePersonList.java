package seedu.address.model.musician;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.musician.exceptions.DuplicatePersonException;
import seedu.address.model.musician.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A musician is considered unique by comparing using {@code Musician#isSamePerson(Musician)}. As such, adding and updating of
 * persons uses Musician#isSamePerson(Musician) for equality so as to ensure that the musician being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a musician uses Musician#equals(Object) so
 * as to ensure that the musician with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Musician#isSamePerson(Musician)
 */
public class UniquePersonList implements Iterable<Musician> {

    private final ObservableList<Musician> internalList = FXCollections.observableArrayList();
    private final ObservableList<Musician> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent musician as the given argument.
     */
    public boolean contains(Musician toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a musician to the list.
     * The musician must not already exist in the list.
     */
    public void add(Musician toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the musician {@code target} in the list with {@code editedMusician}.
     * {@code target} must exist in the list.
     * The musician identity of {@code editedMusician} must not be the same as another existing musician in the list.
     */
    public void setPerson(Musician target, Musician editedMusician) {
        requireAllNonNull(target, editedMusician);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedMusician) && contains(editedMusician)) {
            throw new DuplicatePersonException();
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
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code musicians}.
     * {@code musicians} must not contain duplicate musicians.
     */
    public void setPersons(List<Musician> musicians) {
        requireAllNonNull(musicians);
        if (!personsAreUnique(musicians)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(musicians);
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
        if (!(other instanceof UniquePersonList)) {
            return false;
        }

        UniquePersonList otherUniquePersonList = (UniquePersonList) other;
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
     * Returns true if {@code musicians} contains only unique musicians.
     */
    private boolean personsAreUnique(List<Musician> musicians) {
        for (int i = 0; i < musicians.size() - 1; i++) {
            for (int j = i + 1; j < musicians.size(); j++) {
                if (musicians.get(i).isSamePerson(musicians.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
