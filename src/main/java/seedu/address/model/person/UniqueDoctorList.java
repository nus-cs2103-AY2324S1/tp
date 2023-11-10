package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueDoctorList extends UniqueObjectList<Doctor> {

    protected final ObservableList<Doctor> internalList = FXCollections.observableArrayList();
    protected final ObservableList<Doctor> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public void setDoctors(UniqueDoctorList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    @Override
    public boolean contains(Doctor toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }
    /**
     * Returns true if the list contains the same ic as the given argument.
     */
    public boolean containsIc(Ic nric) {
        requireNonNull(nric);
        return internalList.stream().anyMatch(x -> x.getIc().equals(nric));
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    @Override
    public void add(Doctor toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    @Override
    public void setObject(Doctor target, Doctor editedDoctor) {
        requireAllNonNull(target, editedDoctor);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedDoctor) && contains(editedDoctor)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedDoctor);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    @Override
    public void remove(Doctor toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    @Override
    public void setObjects(List<Doctor> doctors) {
        requireAllNonNull(doctors);
        if (!objectsAreUnique(doctors)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(doctors);
    }

    @Override
    public ObservableList<Doctor> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    @Override
    protected boolean objectsAreUnique(List<Doctor> doctors) {
        for (int i = 0; i < doctors.size() - 1; i++) {
            for (int j = i + 1; j < doctors.size(); j++) {
                if (doctors.get(i).isSamePerson(doctors.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

