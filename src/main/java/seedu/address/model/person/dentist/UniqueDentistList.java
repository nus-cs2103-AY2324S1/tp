package seedu.address.model.person.dentist;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DentistNotFoundException;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * A list of dentists that enforces uniqueness between its elements and does not allow nulls.
 * A dentist is considered unique by comparing using {@code Dentist#isSameDentist(Dentist)}.
 * As such, adding and updating of dentists uses Dentist#isSameDentist(Dentist) for equality
 * so as to ensure that the dentist being added or updated isunique in terms of identity in the UniqueDentistList.
 * However, the removal of a dentist uses Dentist#equals(Object) so as to ensure that the dentist with
 * exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Dentist#isSameDentist(Dentist)
 */
public class UniqueDentistList implements Iterable<Dentist> {

    private final ObservableList<Dentist> internalList = FXCollections.observableArrayList();
    private final ObservableList<Dentist> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent dentist as the given argument.
     */
    public boolean contains(Dentist toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDentist);
    }

    /**
     * Adds a dentist to the list.
     * The dentist must not already exist in the list.
     */
    public void add(Dentist toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the dentist {@code target} in the list with {@code editedDentist}.
     * {@code target} must exist in the list.
     * The dentist identity of {@code editedDentist} must not be the same as another existing dentist in the list.
     */
    public void setDentist(Dentist target, Dentist editedDentist) {
        requireAllNonNull(target, editedDentist);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DentistNotFoundException();
        }

        if (!target.isSameDentist(editedDentist) && contains(editedDentist)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedDentist);
    }

    /**
     * Returns the dentist with the given target integer {@code target}.
     */
    public Dentist getDentist(int target) {
        requireAllNonNull(target);
        Dentist dentist = internalList.get(target);

        if (isNull(dentist)) {
            throw new DentistNotFoundException();
        }

        return dentist;
    }

    /**
     * Removes the equivalent dentist from the list.
     * The dentist must exist in the list.
     */
    public void remove(Dentist toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DentistNotFoundException();
        }
    }

    public void setDentists(UniqueDentistList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code dentists}.
     * {@code dentists} must not contain duplicate dentists.
     */
    public void setDentists(List<Dentist> dentists) {
        requireAllNonNull(dentists);
        if (!dentistsAreUnique(dentists)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(dentists);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Dentist> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Dentist> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueDentistList)) {
            return false;
        }

        UniqueDentistList otherUniqueDentistList = (UniqueDentistList) other;
        return internalList.equals(otherUniqueDentistList.internalList);
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
     * Returns true if {@code dentists} contains only unique dentists.
     */
    private boolean dentistsAreUnique(List<Dentist> dentists) {
        for (int i = 0; i < dentists.size() - 1; i++) {
            for (int j = i + 1; j < dentists.size(); j++) {
                if (dentists.get(i).isSamePerson(dentists.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
